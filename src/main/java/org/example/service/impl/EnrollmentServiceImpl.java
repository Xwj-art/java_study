package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.entity.Course;
import org.example.entity.Enrollment;
import org.example.entity.Student;
import org.example.mapper.CourseMapper;
import org.example.mapper.EnrollmentMapper;
import org.example.mapper.StudentMapper;
import org.example.service.EnrollmentService;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentServiceImpl extends ServiceImpl<EnrollmentMapper, Enrollment> implements EnrollmentService {
    @Autowired
    private EnrollmentMapper enrollmentMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public Boolean enrollCourse(Integer studentId, Integer courseId) {
        // 判定传来的参数是否有误
        if (studentMapper.selectById(studentId) == null ||
        courseMapper.selectById(courseId) == null) {
            return false;
        }
        /*
         * 判定学生能否选课
         * 1. 不能重复选课
         * 2. 不能超过容量（capacity）
         */
        QueryWrapper<Enrollment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id", studentId).eq("course_id", courseId);
        Long enrollCount = enrollmentMapper.selectCount(queryWrapper);
        if (enrollCount != 0) {
            return false;
        }

        QueryWrapper<Enrollment> countQueryWrapper = new QueryWrapper<>();
        countQueryWrapper.eq("course_id", courseId);
        Course course = courseMapper.selectById(courseId);
        if (enrollmentMapper.selectCount(countQueryWrapper) >= course.getCapacity()) {
            return false;
        }

        // 如果都过了，那就是能抢课，插入记录，容量-1
        enrollmentMapper.insert(new Enrollment(studentId, courseId, 0));
        UpdateWrapper<Course> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("course_id", courseId).set("capacity", course.getCapacity()-1);
        courseMapper.update(updateWrapper);
        return true;
    }
}