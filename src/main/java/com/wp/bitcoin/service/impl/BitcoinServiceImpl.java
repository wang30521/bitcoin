package com.wp.bitcoin.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wp.bitcoin.api.BitcoinJsonRpcApi;
import com.wp.bitcoin.api.BitcoinRestApi;
import com.wp.bitcoin.dao.BlockMapper;
import com.wp.bitcoin.dao.TransactionDetailMapper;
import com.wp.bitcoin.dao.TransactionMapper;
import com.wp.bitcoin.enumeration.TxDetailType;
import com.wp.bitcoin.po.Block;
import com.wp.bitcoin.po.Transaction;
import com.wp.bitcoin.po.TransactionDetail;
import com.wp.bitcoin.service.BitcoinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.LinkedHashMap;


public class BitcoinServiceImpl implements BitcoinService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BlockMapper blockMapper;

    @Autowired
    private BitcoinJsonRpcApi bitcoinJsonRpcApi;

    @Autowired
    private BitcoinRestApi bitcoinRestApi;

    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private TransactionDetailMapper transactionDetailMapper;

    @Override
    @Async
    public void syncBlockchainFromHash(String blockhash) throws Throwable {
        logger.info("begin to sync blockchain from {}", blockhash);
        String tempBlockhash = blockhash;
        while (tempBlockhash != null && !tempBlockhash.isEmpty()){

            String nextBlock = syncBlock(tempBlockhash);
            tempBlockhash = nextBlock;
        }
        logger.info("end sync blockchain");
    }

    @Override
    @Transactional
    public String syncBlock(String blockhash) throws Throwable {
        JSONObject blockJson = bitcoinRestApi.getBlock(blockhash);
        Block block = new Block();
        block.setBlockhash(blockJson.getString("hash"));
        block.setBlockheight(blockJson.getInteger("height"));
        Long timestamp = blockJson.getLong("time");
        Date time = new Date(timestamp * 1000);
        block.setBlocktime(time);
        block.setTxsize(blockJson.getShort("nTx"));
        block.setBlocksize(blockJson.getInteger("size"));
        block.setDifficult(blockJson.getDouble("difficulty"));
        block.setPrevBlockHash(blockJson.getString("previousblockhash"));
        block.setNextBlockHash(blockJson.getString("nextblockhash"));
        Integer confirmations = blockJson.getInteger("confirmations");
        blockMapper.insert(block);

        JSONArray txesArray = blockJson.getJSONArray("tx");

        for (Object txObj : txesArray) {
            JSONObject jsonObject = new JSONObject((LinkedHashMap) txObj);
            syncTx(jsonObject, blockhash, time, confirmations);
        }

        return block.getNextBlockHash();
    }

    @Override
    @Transactional
    public void syncTx(JSONObject txJson, String blockhash, Date time, Integer confirmations) throws Throwable {
        Transaction tx = new Transaction();
        String txid = txJson.getString("txid");
        tx.setTxhash(txid);
        tx.setBlockhash(blockhash);
        tx.setTxtime(time);
        tx.setSize(txJson.getLong("size"));
        tx.setWeight(txJson.getInteger("weight"));
        tx.setConfirmations(confirmations);
        transactionMapper.insert(tx);

        //todo set tx detail
        syncTxDetail(txJson, txid);

        //todo set tx amount
    }

    @Override
    @Transactional
    public void syncTxDetail(JSONObject txJson, String txid) throws Throwable {
        JSONArray vouts = txJson.getJSONArray("vout");
        syncTxDetailVout(vouts, txid);
        JSONArray vins = txJson.getJSONArray("vin");
        syncTxDetailVin(vins, txid);
    }

    @Override
    @Transactional
    public void syncTxDetailVout(JSONArray vouts, String txid) {
        for (Object voutObj : vouts) {
            JSONObject jsonObject = new JSONObject((LinkedHashMap) voutObj);
            TransactionDetail txDetail = new TransactionDetail();
            txDetail.setAmount(jsonObject.getDouble("value"));
            txDetail.setTxhash(txid);
            txDetail.setType((byte) TxDetailType.Receive.ordinal());
            JSONObject scriptPubKey = jsonObject.getJSONObject("scriptPubKey");
            JSONArray addresses = scriptPubKey.getJSONArray("addresses");
            if (addresses != null){
                String address = addresses.getString(0);
                txDetail.setAddress(address);
            }
            transactionDetailMapper.insert(txDetail);
        }
    }

    @Override
    @Transactional
    public void syncTxDetailVin(JSONArray vins, String txid) throws Throwable {
        for (Object vinObj : vins) {
            JSONObject jsonObject = new JSONObject((LinkedHashMap) vinObj);
            String vinTxid = jsonObject.getString("txid");
            Integer n = jsonObject.getInteger("vout");

            if (vinTxid != null){
                JSONObject vinTxJson = bitcoinJsonRpcApi.getTransactionById(vinTxid);
                JSONArray vouts = vinTxJson.getJSONArray("vout");
                JSONObject utxoJson = vouts.getJSONObject(n);

                TransactionDetail txDetail = new TransactionDetail();
                txDetail.setAmount(-utxoJson.getDouble("value"));
                txDetail.setTxhash(txid);
                txDetail.setType((byte) TxDetailType.Send.ordinal());
                JSONObject scriptPubKey = utxoJson.getJSONObject("scriptPubKey");
                JSONArray addresses = scriptPubKey.getJSONArray("addresses");
                if (addresses != null){
                    String address = addresses.getString(0);
                    txDetail.setAddress(address);
                }
                transactionDetailMapper.insert(txDetail);
            }

        }
    }

}
