-- ----------------------------
-- Table structure for block_chain
-- ----------------------------

CREATE TABLE `block` (
  `blockhash` varchar(70) NOT NULL,
  `blockheight` int(11) NOT NULL,
  `blocktime` datetime,
  `txSize` smallint,
  `blocksize` int,
  `difficult` double,
  `prev_block_hash` varchar(50),
  `next_block_hash` varchar(50),
  `output_total` double,
  `transaction_fees` double,
  `merkle_root` varchar(70),
  `bc_id` int(11) NOT NULL,
  PRIMARY KEY (`blockhash`),
  unique `index_bcid_height` (`bc_id`, `blockheight`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
