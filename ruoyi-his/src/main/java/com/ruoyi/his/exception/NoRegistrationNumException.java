package com.ruoyi.his.exception;

/**
 * 无号源异常
 *
 * @author WuShaoYan
 * @date 2022/9/6
 */
public class NoRegistrationNumException  extends RuntimeException  {
    public NoRegistrationNumException() {
    }
    public NoRegistrationNumException(String msg) {
        super(msg);
    }

    public NoRegistrationNumException(Throwable cause) {
        super(cause);
    }

    public NoRegistrationNumException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
