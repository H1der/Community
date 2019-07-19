package com.hider.community.mapper;

import com.hider.community.model.Comment;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}