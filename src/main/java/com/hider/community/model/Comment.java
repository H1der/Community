package com.hider.community.model;

public class Comment {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.id
     *
     * @mbg.generated Thu Jul 25 19:10:51 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.parent_id
     *
     * @mbg.generated Thu Jul 25 19:10:51 CST 2019
     */
    private Integer parentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.content
     *
     * @mbg.generated Thu Jul 25 19:10:51 CST 2019
     */
    private String content;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.type
     *
     * @mbg.generated Thu Jul 25 19:10:51 CST 2019
     */
    private Integer type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.comment_count
     *
     * @mbg.generated Thu Jul 25 19:10:51 CST 2019
     */
    private Integer commentCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.commentator
     *
     * @mbg.generated Thu Jul 25 19:10:51 CST 2019
     */
    private Integer commentator;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.gmt_create
     *
     * @mbg.generated Thu Jul 25 19:10:51 CST 2019
     */
    private Long gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.gmt_modified
     *
     * @mbg.generated Thu Jul 25 19:10:51 CST 2019
     */
    private Long gmtModified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.like_count
     *
     * @mbg.generated Thu Jul 25 19:10:51 CST 2019
     */
    private Integer likeCount;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.id
     *
     * @return the value of comment.id
     *
     * @mbg.generated Thu Jul 25 19:10:51 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.id
     *
     * @param id the value for comment.id
     *
     * @mbg.generated Thu Jul 25 19:10:51 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.parent_id
     *
     * @return the value of comment.parent_id
     *
     * @mbg.generated Thu Jul 25 19:10:51 CST 2019
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.parent_id
     *
     * @param parentId the value for comment.parent_id
     *
     * @mbg.generated Thu Jul 25 19:10:51 CST 2019
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.content
     *
     * @return the value of comment.content
     *
     * @mbg.generated Thu Jul 25 19:10:51 CST 2019
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.content
     *
     * @param content the value for comment.content
     *
     * @mbg.generated Thu Jul 25 19:10:51 CST 2019
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.type
     *
     * @return the value of comment.type
     *
     * @mbg.generated Thu Jul 25 19:10:51 CST 2019
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.type
     *
     * @param type the value for comment.type
     *
     * @mbg.generated Thu Jul 25 19:10:51 CST 2019
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.comment_count
     *
     * @return the value of comment.comment_count
     *
     * @mbg.generated Thu Jul 25 19:10:51 CST 2019
     */
    public Integer getCommentCount() {
        return commentCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.comment_count
     *
     * @param commentCount the value for comment.comment_count
     *
     * @mbg.generated Thu Jul 25 19:10:51 CST 2019
     */
    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.commentator
     *
     * @return the value of comment.commentator
     *
     * @mbg.generated Thu Jul 25 19:10:51 CST 2019
     */
    public Integer getCommentator() {
        return commentator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.commentator
     *
     * @param commentator the value for comment.commentator
     *
     * @mbg.generated Thu Jul 25 19:10:51 CST 2019
     */
    public void setCommentator(Integer commentator) {
        this.commentator = commentator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.gmt_create
     *
     * @return the value of comment.gmt_create
     *
     * @mbg.generated Thu Jul 25 19:10:51 CST 2019
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.gmt_create
     *
     * @param gmtCreate the value for comment.gmt_create
     *
     * @mbg.generated Thu Jul 25 19:10:51 CST 2019
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.gmt_modified
     *
     * @return the value of comment.gmt_modified
     *
     * @mbg.generated Thu Jul 25 19:10:51 CST 2019
     */
    public Long getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.gmt_modified
     *
     * @param gmtModified the value for comment.gmt_modified
     *
     * @mbg.generated Thu Jul 25 19:10:51 CST 2019
     */
    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.like_count
     *
     * @return the value of comment.like_count
     *
     * @mbg.generated Thu Jul 25 19:10:51 CST 2019
     */
    public Integer getLikeCount() {
        return likeCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.like_count
     *
     * @param likeCount the value for comment.like_count
     *
     * @mbg.generated Thu Jul 25 19:10:51 CST 2019
     */
    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }
}