package com.wp.bitcoin.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class BitcoinScheduler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //@Autowired
   // private SimpMessageSendingOperations messageSendingOperations;

    //每十分钟更新一次,针对Block的生成和推送
    //@Async
    //2秒
    @Scheduled(fixedRate = 10*60*1000)
    public void syncData(){
        logger.info("begin to sync bitcoin data");
    }

}
