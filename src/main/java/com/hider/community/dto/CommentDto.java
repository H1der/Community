package com.hider.community.dto;

import com.hider.community.model.User;
import lombok.Data;

@Data
public class CommentDto {
    private Integer id;
    private Integer parentId;
    private String content;
    private Integer type;
    private Integer commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer commentCount;
    private Integer likeCount;
    private User user;
}
