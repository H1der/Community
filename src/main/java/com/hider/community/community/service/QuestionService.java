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
        Integer totalCount = questionMapper.count();
        paginationDto.setPagination(totalCount, page, size);

        if (page < 1 || page > paginationDto.getTotalPage()) {
            page = 1;
        }
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
}
