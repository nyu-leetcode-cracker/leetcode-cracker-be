package com.nyu.leetcode;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import org.junit.jupiter.api.Test;

/**
 * @author :zixiaotong
 * @date :Created in 2021/8/11 18:35
 */
public class UtilTests {
    @Test
    public void timeToDate(){
        DateTime date = DateUtil.date(1628653463L*1000);
        System.out.println(date.toString("yyyy-MM-dd hh:mm:ss"));
    }
}
