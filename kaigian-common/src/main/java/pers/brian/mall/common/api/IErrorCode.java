package pers.brian.mall.common.api;

/**
 * 封装API的错误码
 *
 * @author BrianHu
 * @create 2021-11-11 11:11
 **/
public interface IErrorCode {
    long getCode();

    String getMessage();
}
