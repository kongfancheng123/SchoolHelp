package com.agioe.tool.data.tcp.payload.meta;

/**
 * 控制命令类型常量
 *
 * @author yshen
 * @since 2018/10/10
 */
public interface ControlConstant {
    /**
     * 人工遥控
     */
    byte MANUAL_REMOTE_CONTROL = 0;
    /**
     * 联动遥控
     */
    byte LINK_REMOTE_CONTROL = 1;
    /**
     * 人工遥调
     */
    byte MANUAL_REMOTE_ADJUSTMENT = 2;
    /**
     * 联动遥调
     */
    byte LINK_REMOTE_ADJUSTMENT = 3;

    /**
     * 设备重启
     */
    byte DEVICE_REBOOT = 11;
    /**
     * 设备开音
     */
    byte DEVICE_VOICE_ON = 12;
    /**
     * 设备消音
     */
    byte DEVICE_VOICE_OFF = 13;
    /**
     * 报警复位
     */
    byte ALARM_RESET = 14;
    /**
     * 单条报警复位
     */
    byte SINGLE_ALARM_RESET = 15;
}
