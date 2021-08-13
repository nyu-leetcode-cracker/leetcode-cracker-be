package com.nyu.leetcode.service.impl;

import cn.hutool.core.io.IoUtil;
import cn.hutool.json.JSONUtil;
import com.nyu.leetcode.base.constant.ZonedTimeConstants;
import com.nyu.leetcode.domain.dto.UserDailySubmission;
import com.nyu.leetcode.domain.entity.Question;
import com.nyu.leetcode.domain.entity.Submission;
import com.nyu.leetcode.service.LeetcodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author :zixiaotong
 * @date :Created in 2021/8/10 17:41
 */
@Service
@Slf4j
public class LeetcodeServiceImpl implements LeetcodeService {

    /**
     * 提交状态为通过的标识
     */
    private static final String ACCEPTED = "A_10";
    /**
     * Node.js的路径
     */
    @Value("${node.path:node}")
    private String nodePath;

    /**
     * node脚本的路径
     */
    @Value("${node.script:node/test.js}")
    private String scriptPath;

    @Value("${problems.target:3}")
    private Integer problemsShouldBeFinished;

    @Override
    public UserDailySubmission querySubmissionByUser(String username) {
        String jsonStr = querySubmissionWithNodeJs(username);
        List<Submission> submissions = convertResponseToEntity(jsonStr);
        return getSubmissionStatus(username, submissions);
    }

    private UserDailySubmission getSubmissionStatus(String username, List<Submission> submissions) {
        UserDailySubmission dailySubmission = new UserDailySubmission();
        dailySubmission.setUsername(username);
        dailySubmission.setProblemsShouldBeFinished(problemsShouldBeFinished);
        dailySubmission.setTimestamp(LocalDateTime.now());

        if (CollectionUtils.isEmpty(submissions)) {
            dailySubmission.setProblemsFinished(0);
            dailySubmission.setQuestions(new LinkedList<>());
            return dailySubmission;
        }

        List<Question> questions =
                submissions
                        .stream()
                        .filter(this::isAcceptedSubmissionToday)
                        .map(Submission::getQuestion)
                        .distinct()
                        .collect(Collectors.toList());
        dailySubmission.setQuestions(questions);
        dailySubmission.setProblemsFinished(questions.size());
        return dailySubmission;
    }

    private boolean isAcceptedSubmissionToday(Submission sub) {
        ZonedDateTime start = ZonedDateTime.now(ZonedTimeConstants.NEW_YORK_TIMEZONE).truncatedTo(ChronoUnit.DAYS);
        return sub.getSubmitDateTime().isAfter(start)
                       && ACCEPTED.equals(sub.getStatus());
    }

    private List<Submission> convertResponseToEntity(String jsonStr) {
        return JSONUtil
                       .parseObj(jsonStr)
                       .getJSONObject("data")
                       .getJSONArray("recentSubmissions")
                       .toList(Submission.class);
    }

    private String querySubmissionWithNodeJs(String username) {
        File file = new File(scriptPath);
        if (!file.exists()) {
            log.error("node file not exist");
            return "文件不存在";
        }

        String command = String.format("%s %s \"%s\"", nodePath, scriptPath, username);
        Runtime rt = Runtime.getRuntime();
        Process process;
        String result = "";
        try {
            process = rt.exec(command);
            result = IoUtil.read(process.getInputStream(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.error("fetch submission failed", e);
        }
        return result;
    }
}
