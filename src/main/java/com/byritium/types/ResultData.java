package com.byritium.types;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.byritium.types.constance.ResultEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultData<T> {

    //响应码
    private int code;
    //数据体
    private T data;

    //相应信息
    private String message = "";


    public static ResultData success(Object data) {
        return common(ResultEnum.SUCCESS.getCode(), data, ResultEnum.SUCCESS.getMessage());
    }

    public static ResultData success(Object data, String message) {
        return common(ResultEnum.SUCCESS.getCode(), data, message);
    }

    public static ResultData success(int code, Object data, String message) {
        return common(code, data, message);
    }


    public static ResultData error(String message) {
        return common(ResultEnum.ERROR_CODE.getCode(), null, message);
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
