package com.wp.bitcoin.dto;

import java.util.Date;

public class BlockListDTO {
    private String blockhash;

    private Integer blockheight;

    private Long blocktime;

    private Short txsize;

    private Integer blocksize;

    public String getBlockhash() {
        return blockhash;
    }

    public void setBlockhash(String blockhash) {
        this.blockhash = blockhash;
    }

    public Integer getBlockheight() {
        return blockheight;
    }

    public void setBlockheight(Integer blockheight) {
        this.blockheight = blockheight;
    }

    public Long getBlocktime() {
        return blocktime;
    }

    public void setBlocktime(Long blocktime) {
        this.blocktime = blocktime;
    }

    public Short getTxsize() {
        return txsize;
    }

    public void setTxsize(Short txsize) {
        this.txsize = txsize;
    }

    public Integer getBlocksize() {
        return blocksize;
    }

    public void setBlocksize(Integer blocksize) {
        this.blocksize = blocksize;
    }
}
