package com.hider.community.dto;

import com.hider.community.model.User;
import lombok.Data;

@Data
public class NotificationDto {
    private Integer id;
    private Long gmtCreate;
    private Integer status;
    private Integer notifier;
    private String notifierName;
    private Integer outerId;
    private String outerTitle;
    private String typeName;
    private Integer type;
}
