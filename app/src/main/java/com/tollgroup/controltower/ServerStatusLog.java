package com.tollgroup.controltower;

/**
 * Created by pong on 2017/4/4.
 */

 class ServerStatusLog {
    private String LogDetail;
    private String Timestamp;
    private ServerStatusLog () {}

    public ServerStatusLog (String logdetail, String Timestamp) {
        this.LogDetail = logdetail;
        this.Timestamp = Timestamp;
    }

    public String getLogDetail() {
        return LogDetail;
    }
    public String getTimestamp() {
        return Timestamp;
    }

}
