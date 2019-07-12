package com.hider.community.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDto {
    private List<QuestionDto> questions;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;

    /**
     * 文章数除每页显示数等于0时直接显示结果为页数,不为0的时候结果页数+1页
     *
     * @param totalCount
     * @param page
     * @param size
     */
    public void setPagination(Integer totalPage, Integer page) {

        this.totalPage = totalPage;
        this.page = page;

        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }

            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }

        //当前页为第一页的时候,上一页按钮不显示
        if (page == 1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }

        //当前页为最后一页时,最后一页按钮不显示
        if (page == totalPage) {
            showNext = false;
        } else {
            showNext = true;
        }

        //如果当前为第一页,那么去首页的按钮不展示
        if (pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }

        //如果当前为最后一页,那么去最后页的按钮不展示
        if (pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }
    }
}
