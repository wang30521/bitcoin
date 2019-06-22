package com.wp.bitcoin.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public interface BitcoinJsonRpcApi {

    JSONObject getBlockchainInfo() throws Throwable;

    JSONObject getBlockByHash(String blockhash) throws Throwable;

    JSONObject getTransactionById(String txid) throws Throwable;

    String getBlockhashByHeight(Integer height) throws Throwable;

    JSONObject getmempoolentry(String txid) throws Throwable;

    JSONArray getrawmempool() throws Throwable;

    JSONArray getMempoolInfo() throws Throwable;

    Double getBalance(String address) throws Throwable;
}
