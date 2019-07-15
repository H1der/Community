package com.hider.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND(2001, "问题不存在!"),
    TARGET_PARAM_NOT_FOUND(2002, "没有选中问题或评论进行回复!"),
    NO_LOGIN(2003, "请先登录再评论!"),
    ;

    private Integer code;

    private String message;

    CustomizeErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }


}
