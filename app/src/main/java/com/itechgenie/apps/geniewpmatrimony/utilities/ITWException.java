package com.itechgenie.apps.geniewpmatrimony.utilities;

/**
 * Created by Prakash-hp on 28-05-2017.
 */

public class ITWException extends Exception {

    public ITWException(String message, Throwable e) {
        super(message, e);
    }

    public ITWException(String message) {
        super(message);
    }

    public ITWException(Throwable e) {
        super(e);
    }

}
