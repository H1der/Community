package com.hider.community.service;

import com.hider.community.dto.PaginationDto;
import com.hider.community.dto.QuestionDto;
import com.hider.community.dto.QuestionQueryDto;
import com.hider.community.exception.CustomizeErrorCode;
import com.hider.community.exception.CustomizeException;
import com.hider.community.mapper.QuestionExtMapper;
import com.hider.community.mapper.QuestionMapper;
import com.hider.community.mapper.UserMapper;
import com.hider.community.model.Question;
import com.hider.community.model.QuestionExample;
import com.hider.community.model.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;


    public PaginationDto list(String search, Integer page, Integer size) {
        if (StringUtils.isNoneBlank(search)) {
            String[] tags = StringUtils.split(search, ' ');
            search = Arrays.stream(tags).collect(Collectors.joining("|"));
        }
        PaginationDto paginationDto = new PaginationDto();
        Integer totalPage;
        QuestionQueryDto questionQueryDto = new QuestionQueryDto();
        questionQueryDto.setSearch(search);
        Integer totalCount = questionExtMapper.countBySearch(questionQueryDto);

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

        QuestionExample questionExample = new QuestionExample();
        questionExample.setOrderByClause("gmt_create desc");
        questionQueryDto.setSize(size);
        questionQueryDto.setPage(offset);
        List<Question> questions = questionExtMapper.selectBySearch(questionQueryDto);
        List<QuestionDto> questionDtoList = new ArrayList<>();


        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        paginationDto.setData(questionDtoList);

        return paginationDto;

    }

    public PaginationDto list(Integer userId, Integer page, Integer size) {
        PaginationDto paginationDto = new PaginationDto();

        Integer totalPage;
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(userId);
        Integer totalCount = (int) questionMapper.countByExample(questionExample);

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


        QuestionExample example = new QuestionExample();
        example.createCriteria().andCreatorEqualTo(userId);
        example.setOrderByClause("gmt_create desc");
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));
        List<QuestionDto> questionDtoList = new ArrayList<>();


        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        paginationDto.setData(questionDtoList);

        return paginationDto;
    }

    public QuestionDto getById(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDto questionDto = new QuestionDto();
        BeanUtils.copyProperties(question, questionDto);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDto.setUser(user);
        return questionDto;
    }

    public void createOrUpdate(Question question) {
        if (question.getId() == null) {
            //创建
            question.setCommentCount(0);
            question.setViewCount(0);
            question.setLikeCount(0);
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insert(question);
        } else {
            //更新
            Question updateQuestion = new Question();
            updateQuestion.setGmtModified(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            QuestionExample example = new QuestionExample();
            example.createCriteria().andIdEqualTo(question.getId());
            int updated = questionMapper.updateByExampleSelective(updateQuestion, example);
            if (updated != 1) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    public void incView(Integer id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.incView(question);
    }

    public List<QuestionDto> selectRelated(QuestionDto queryDto) {
        if (StringUtils.isBlank(queryDto.getTag())) {
            return new ArrayList<>();
        }
        String[] tags = StringUtils.split(queryDto.getTag(), ',');
        String regexpTag = Arrays.stream(tags).collect(Collectors.joining("|"));
        Question question = new Question();
        question.setId(queryDto.getId());
        question.setTag(regexpTag);

        List<Question> questions = questionExtMapper.selectRelated(question);
        List<QuestionDto> questionDtos = questions.stream().map(q -> {
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(q, questionDto);
            return questionDto;
        }).collect(Collectors.toList());

        return questionDtos;
    }
}
