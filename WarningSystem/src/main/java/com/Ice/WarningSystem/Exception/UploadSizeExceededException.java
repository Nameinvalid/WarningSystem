package com.Ice.WarningSystem.Exception;

import ch.qos.logback.core.encoder.EchoEncoder;

public class UploadSizeExceededException extends Exception {

    public UploadSizeExceededException(){

    }

    public UploadSizeExceededException(String message){
        super(message);
    }

    public UploadSizeExceededException(String message,Throwable cause){
        super(message,cause);
    }

    public UploadSizeExceededException(Throwable cause){
        super(cause);
    }

    public UploadSizeExceededException(String message,Throwable cause,boolean enableSuppression, boolean writeableStackTrace){
        super(message,cause,enableSuppression,writeableStackTrace);
    }
}
