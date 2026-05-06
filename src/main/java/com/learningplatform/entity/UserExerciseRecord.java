package com.learningplatform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("user_exercise_record")
public class UserExerciseRecord {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private Long exerciseId;
    private String userAnswer;
    private Boolean isCorrect;
    private LocalDateTime createTime;
}