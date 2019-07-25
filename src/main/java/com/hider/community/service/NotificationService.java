package com.hider.community.service;

import com.hider.community.dto.NotificationDto;
import com.hider.community.dto.PaginationDto;
import com.hider.community.enums.NotificationStatusEnum;
import com.hider.community.enums.NotificationTypeEnum;
import com.hider.community.exception.CustomizeErrorCode;
import com.hider.community.exception.CustomizeException;
import com.hider.community.mapper.NotificationMapper;
import com.hider.community.mapper.UserMapper;
import com.hider.community.model.Notification;
import com.hider.community.model.NotificationExample;
import com.hider.community.model.User;
import com.hider.community.model.UserExample;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class NotificationService {
    @Autowired
    private NotificationMapper notificationMapper;
    @Autowired
    private UserMapper userMapper;

    public PaginationDto list(Integer userId, Integer page, Integer size) {

        PaginationDto<NotificationDto> paginationDto = new PaginationDto<>();

        Integer totalPage;
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria().andReceiverEqualTo(userId);
        Integer totalCount = (int) notificationMapper.countByExample(notificationExample);

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

        NotificationExample example = new NotificationExample();
        example.createCriteria().andReceiverEqualTo(userId);
        example.setOrderByClause("gmt_create desc");
        List<Notification> notifications = notificationMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));
        if (notifications.size() == 0) {
            return paginationDto;
        }
        List<NotificationDto> notificationDtos = new ArrayList<>();
        for (Notification notification : notifications) {
            NotificationDto notificationDto = new NotificationDto();
            BeanUtils.copyProperties(notification, notificationDto);
            notificationDto.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
            notificationDtos.add(notificationDto);
        }
        paginationDto.setData(notificationDtos);
        return paginationDto;
    }

    public Long unreadCount(Integer userId) {
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria().andReceiverEqualTo(userId).andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());
        return notificationMapper.countByExample(notificationExample);
    }

    public NotificationDto read(Integer id, User user) {
        Notification notification = notificationMapper.selectByPrimaryKey(id);
        if (notification == null) {
            throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FAIL);
        }
        if (!Objects.equals(notification.getReceiver(), user.getId())) {
            throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FAIL);
        }
        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationMapper.updateByPrimaryKey(notification);

        NotificationDto notificationDto = new NotificationDto();
        BeanUtils.copyProperties(notification, notificationDto);
        notificationDto.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
        return notificationDto;
    }
}
