package com.wp.bitcoin.service.impl;

import com.wp.bitcoin.dao.BlockMapper;
import com.wp.bitcoin.dto.BlockListDTO;
import com.wp.bitcoin.po.Block;
import com.wp.bitcoin.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class BlockServiceImpl implements BlockService {
    @Autowired
    private BlockMapper blockMapper;

    @Override
    public List<BlockListDTO> getRecentBlocks() {
        ArrayList<BlockListDTO> blockListDTOS = new ArrayList<>();
        List<Block> blocks = blockMapper.selectRecentBlocks();
        for(Block block:blocks){
            BlockListDTO blockListDTO = new BlockListDTO();
            blockListDTO.setBlockhash(block.getBlockhash());
            blockListDTO.setBlockheight(block.getBlockheight());
            blockListDTO.setBlocktime(block.getBlocktime().getTime());
            blockListDTO.setTxsize(block.getTxsize());
            blockListDTO.setBlocksize(block.getBlocksize());
            blockListDTOS.add(blockListDTO);


        }

        return blockListDTOS;
    }
}
