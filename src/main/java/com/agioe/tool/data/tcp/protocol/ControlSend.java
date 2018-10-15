package com.agioe.tool.data.tcp.protocol;

import com.agioe.tool.data.common.ObjectUtil;
import com.agioe.tool.data.tcp.ControlQueue;
import com.agioe.tool.data.tcp.Header;
import com.agioe.tool.data.tcp.Message;
import com.agioe.tool.data.tcp.payload.ControlParameter;
import com.agioe.tool.data.tcp.protocol.factory.AbstractProtocol;
import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static com.agioe.tool.data.tcp.payload.meta.ControlConstant.*;
import static com.agioe.tool.data.tcp.payload.meta.ProtocolConstant.CONTROL_SEND;


/**
 * 2.5.1.上级平台下发控制命令
 *
 * @author yshen
 * @since 2018/10/10
 */
public class ControlSend extends AbstractProtocol {

    public ControlSend() {
        super(CONTROL_SEND);
    }

    @Override
    public ByteBuf encode(Message msg) {
        return null;
    }

    @Override
    public Message decode(ByteBuf bodyBuf, Header header) {
        Message message = new Message();
        List<Object> body = new ArrayList<>();
        //控制点数量
        int keyNumber = bodyBuf.readShort();
        for (int i = 0; i < keyNumber; i++) {
            //控制点编码长度
            int keyLen = bodyBuf.readByte();
            //控制点编码
            byte[] keyBuf = new byte[keyLen];
            bodyBuf.readBytes(keyBuf);
            String key = new String(keyBuf, Charset.forName("ascii"));
            //控制命令类型
            byte controlCommandType = bodyBuf.readByte();
            //控制方式
            byte controlType = bodyBuf.readByte();
            //控制方式值
            short controlTypeVal = bodyBuf.readShort();
            //控制动作值
            String controlActionVal = null;
            switch (controlCommandType) {
                case MANUAL_REMOTE_CONTROL:
                    controlActionVal = String.valueOf(bodyBuf.readByte());
                    break;
                case LINK_REMOTE_CONTROL:
                    controlActionVal = String.valueOf(bodyBuf.readByte());
                    break;
                case MANUAL_REMOTE_ADJUSTMENT:
                    controlActionVal = String.valueOf(bodyBuf.readFloat());
                    break;
                case LINK_REMOTE_ADJUSTMENT:
                    controlActionVal = String.valueOf(bodyBuf.readFloat());
                    break;
                default:
                    break;
            }
            ControlParameter controlParameter = new ControlParameter();
            controlParameter.setKey(key);
            controlParameter.setCmdType(controlCommandType);
            controlParameter.setControlType(controlType);
            controlParameter.setControlTypeVal(controlTypeVal);
            controlParameter.setControlActionVal(controlActionVal);
            body.add(controlParameter);
        }
        message.setHeader(header);
        message.setBody(body);
        return message;
    }


    @Override
    public AbstractProtocol getReplyProtocol() {
        return new ControlReply();
    }

    @Override
    public Message buildReplyMessage(int sessionId, List<Object> objectList) {
        Message replyMsg = new Message();
        Header header = new Header();
        header.setSessionId(sessionId);
        List<ControlParameter> body = ObjectUtil.toEntity(objectList);
        for (ControlParameter parameter : body) {
            parameter.setFeedback((byte) 1);
        }
        replyMsg.setHeader(header);
        replyMsg.setBody(ObjectUtil.toObject(body));
        return replyMsg;
    }

    @Override
    public void onAvailable(Message msg) {
        List<Object> body = msg.getBody();
        List<ControlParameter> parameterList = (List) body;
        ControlQueue.getInstance().push(parameterList);
    }

    @Override
    public void send(String ipAndPortString, Message msg) {

    }
}
