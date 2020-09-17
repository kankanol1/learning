package com.qxd.learn.task;
import com.qxd.learn.service.TokenDaoImpl;
import com.qxd.learn.service.wx.JSAPITicketService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledService  {
    private static Logger logger = Logger.getLogger(ScheduledService.class);
    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss sss");

    @Autowired
    private TokenDaoImpl tokenDao;
    @Autowired
    private JSAPITicketService jsapiTicketService;
    @Scheduled(cron="0 */60 * * * *")
    @PostConstruct
    public void startDeleteImgService(){
        logger.info("=====>>>>>使用cron  {} "+ df.format(new Date()));
        tokenDao.save();
    }

}
