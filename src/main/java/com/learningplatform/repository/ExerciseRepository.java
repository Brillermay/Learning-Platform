package com.learningplatform.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.learningplatform.entity.Exercise;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface ExerciseRepository extends BaseMapper<Exercise> {

    List<Exercise> selectExercisesByKnowledge(@Param("knowledgeId") Long knowledgeId);

    List<Exercise> selectExercisesByUserProgress(@Param("userId") Long userId,
                                                 @Param("status") String status);

    int updateExerciseStatistics(@Param("exerciseId") Long exerciseId,
                                 @Param("isCorrect") Boolean isCorrect);

    @Select("SELECT e.* FROM exercise e " +
            "LEFT JOIN user_exercise_record r ON e.id = r.exercise_id AND r.user_id = #{userId} " +
            "WHERE e.deleted = 0 AND r.is_correct = 0")
    List<Exercise> findWrongExercisesByUserId(@Param("userId") Long userId);

}