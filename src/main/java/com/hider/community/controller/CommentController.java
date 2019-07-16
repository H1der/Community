package com.hider.community.controller;

import com.hider.community.dto.CommentDto;
import com.hider.community.dto.ResultDto;
import com.hider.community.exception.CustomizeErrorCode;
import com.hider.community.model.Comment;
import com.hider.community.model.User;
import com.hider.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentDto commentDto, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        //判断当前提交的请求中用户是否登录
        if (user == null) {
            return ResultDto.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        Comment comment = new Comment();
        comment.setParentId(commentDto.getParentId());
        comment.setContent(commentDto.getContent());
        comment.setType(commentDto.getType());
        comment.setCommentator(user.getId());
        comment.setParentId(commentDto.getParentId());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setLikeCount(0);
        commentService.insert(comment);

        return ResultDto.okOf();
    }
}
