package com.ivy.domain.store;

/**
 * QuestionItem
 *
 * @Author: ivy
 * @CreateTime: 2021-06-28
 */
public class QuestionItem {
    private String id;
    private String questionId;
    private String content;
    private String picture;
    private String isRight;

//    private Question question;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getIsRight() {
        return isRight;
    }

    public void setIsRight(String isRight) {
        this.isRight = isRight;
    }
}
