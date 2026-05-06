package com.learningplatform.controller;

import com.learningplatform.common.ApiResponse;
import com.learningplatform.dto.*;

import com.learningplatform.service.ExerciseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/exercise")
@Validated
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    // 获取习题列表
    @GetMapping("/list")
    public ApiResponse<ExerciseListResponse> getExerciseList(@Valid ExerciseQueryRequest request) {
        ExerciseListResponse response = exerciseService.getExerciseList(request);
        return ApiResponse.success(response);
    }

    // 获取习题详情
    @GetMapping("/{id}")
    public ApiResponse<ExerciseDetailResponse> getExerciseDetail(@PathVariable Long id) {
        ExerciseDetailResponse response = exerciseService.getExerciseDetail(id);
        return ApiResponse.success(response);
    }

    // 创建习题
    @PostMapping
    public ApiResponse<Long> createExercise(@Valid @RequestBody CreateExerciseRequest request) {
        Long exerciseId = exerciseService.createExercise(request);
        return ApiResponse.success(exerciseId);
    }

    // 更新习题
    @PutMapping("/{id}")
    public ApiResponse<Boolean> updateExercise(@PathVariable Long id,
                                               @Valid @RequestBody UpdateExerciseRequest request) {
        request.setId(id);
        Boolean result = exerciseService.updateExercise(request);
        return ApiResponse.success(result);
    }

    // 删除习题
    @DeleteMapping("/{id}")
    public ApiResponse<Boolean> deleteExercise(@PathVariable Long id) {
        Boolean result = exerciseService.deleteExercise(id);
        return ApiResponse.success(result);
    }

    // 提交答案
    @PostMapping("/submit")
    public ApiResponse<SubmitResultResponse> submitAnswer(@Valid @RequestBody SubmitAnswerRequest request) {
        // 从认证信息获取用户ID
        Long userId = getCurrentUserId();
        SubmitResultResponse response = exerciseService.submitAnswer(request, userId);
        return ApiResponse.success(response);
    }

    // 获取错题列表
    @GetMapping("/wrong")
    public ApiResponse<ExerciseListResponse> getWrongExercises(@Valid ExerciseQueryRequest request) {
        Long userId = getCurrentUserId();
        ExerciseListResponse response = exerciseService.getWrongExercises(userId, request);
        return ApiResponse.success(response);
    }

    // 根据知识点获取习题
    @GetMapping("/knowledge/{knowledgeId}")
    public ApiResponse<List<ExerciseResponse>> getExercisesByKnowledge(@PathVariable Long knowledgeId) {
        List<ExerciseResponse> response = exerciseService.getExercisesByKnowledge(knowledgeId);
        return ApiResponse.success(response);
    }

    private Long getCurrentUserId() {
        // 从安全上下文中获取当前用户ID
        // 实际项目中需要集成Spring Security
        return 1L;  // 临时返回固定用户ID
    }
}