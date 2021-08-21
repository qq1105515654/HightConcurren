package com.xgg.hightconcurren.socket;

import lombok.Data;

import java.net.Socket;
import java.security.InvalidParameterException;

@Data
public class CMPPClient {

    private CMPPConnectionInfo conInfo=null;

    private Socket socket;
    //长连接的链路检测包内容
    private byte Reserved=1;

    private static CMPPClient client;

    public static CMPPClient getInstance(CMPPConnectionInfo info){
        if(info==null){
            throw new InvalidParameterException("Param is not empty!");
        }
        if(client==null){
            client=new CMPPClient(info);
        }
        return client;
    }

    private CMPPClient(CMPPConnectionInfo info){
        this.conInfo=info;
    }

    private CMPPClient(){}

    public Socket getSocket(){return this.socket;}

    private void builderSocket(CMPPConnectionInfo info){

    }
}
