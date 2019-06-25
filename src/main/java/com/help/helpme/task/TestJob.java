//package com.help.helpme.task;
//
//import com.help.helpme.entity.sys.SysAdmin;
//import com.help.helpme.service.sys.SysAdminService;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//import java.util.List;
//
///**
// * @description: 测试
// * @author: zhangshuai
// * @create: 2019-06-05 17:06
// */
//@Component
//public class TestJob {
//
//    @Autowired
//    private SysAdminService sysAdminService;
//
//    private final Logger logger = Logger.getLogger(getClass());
//    @Scheduled(cron = "0/2 * * * * ?")
////    @Scheduled(cron = "0/30 * * * * ?")
////    @Scheduled(cron = "0 0 0/2 * * *")
////    @Scheduled(cron = "0 0/30 * * * ?")  //半小时
//    public void redoHandleJob() {
//        logger.info("开始执行任务redoHandleJob，时间:" + new Date());
//
//        SysAdmin sysAdmin = new SysAdmin();
//        List<SysAdmin> sysAdmins = sysAdminService.findAll(sysAdmin);
//        int i=0;
//        for (SysAdmin sysAdmin1: sysAdmins) {
//            i++;
//        }
//
//        logger.info("结束执行任务redoHandleJob,成功处理条数:"+i);
//
//    }
//
//}
