package com.hider.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND(2001, "问题不存在!"),
    TARGET_PARAM_NOT_FOUND(2002, "没有选中问题或评论进行回复!"),
    NO_LOGIN(2003, "请先登录再评论!"),
    SYSTEM_ERROR(2004, "服务器错误,请稍后再试."),
    TYPE_PARAM_WRONG(2005, "评论类型错误或不存在."),
    COMMENT_NOT_FOUND(2006, "回复的评论不存在."),
    COMMENT_IS_EMPTY(2007, "输入内容不能为空."),
    READ_NOTIFICATION_FAIL(2008, "消息来源非法!."),
    NOTIFICATION_NOT_FOUND(2009, "信息未找到!."),
    FILE_UPLOAD_FAIL(2010, "图片上传失败!."),
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
