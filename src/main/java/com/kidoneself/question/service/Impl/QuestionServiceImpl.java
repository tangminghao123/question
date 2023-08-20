package com.kidoneself.question.service.Impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kidoneself.aio.common.core.base.R;
import com.kidoneself.question.mapper.QuestionMapper;
import com.kidoneself.question.modle.dto.QuestionDto;
import com.kidoneself.question.modle.entity.Question;
import com.kidoneself.question.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {


    @Resource
    private QuestionMapper questionMapper;


    @Override
    public R<?> getQuestion(Integer id) {
        Question question = questionMapper.selectById(id);
        if (BeanUtil.isEmpty(question)) {
            return R.failed("获取题目失败，请刷新重试");
        }
        QuestionDto questionDto = new QuestionDto();
        BeanUtil.copyProperties(question, questionDto);
        return R.ok(questionDto);
    }

    @Override
    public R<?> getQuestionList(Integer limit) {
        int month = DateUtil.thisMonth() + 1;
        List<QuestionDto> questions = questionMapper.getQuestionList(limit, month);
        return R.ok(questions);
    }

}
