package com.serotonin.service.impl;

import com.serotonin.service.DealEventService;
import com.serotonin.service.GetKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by fchkong on 2018/11/15.
 */
@Service
public class DealEventServiceImpl implements DealEventService {
    @Autowired
    private GetKey getKey;

    @Override
    public void dealEvent(Integer i, String result1, String com, Integer deviceNum) {
        try {
            String alarm = result1.substring(0, 1);
            if (alarm.equals("0")) {//无报警
                System.out.println("无报警");
                System.out.println(alarm);
            } else {//有报警
                //获取key
                String key = getKey.getKey(com, i, deviceNum);
                System.out.println("有报警,对数据库进行处理,key为:" + key);
                System.out.println(alarm);
                Thread.sleep(1000);
                System.out.println("报警处理中.........");
            }
            String falut = result1.substring(1, 2);
            if (falut.equals("0")) {//无故障
                //todo:对数据库进行处理
                //todo:先查询数据库实时表中是否有这条数据,无就进行不进行操作,有就改变将故障存放在历史表中
                //获取key
                String key = getKey.getKey(com, i, deviceNum);
                System.out.println("无故障,key为:" + key);
                System.out.println(falut);
            } else {//有故障
                //todo:对数据库进行处理
                //todo:先查询数据库实时表中是否有这条数据,有就不存储,无就进行存储
                //获取key
                String key = getKey.getKey(com, i, deviceNum);
                System.out.println("有故障,key为:" + key);
                System.out.println(falut);
                Thread.sleep(1000);
                System.out.println("故障处理中.........");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
