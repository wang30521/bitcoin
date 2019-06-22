-- ----------------------------
-- Table structure for block_chain
-- ----------------------------

CREATE TABLE `transaction` (
  `txid` char(64) NOT NULL,
  `txhash` char(64) NOT NULL,
  `blockhash` char(64) NOT NULL,
  `size` bigint(20) NOT NULL,
  `weight` int(11) NOT NULL,
   `amount` double,
  `txtime` datetime NOT NULL,
  `total_input` double,
  `total_output` double,
  `confirmations` int,
  `fees` double,
  PRIMARY KEY (`txid`),
  unique `index_txhash` (`txhash`),
  unique `index_blockhash` (`blockhash`),
  unique `index_txtime` (`txtime`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
