package pers.brian.mall.common.exception;


import pers.brian.mall.common.api.IErrorCode;

/**
 * @Description: 断言处理类，用于抛出各种API异常
 * @Author: BrianHu
 * @Date: 2021-11-11 11:11
 * @Version: 0.0.1
 **/
public class Asserts {
    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(IErrorCode errorCode) {
        throw new ApiException(errorCode);
    }
}
