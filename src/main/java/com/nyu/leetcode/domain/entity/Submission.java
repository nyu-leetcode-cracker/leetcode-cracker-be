package com.nyu.leetcode.domain.entity;

import cn.hutool.core.date.DateUtil;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author :zixiaotong
 * @date :Created in 2021/8/10 17:49
 */
@Data
public class Submission {
    private String status;
    private String lang;
    private Question question;
    private Long submitTime;

    public LocalDateTime getSubmitDateTime() {
        return DateUtil.toLocalDateTime(DateUtil.date(submitTime * 1000));
    }
}
