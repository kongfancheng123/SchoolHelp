package com.serotonin.service.impl;

import com.serotonin.Thread.RduRunThread;
import com.serotonin.Thread.TcpRunThread;
import com.serotonin.entity.HostInfo;
import com.serotonin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by fchkong on 2018/11/19.
 */
@Service
public class ParamEntryServiceImpl implements ParamEntryService {
    @Autowired
    private HostInfoService hostInfoService;

    @Autowired
    private DealEventService dealEventService;

    @Autowired
    private RunService runService;

    @Autowired
    private RealtimeEventService realtimeEventService;


    @Override
    public void paramEntry() {
        HostInfo hostInfo = new HostInfo();
        hostInfo.setHostType(3);
        List<HostInfo> hostInfos = hostInfoService.selectByHostInfo(hostInfo);
        if (hostInfos.size() > 0) {
            for (HostInfo hostInfo1 : hostInfos) {
                String hostAddr = hostInfo1.getHostAddr();
                if (hostAddr.contains(".")) {
                    TcpRunThread tcpRunThread = new TcpRunThread(hostInfo1, hostInfoService, runService, dealEventService);
                    Thread thread = new Thread(tcpRunThread);
                    thread.start();
                } else {
                    RduRunThread rduRunThread = new RduRunThread(hostInfo1, hostInfoService, runService, dealEventService);
                    Thread thread = new Thread(rduRunThread);
                    thread.start();
                }
            }
        }
    }
}
