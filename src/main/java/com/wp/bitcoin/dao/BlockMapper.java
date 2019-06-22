package com.wp.bitcoin.dao;

import com.wp.bitcoin.po.Block;

import java.util.List;

public interface BlockMapper {


    int deleteByPrimaryKey(String blockhash);

    int insert(Block record);

    int insertSelective(Block record);

    Block selectByPrimaryKey(String blockhash);

    int updateByPrimaryKeySelective(Block record);

    int updateByPrimaryKey(Block record);

    //custom
    List<Block> selectRecentBlocks();
}