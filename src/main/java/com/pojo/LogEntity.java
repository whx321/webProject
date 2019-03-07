/**
 * LogEntity.java
 * Copyright 2018 天津亿网通达网络技术有限公司.
 * All rights reserved.
 * Created on 2018-11-08 9:47
 */
package com.pojo;

/**
 * 文件的说明:日志实体类
 *
 * @author 杨凯
 * @version 1.0.0, 2018-11-08 9:47
 **/
public class LogEntity {
    private String USERID;
    private String MODULE;
    private String METHER;
    private String RESPONSE_TIME;
    private String IP;
    private String TIME;
    private String DESCRIPTION;

    public LogEntity() {
    }

    public LogEntity(String USERID, String MODULE, String METHER, String RESPONSE_TIME, String IP, String TIME, String DESCRIPTION) {
        this.USERID = USERID;
        this.MODULE = MODULE;
        this.METHER = METHER;
        this.RESPONSE_TIME = RESPONSE_TIME;
        this.IP = IP;
        this.TIME = TIME;
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getUSERID() {
        return USERID;
    }

    public void setUSERID(String USERID) {
        this.USERID = USERID;
    }

    public String getMODULE() {
        return MODULE;
    }

    public void setMODULE(String MODULE) {
        this.MODULE = MODULE;
    }

    public String getMETHER() {
        return METHER;
    }

    public void setMETHER(String METHER) {
        this.METHER = METHER;
    }

    public String getRESPONSE_TIME() {
        return RESPONSE_TIME;
    }

    public void setRESPONSE_TIME(String RESPONSE_TIME) {
        this.RESPONSE_TIME = RESPONSE_TIME;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getTIME() {
        return TIME;
    }

    public void setTIME(String TIME) {
        this.TIME = TIME;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    @Override
    public String toString() {
        return "LogEntity{" +
                "USERID='" + USERID + '\'' +
                ", MODULE='" + MODULE + '\'' +
                ", METHER='" + METHER + '\'' +
                ", RESPONSE_TIME='" + RESPONSE_TIME + '\'' +
                ", IP='" + IP + '\'' +
                ", TIME='" + TIME + '\'' +
                ", DESCRIPTION='" + DESCRIPTION + '\'' +
                '}';
    }
}
