package com.nyu.leetcode.domain.entity;

import cn.hutool.core.date.DateUtil;
import com.nyu.leetcode.base.constant.ZonedTimeConstants;
import lombok.Data;

import java.time.ZonedDateTime;

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

    public ZonedDateTime getSubmitDateTime() {
        return ZonedDateTime.ofInstant(DateUtil.date(submitTime * 1000).toInstant(), ZonedTimeConstants.SHANGHAI_TIMEZONE);
    }
}
