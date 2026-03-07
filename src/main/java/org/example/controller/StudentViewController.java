package org.example.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.example.pojo.vo.CourseOfStudentVO;
import org.example.service.EnrollmentService;
import org.example.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student/view")
public class StudentViewController {
    @Autowired
    EnrollmentService enrollmentService;

    @GetMapping("/my-course")
    public Result<IPage<CourseOfStudentVO>> getMyCourse(@RequestParam(defaultValue = "1") int current,
                                                        @RequestParam(defaultValue = "10") int size,
                                                        @RequestParam Integer studentId) {
        IPage<CourseOfStudentVO> page = enrollmentService.getStudentCoursePage(current, size, studentId);
        return Result.success(page);
    }
}
