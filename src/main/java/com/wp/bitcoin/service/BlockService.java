package com.wp.bitcoin.service;

import com.wp.bitcoin.dto.BlockListDTO;

import java.util.List;

public interface BlockService {

    List<BlockListDTO> getRecentBlocks();
}
