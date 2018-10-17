package com.agioe.tool.data.singleton;


import com.agioe.tool.data.tcp.api.ControlListener;
import com.agioe.tool.data.tcp.api.DefaultTcpApiInstance;

/**
 * 枚举模式创建单例
 */
public class TcpApiSingleton {

    public static DefaultTcpApiInstance getDefaultTcpApiInstance() {
        return MyEnumSingleton.INSTANCE.getDefaultTcpApiInstance();
    }

    private enum MyEnumSingleton {
        INSTANCE;
        private DefaultTcpApiInstance defaultTcpApiInstance;

        private ControlListener listener;

        private MyEnumSingleton() {
            defaultTcpApiInstance = new DefaultTcpApiInstance(listener);
        }

        public DefaultTcpApiInstance getDefaultTcpApiInstance() {
            return defaultTcpApiInstance;
        }
    }
}
