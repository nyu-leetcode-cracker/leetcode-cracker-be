package com.nyu.leetcode.domain.dto;

import com.nyu.leetcode.domain.entity.Question;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author :zixiaotong
 * @date :Created in 2021/8/11 17:50
 */
@Data
public class UserDailySubmission {
    private String username;
    private Integer problemsFinished;
    private Integer problemsShouldBeFinished;
    private LocalDateTime timestamp;
    private List<Question> questions;

    public Boolean getFinished() {
        return problemsFinished >= problemsShouldBeFinished;
    }
}
