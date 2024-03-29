package com.hider.community.controller;

import com.hider.community.dto.NotificationDto;
import com.hider.community.dto.PaginationDto;
import com.hider.community.enums.NotificationTypeEnum;
import com.hider.community.model.User;
import com.hider.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notification/{id}")
    public String profile(HttpServletRequest request, @PathVariable(name = "id") Integer id) {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }

        NotificationDto notificationDto = notificationService.read(id, user);

        if (NotificationTypeEnum.REPLY_COMMENT.getType() == notificationDto.getType() ||
                NotificationTypeEnum.REPLY_QUESTION.getType() == notificationDto.getType()) {
            return "redirect:/question/" + notificationDto.getOuterId();
        } else {
            return "redirect:/";
        }
    }
}
