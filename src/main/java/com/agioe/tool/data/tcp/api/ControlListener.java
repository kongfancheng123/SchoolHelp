package com.agioe.tool.data.tcp.api;

import com.agioe.tool.data.tcp.payload.ControlParameter;

import java.util.List;

/**
 * 控制监听器
 *
 * @author yshen
 * @since 2018/10/10
 */
public interface ControlListener {
    /**
     * 控制命令到达
     *
     * @param parameterList 控制命令参数
     */
    void onControlArrive(List<ControlParameter> parameterList);
}
