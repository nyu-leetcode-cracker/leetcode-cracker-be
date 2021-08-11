package com.nyu.leetcode.domain.entity;

import lombok.Data;

/**
 * @author :zixiaotong
 * @date :Created in 2021/8/10 17:48
 */
@Data
public class Question{
    private String questionFrontendId;
    private String title;
    private String translatedTitle;
    private String titleSlug;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Question question = (Question) o;

        return questionFrontendId != null ? questionFrontendId.equals(question.questionFrontendId) : question.questionFrontendId == null;
    }

    @Override
    public int hashCode() {
        return questionFrontendId != null ? questionFrontendId.hashCode() : 0;
    }
}
