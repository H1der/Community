package com.hider.community.service;

import com.hider.community.exception.CustomizeErrorCode;
import com.hider.community.exception.CustomizeException;
import com.hider.community.model.Comment;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    public void insert(Comment comment) {
        throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
    }
}
