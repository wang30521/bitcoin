package com.wp.bitcoin.controller;

import com.wp.bitcoin.api.BitcoinJsonRpcApi;
import com.wp.bitcoin.api.BitcoinRestApi;
import com.wp.bitcoin.dto.BlockGetDTO;
import com.wp.bitcoin.dto.BlockListDTO;
import com.wp.bitcoin.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/block")
public class BlockController {

    @Autowired
    private BitcoinRestApi bitcoinRestApi;

    @Autowired
    private BitcoinJsonRpcApi bitcoinJsonRpcApi;

    @Autowired
    private BlockService blockService;

    @GetMapping("/getRecentBlocks")
    public List<BlockListDTO> getRecentBlocks() throws Throwable {

        List<BlockListDTO> recentBlocks = blockService.getRecentBlocks();

        return recentBlocks;
    }

    @GetMapping("/getByBlockhash")
    public BlockGetDTO getByBlockhash(@RequestParam String blockHash){
        BlockGetDTO blockGetDTO = new BlockGetDTO();
        blockGetDTO.setBlockhash("00000000000000000019ee09aeaa3004fb1c089bee625e4a67d9dcd23e7e8833");
        blockGetDTO.setBlockheight(580980);
        blockGetDTO.setBlocktime(new Date().getTime());
        blockGetDTO.setBlocksize(1253997);
        blockGetDTO.setTxsize((short) 2433);
        blockGetDTO.setDifficult(7409399249090.25);
        blockGetDTO.setPrevBlockHash("00000000000000000011e175cb81ce01c92cc7bb0e908b2065f6ef52598be500");
        blockGetDTO.setNextBlockHash("0000000000000000001f1c6fbb49e1200577f453e5a47dbff26e1d7d269141eb");
        blockGetDTO.setOutputTotal(11010.228023);
        blockGetDTO.setTransactionFees(0.68488396);
        blockGetDTO.setMerkleRoot("39b01d9b3231ce927ca7a3354bf13748e848cb8d8492b545cd8ff1226ba1da7f");

        return blockGetDTO;
    }

    @GetMapping("/getByHeight")
    public BlockGetDTO getByHeight(@RequestParam Integer blockHeight){
        BlockGetDTO blockGetDTO = new BlockGetDTO();
        blockGetDTO.setBlockhash("00000000000000000019ee09aeaa3004fb1c089bee625e4a67d9dcd23e7e8833");
        blockGetDTO.setBlockheight(580980);
        blockGetDTO.setBlocktime(new Date().getTime());
        blockGetDTO.setBlocksize(1253997);
        blockGetDTO.setTxsize((short) 2433);
        blockGetDTO.setDifficult(7409399249090.25);
        blockGetDTO.setPrevBlockHash("00000000000000000011e175cb81ce01c92cc7bb0e908b2065f6ef52598be500");
        blockGetDTO.setNextBlockHash("0000000000000000001f1c6fbb49e1200577f453e5a47dbff26e1d7d269141eb");
        blockGetDTO.setOutputTotal(11010.228023);
        blockGetDTO.setTransactionFees(0.68488396);
        blockGetDTO.setMerkleRoot("39b01d9b3231ce927ca7a3354bf13748e848cb8d8492b545cd8ff1226ba1da7f");

        return blockGetDTO;
    }
}
