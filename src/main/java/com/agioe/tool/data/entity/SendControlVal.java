package com.agioe.tool.data.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendControlVal {
    private static SendControlVal s = new SendControlVal();
    private Integer val = 1;

    private SendControlVal() {

    }

    public static SendControlVal getSendControlVal() {
        return s;
    }
}
