package org.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.entity.Enrollment;
import org.example.pojo.vo.CourseOfStudentVO;

public interface EnrollmentService extends IService<Enrollment> {
    Boolean enrollCourse(Integer studentId, Integer courseId);
    Boolean cancelEnrollment(Integer studentId, Integer courseId);
    IPage<CourseOfStudentVO> getStudentCoursePage(int current, int size, Integer studentId);
}
