package com.hider.community.community.dto;

import lombok.Data;

@Data
public class GithubUser {
    private String name;
    private Integer id;
    private String bio;
    private String avatar_url;
}
