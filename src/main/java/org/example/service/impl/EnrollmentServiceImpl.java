package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.entity.Course;
import org.example.entity.Enrollment;
import org.example.exception.BusinessException;
import org.example.mapper.CourseMapper;
import org.example.mapper.EnrollmentMapper;
import org.example.mapper.StudentMapper;
import org.example.pojo.vo.CourseOfStudentVO;
import org.example.service.EnrollmentService;
import org.example.utils.ThrowUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnrollmentServiceImpl extends ServiceImpl<EnrollmentMapper, Enrollment> implements EnrollmentService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private CourseMapper courseMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean enrollCourse(Integer studentId, Integer courseId) {
        // 判定传来的参数是否有误
        if (studentMapper.selectById(studentId) == null) {
            throw new BusinessException("学生信息不存在", 400);
        }
        if (courseMapper.selectById(courseId) == null) {
            throw new BusinessException("课程信息不存在", 400);
        }
        /*
         * 判定学生能否选课
         * 1. 不能重复选课
         * 2. 不能超过容量（capacity）
         */
        QueryWrapper<Enrollment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id", studentId).eq("course_id", courseId);

        ThrowUtils.throwIf(baseMapper.selectCount(queryWrapper) != 0, 400, "你已经选过这门课了");

        QueryWrapper<Enrollment> countQueryWrapper = new QueryWrapper<>();
        countQueryWrapper.eq("course_id", courseId);
        Course course = courseMapper.selectById(courseId);

        ThrowUtils.throwIf(baseMapper.selectCount(countQueryWrapper) >= course.getCapacity(), 400, "课程容量已满");

        // 如果都过了，那就是能抢课，插入记录，容量-1
        baseMapper.insert(new Enrollment(studentId, courseId));

        UpdateWrapper<Course> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("course_id", courseId).set("capacity", course.getCapacity()-1);
        courseMapper.update(updateWrapper);

        return true;
    }

    @Override
    public Boolean cancelEnrollment(Integer studentId, Integer courseId) {
        /*
         * 退选课程，判定学生是否有选课，有的话才能删除
         * 若删除的话，需要将enrollment信息删除掉，并释放
         * course的capacity，容量+1
         */
        ThrowUtils.throwIf(studentMapper.selectById(studentId) == null, 400, "学生信息不存在");
        ThrowUtils.throwIf(courseMapper.selectById(courseId) == null, 400, "课程信息不存在");

        QueryWrapper<Enrollment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id", studentId).eq("course_id", courseId);
        // 找到这条记录，并删除
        baseMapper.delete(queryWrapper);
        // 增加容量
        UpdateWrapper<Course> updateWrapper = new UpdateWrapper<>();
        Course  course = courseMapper.selectById(courseId);
        updateWrapper.eq("course_id", courseId).set("capacity", course.getCapacity()-1);
        courseMapper.update(updateWrapper);
        return true;
    }

    @Override
    public IPage<CourseOfStudentVO> getStudentCoursePage(int current, int size, Integer studentId) {
        IPage<CourseOfStudentVO> page = new Page<>(current, size);
        return baseMapper.getStudentCoursePage(page, studentId);
    }
}