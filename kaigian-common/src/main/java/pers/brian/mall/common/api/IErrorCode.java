package pers.brian.mall.common.api;

/**
 * @Description: 封装API的错误码
 * @Author: BrianHu
 * @Create: 2021-11-11 11:11
 * @Version: 0.0.1
 **/
public interface IErrorCode {
    long getCode();

    String getMessage();
}
