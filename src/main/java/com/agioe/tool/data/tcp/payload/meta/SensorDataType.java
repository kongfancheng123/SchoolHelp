package com.agioe.tool.data.tcp.payload.meta;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

/**
 * 数据类型枚举
 *
 * @author yshen
 * @since 2018/5/21
 */
public enum SensorDataType {

    YC((byte) 0) {
        @Override
        public String decode(ByteBuf buf) {
            String result = String.valueOf(buf.readFloat());
            return result;
        }

        @Override
        public ByteBuf encode(String val) {
            ByteBuf buf = Unpooled.buffer();
            buf.writeFloat(Float.valueOf(val));
            return buf;
        }

    },
    YX((byte) 1) {
        @Override
        public String decode(ByteBuf buf) {
            String result = String.valueOf(buf.readByte());
            return result;
        }

        @Override
        public ByteBuf encode(String val) {
            return null;
        }

    },
    YC_FLOAT_ARRAY((byte) 2) {
        @Override
        public String decode(ByteBuf buf) {
            StringBuilder sb = new StringBuilder();
            while (buf.readableBytes() > 0) {
                sb.append(buf.readFloat()).append(",");
            }
            return sb.toString();
        }

        @Override
        public ByteBuf encode(String val) {
            return null;
        }

    },
    YC_INT_ARRAY((byte) 3) {
        @Override
        public String decode(ByteBuf buf) {
            StringBuilder sb = new StringBuilder();
            while (buf.readableBytes() > 0) {
                sb.append(buf.readInt()).append(",");
            }
            return sb.toString();
        }

        @Override
        public ByteBuf encode(String val) {
            return null;
        }

    },
    CHARACTER_STRING((byte) 4) {
        @Override
        public String decode(ByteBuf buf) {
            byte[] valBuf = new byte[buf.readableBytes()];
            buf.readBytes(valBuf);
            return new String(valBuf, Charset.forName("ascii"));
        }

        @Override
        public ByteBuf encode(String val) {
            return null;
        }

    };

    private byte type;

    SensorDataType(byte type) {
        this.type = type;
    }

    /**
     * 根据type获取枚举对象
     *
     * @param type
     * @return
     */
    public static SensorDataType getByType(byte type) {
        for (SensorDataType dataType : SensorDataType.values()) {
            if (dataType.type == type) {
                return dataType;
            }
        }
        throw new IllegalArgumentException("No element matches " + type);
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    /**
     * 解码
     *
     * @param buf
     * @return
     */
    public abstract String decode(ByteBuf buf);

    public abstract ByteBuf encode(String val);
}
