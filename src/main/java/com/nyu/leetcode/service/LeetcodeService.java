package com.nyu.leetcode.service;

import com.nyu.leetcode.domain.dto.UserDailySubmission;

/**
 * @author :zixiaotong
 * @date :Created in 2021/8/10 17:38
 */
public interface LeetcodeService {
    UserDailySubmission querySubmissionByUser(String username);
}
