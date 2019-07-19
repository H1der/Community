package com.hider.community.dto;

import com.hider.community.exception.CustomizeErrorCode;
import com.hider.community.exception.CustomizeException;
import lombok.Data;

import java.util.List;

@Data
public class ResultDto<T> {
    private Integer code;
    private String message;
    private T data;

    public static ResultDto errorOf(Integer code, String message) {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(code);
        resultDto.setMessage(message);
        return resultDto;
    }

    public static ResultDto errorOf(CustomizeErrorCode errorCode) {
        return errorOf(errorCode.getCode(), errorCode.getMessage());
    }

    public static ResultDto okOf() {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(200);
        resultDto.setMessage("请求成功");
        return resultDto;
    }

    public static <T> ResultDto okOf(Object t) {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(200);
        resultDto.setMessage("请求成功");
        resultDto.setData(t);
        return resultDto;
    }

    public static ResultDto errorOf(CustomizeException e) {
        return errorOf(e.getCode(), e.getMessage());
    }
}
