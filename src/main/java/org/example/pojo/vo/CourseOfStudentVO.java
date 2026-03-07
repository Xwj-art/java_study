package org.example.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/*
 * 开始实现返回课表的功能，要求可以根据学生的学号，
 * 返回学生课程列表
 * 每个课程需要有：任课老师、学分、成绩、课程名以及开设学期
 */
@Data
@AllArgsConstructor
public class CourseOfStudentVO {
    // 返回的是课程列表，列表涉及到分页，分页在config配置
    private String teacherName;

    private String courseName;
    private Float courseCredits;

    private Float grade;
    private Float enrollmentSemester;
}
