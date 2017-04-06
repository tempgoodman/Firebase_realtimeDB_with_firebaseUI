package com.tollgroup.controltower;

/**
 * Created by pong on 2017/4/4.
 */

 class ServerStatusLog {
    private String LogDetail;
    private String Timestamp;
    private String AlertStatus;
    private String AlertType;
    private String DisplayTime;
    private String ServerName;
    private String AlertDetail;

    private ServerStatusLog () {}

    public ServerStatusLog (String logdetail, String Timestamp) {
        this.LogDetail = logdetail;
        this.Timestamp = Timestamp;
    }
    public String getAlertType(){
        return AlertType;
    }
    public String getAlertStatus(){
        return AlertStatus;
    }

    public String getAlertDetail() {
        return AlertDetail;
    }

    public String getServerName() {
        return ServerName;
    }
    public String getDisplayTime() {
        return DisplayTime;
    }

    public String getLogDetail() {
        return LogDetail;
    }
    public String getTimestamp() {
        return Timestamp;
    }

}
