package com.wp.bitcoin.api;


import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "BitcoinRestApi", url = "http://localhost:18332")
public interface BitcoinRestApi {

    @GetMapping("/rest/chaininfo.json")
    JSONObject getBlockChainInfo();

    @GetMapping("/rest/block/notxdetails/{blockhash}.json")
    JSONObject getBlockNoTxDetails(@PathVariable(value = "blockHash") String blockhash);

    @GetMapping("/rest/tx/{txHash}.json")
    JSONObject getTransaction(@PathVariable(value = "txHash") String txHash);

    @GetMapping("/rest/block/{blockHash}.json")
    JSONObject getBlock(@PathVariable("blockHash") String blockHash);

    @GetMapping("/rest/headers/{count}/{blockhash}.json")
    List<JSONObject> getBlockHeaders(@PathVariable("count") Integer count, @PathVariable("blockhash") String blockhash);

    @GetMapping("/rest/mempool/info.json")
    JSONObject getMempoolInfo();

    @GetMapping("/rest/mempool/contents.json")
    JSONObject getMempoolContents();

    @GetMapping("/rest/getutxos/{txid}-{n}.json")
    JSONObject getUTXO(@PathVariable("txid") String txid, @PathVariable("n") Integer n);

    @GetMapping("/rest/getutxos/checkmempool/{txid}-{n}.json")
    JSONObject getUTXOCheckMempool(@PathVariable("txid") String txid, @PathVariable("n") Integer n);


}
