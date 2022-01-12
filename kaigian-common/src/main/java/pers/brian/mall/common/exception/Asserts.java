package pers.brian.mall.common.exception;


import pers.brian.mall.common.api.IErrorCode;

/**
 * 断言处理类，用于抛出各种API异常
 *
 * @author BrianHu
 * @create 2021-11-11 11:11
 **/
public class Asserts {
    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(IErrorCode errorCode) {
        throw new ApiException(errorCode);
    }
}
