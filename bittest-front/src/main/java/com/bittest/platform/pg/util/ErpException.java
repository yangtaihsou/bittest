package com.bittest.platform.pg.util;

/**
 *
 * Date: 14-12-4
 * Time: 下午2:21
 */
public class ErpException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 6454021943685250455L;

    private String key;
    private String message;


    public ErpException(String key, String message) {
        super(key);
        this.key = key;
        this.message = message;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
