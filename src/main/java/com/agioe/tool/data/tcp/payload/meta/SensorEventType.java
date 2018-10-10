package com.agioe.tool.data.tcp.payload.meta;

/**
 * 事件类型枚举
 *
 * @author yshen
 * @since 2018/5/29
 */
public enum SensorEventType {
    /**
     * 报警
     */
    ALARM_CREATE((byte) 0),
    /**
     * 故障
     */
    FAULT_CREATE((byte) 1),
    /**
     * 故障解除
     */
    FAULT_REMOVE((byte) 2);


    private byte type;

    SensorEventType(byte type) {
        this.type = type;
    }

    /**
     * 根据type获取枚举对象
     *
     * @param type
     * @return
     */
    public static SensorEventType getByType(byte type) {
        for (SensorEventType eventType : SensorEventType.values()) {
            if (eventType.type == type) {
                return eventType;
            }
        }
        throw new IllegalArgumentException("No element matches " + type);
    }

    public byte getType() {
        return type;
    }
}

