package com.agioe.tool.data.Test;

import com.agioe.tool.data.tcp.api.ControlListener;
import com.agioe.tool.data.tcp.payload.ControlParameter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : YShen
 **/
@Component
public class MyControlListener implements ControlListener {
    @Override
    public void onControlArrive(List<ControlParameter> parameterList) {
        System.out.println("控制事件:" + parameterList);
    }
}
