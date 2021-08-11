package com.nyu.leetcode.controller;

import com.nyu.leetcode.domain.dto.UserDailySubmission;
import com.nyu.leetcode.domain.vo.R;
import com.nyu.leetcode.service.LeetcodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Home
 *
 * @author :zixiaotong
 * @date :Created in 2021/8/10 15:44
 */
@RestController
@RequestMapping
@RequiredArgsConstructor
public class HomeController {

    private final LeetcodeService leetcodeService;

    @GetMapping
    public R<String> home() {
        return R.success("demo");
    }

    @GetMapping("submission/{username}")
    public R<UserDailySubmission> querySubmission(@PathVariable String username) {
        return R.success(leetcodeService.querySubmissionByUser(username));
    }
}
