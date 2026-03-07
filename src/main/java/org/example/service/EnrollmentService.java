package org.example.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.entity.Enrollment;
import org.example.entity.Student;
import org.example.pojo.vo.CourseOfStudentVO;

public interface EnrollmentService extends IService<Enrollment> {
    public Boolean enrollCourse(Integer studentId, Integer courseId);
    public Boolean cancelEnrollment(Integer studentId, Integer courseId);
    public IPage<CourseOfStudentVO> getStudentCoursePage(int current, int size, Integer studentId);
}
