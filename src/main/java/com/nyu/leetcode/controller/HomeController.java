package com.nyu.leetcode.controller;

import com.nyu.leetcode.domain.vo.R;
import org.springframework.web.bind.annotation.GetMapping;
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
public class HomeController {

    @GetMapping
    public R<String> home() {
        return R.success("demo");
    }
}
