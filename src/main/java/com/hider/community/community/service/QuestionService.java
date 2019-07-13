package com.hider.community.community.service;

import com.hider.community.community.dto.PaginationDto;
import com.hider.community.community.dto.QuestionDto;
import com.hider.community.community.mapper.QuestionMapper;
import com.hider.community.community.mapper.UserMapper;
import com.hider.community.community.model.Question;
import com.hider.community.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;


    public PaginationDto list(Integer page, Integer size) {
        PaginationDto paginationDto = new PaginationDto();
        Integer totalPage;
        Integer totalCount = questionMapper.count();

        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        if (page < 1 || page > totalPage) {
            page = 1;
        }
        paginationDto.setPagination(totalPage, page);
        //分页公式
        Integer offset = size * (page - 1);

        List<Question> questions = questionMapper.list(offset, size);
        List<QuestionDto> questionDtoList = new ArrayList<>();


        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        paginationDto.setQuestions(questionDtoList);

        return paginationDto;

    }

    public PaginationDto list(Integer userId, Integer page, Integer size) {
        PaginationDto paginationDto = new PaginationDto();

        Integer totalPage;
        Integer totalCount = questionMapper.countByUserId(userId);

        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        if (page < 1 || page > totalPage) {
            page = 1;
        }
        paginationDto.setPagination(totalPage, page);
        //分页公式
        Integer offset = size * (page - 1);

        List<Question> questions = questionMapper.listByUserId(userId, offset, size);
        List<QuestionDto> questionDtoList = new ArrayList<>();


        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        paginationDto.setQuestions(questionDtoList);

        return paginationDto;
    }

    public QuestionDto getByid(Integer id) {
        Question question = questionMapper.getById(id);
        QuestionDto questionDto = new QuestionDto();
        BeanUtils.copyProperties(question, questionDto);
        User user = userMapper.findById(question.getCreator());
        questionDto.setUser(user);
        return questionDto;
    }
}
