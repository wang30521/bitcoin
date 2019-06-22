-- ----------------------------
-- Table structure for block_chain
-- ----------------------------

CREATE TABLE `transaction_detail` (
  `txid` bigint NOT NULL auto_increment,
  `address` varchar(100),
  `type` tinyint,
  `amount` double,
  `txhash`   char(64),
  PRIMARY KEY (`txid`, `address`),
  index `index_address` (`address`),
   index `idx_txhash` (`txhash`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
