package com.hider.community.controller;

import com.hider.community.dto.CommentCreateDto;
import com.hider.community.dto.CommentDto;
import com.hider.community.dto.ResultDto;
import com.hider.community.enums.CommentTypeEnum;
import com.hider.community.exception.CustomizeErrorCode;
import com.hider.community.model.Comment;
import com.hider.community.model.User;
import com.hider.community.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDto commentCreateDto, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        //判断当前提交的请求中用户是否登录
        if (user == null) {
            return ResultDto.errorOf(CustomizeErrorCode.NO_LOGIN);
        }

        if (commentCreateDto == null || StringUtils.isBlank(commentCreateDto.getContent())) {
            return ResultDto.errorOf(CustomizeErrorCode.COMMENT_IS_EMPTY);
        }

        Comment comment = new Comment();
        comment.setParentId(commentCreateDto.getParentId());
        comment.setContent(commentCreateDto.getContent());
        comment.setType(commentCreateDto.getType());
        comment.setCommentator(user.getId());
        comment.setParentId(commentCreateDto.getParentId());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setLikeCount(0);
        commentService.insert(comment, user);

        return ResultDto.okOf();
    }

    @ResponseBody
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    public ResultDto<List> comments(@PathVariable(name = "id") Integer id) {
        List<CommentDto> commentDtos = commentService.listByTargetId(id, CommentTypeEnum.COMMENT);
        return ResultDto.okOf(commentDtos);
    }
}
