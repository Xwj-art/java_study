package org.example.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// 选课记录，中间表
// TODO: 需要加入选课时间
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("enrollment")
public class Enrollment {
    private Integer id;
    private Integer teacher_id;
    private Integer student_id;
    private Integer course_id;

    private Integer semester;
    // 每个学生对应一则成绩，可以为空，到了考试结束才填写
    private Float grade;

    public Enrollment(Integer student_id, Integer course_id) {
        this.student_id = student_id;
        this.course_id = course_id;

        LocalDate now = LocalDate.now();
        int year = now.getYear();
        int month = now.getMonthValue();

        // 逻辑示例：2-7月为春季学期(01)，8-1月为秋季学期(02)
        int term = (month >= 2 && month <= 7) ? 1 : 2;

        // 如果是1月份，逻辑上可能属于上一年的秋季学期
        if (month == 1) {
            year -= 1;
        }
        this.semester = year * 100 + term;
    }
}
