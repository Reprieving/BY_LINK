package com.byritium.types;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.byritium.types.constance.ResultEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * @author djh
 * @version 1.0
 * @date 2020/11/12/012 11:58
 * @description 返回前端数据公共类
 **/
@Getter
@Setter
public class ResultData<T> {

    //响应码
    private int code;
    //数据体
    private T data;

    //相应信息
    private String message = "";
    private String ex = "";

    private final static Object emptyData = null;
    private final static String emptyMessage = "";


    public static ResultData success() {
        return common(ResultEnum.SUCCESS.getCode(), emptyData, ResultEnum.SUCCESS.getMessage());
    }

    public static ResultData success(Object data) {
        return common(ResultEnum.SUCCESS.getCode(), data, ResultEnum.SUCCESS.getMessage());
    }

    public static ResultData success(String message) {
        return common(ResultEnum.SUCCESS.getCode(), emptyData, message);
    }

    public static ResultData success(Object data, String message) {
        return common(ResultEnum.SUCCESS.getCode(), data, message);
    }

    public static ResultData success(int code, Object data, String message) {
        return common(code, data, message);
    }

    public static ResultData error() {
        return common(ResultEnum.ERROR_CODE.getCode(), emptyData, emptyMessage);
    }

    public static ResultData error(Object data) {
        return common(ResultEnum.ERROR_CODE.getCode(), data, emptyMessage);
    }

    public static ResultData error(String message) {
        return common(ResultEnum.ERROR_CODE.getCode(), emptyData, message);
    }

    public static ResultData error(Object data, String message) {
        return common(ResultEnum.ERROR_CODE.getCode(), data, message);
    }

    public static ResultData error(int code, String message) {
        return common(code, emptyData, message);
    }

    public static ResultData error(int code, Object data, String message) {
        return common(code, data, message);
    }


    private static ResultData common(int code, Object data, String message) {
        ResultData resultData = new ResultData();
        resultData.setCode(code);
        resultData.setData(data);
        resultData.setMessage(message);

        return resultData;
    }

}
