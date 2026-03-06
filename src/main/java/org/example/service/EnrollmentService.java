package org.example.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.entity.Enrollment;
import org.example.entity.Student;

public interface EnrollmentService extends IService<Enrollment> {
    public Boolean enrollCourse(Integer studentId, Integer courseId);
}
