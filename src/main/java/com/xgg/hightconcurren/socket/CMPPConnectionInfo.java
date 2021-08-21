package com.xgg.hightconcurren.socket;

import lombok.Data;

@Data
public class CMPPConnectionInfo {

    private String host;

    private Integer port;

    //每次发送包的间隔时间 -> 用于保持长连接  按照文档默认3分钟
    private Integer interval=3;
    //每次发送包的超时时间 -> 用于重试 按照文档默认60秒
    private Integer timeout=60;
    //重试次数 按照文档默认3次
    private Integer retry=3;
    //长连接=true or 短连接=false 默认 false
    private boolean keep;
}
