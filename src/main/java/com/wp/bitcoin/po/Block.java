package com.wp.bitcoin.po;

import java.util.Date;

public class Block {
    private String blockhash;

    private Integer blockheight;

    private Date blocktime;

    private Short txsize;

    private Integer blocksize;

    private Double difficult;

    private String prevBlockHash;

    private String nextBlockHash;

    private Double outputTotal;

    private Double transactionFees;

    private String merkleRoot;

    private Integer bcId;

    public String getBlockhash() {
        return blockhash;
    }

    public void setBlockhash(String blockhash) {
        this.blockhash = blockhash == null ? null : blockhash.trim();
    }

    public Integer getBlockheight() {
        return blockheight;
    }

    public void setBlockheight(Integer blockheight) {
        this.blockheight = blockheight;
    }

    public Date getBlocktime() {
        return blocktime;
    }

    public void setBlocktime(Date blocktime) {
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

    public Double getDifficult() {
        return difficult;
    }

    public void setDifficult(Double difficult) {
        this.difficult = difficult;
    }

    public String getPrevBlockHash() {
        return prevBlockHash;
    }

    public void setPrevBlockHash(String prevBlockHash) {
        this.prevBlockHash = prevBlockHash == null ? null : prevBlockHash.trim();
    }

    public String getNextBlockHash() {
        return nextBlockHash;
    }

    public void setNextBlockHash(String nextBlockHash) {
        this.nextBlockHash = nextBlockHash == null ? null : nextBlockHash.trim();
    }

    public Double getOutputTotal() {
        return outputTotal;
    }

    public void setOutputTotal(Double outputTotal) {
        this.outputTotal = outputTotal;
    }

    public Double getTransactionFees() {
        return transactionFees;
    }

    public void setTransactionFees(Double transactionFees) {
        this.transactionFees = transactionFees;
    }

    public String getMerkleRoot() {
        return merkleRoot;
    }

    public void setMerkleRoot(String merkleRoot) {
        this.merkleRoot = merkleRoot == null ? null : merkleRoot.trim();
    }

    public Integer getBcId() {
        return bcId;
    }

    public void setBcId(Integer bcId) {
        this.bcId = bcId;
    }
}