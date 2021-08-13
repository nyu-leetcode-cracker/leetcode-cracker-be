package com.nyu.leetcode.base.utils;

import com.nyu.leetcode.base.constant.ZonedTimeConstants;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author :zixiaotong
 * @date :Created in 2021/8/12 16:24
 */
public class ZonedTimeUtils {

    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy/MM/dd").withZone(ZonedTimeConstants.NEW_YORK_TIMEZONE);

    public static String toNewYorkDate(LocalDateTime time) {
        return DTF.format(time);
    }
}
