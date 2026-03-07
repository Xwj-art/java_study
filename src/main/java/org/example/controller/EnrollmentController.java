package org.example.controller;

import org.example.entity.Enrollment;
import org.example.exception.BusinessException;
import org.example.service.EnrollmentService;
import org.example.utils.Result;
import org.example.utils.ThrowUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentService;
    @PostMapping("/enroll")
    public Result<Boolean> studentEnrollCourse(@RequestParam Integer studentId, @RequestParam Integer courseId) {
        return Result.success(enrollmentService.enrollCourse(studentId, courseId));
    }
    @DeleteMapping("/del")
    public Result<Boolean> delEnrollment(@RequestParam Integer studentId, @RequestParam Integer courseId) {
        return Result.success(enrollmentService.cancelEnrollment(studentId, courseId));
    }

    @PostMapping("/update")
    public Result<Boolean> updateEnrollment(@RequestBody Enrollment enrollment) {
        ThrowUtils.throwIf(enrollmentService.updateById(enrollment), 400, "更新选课信息失败");
        return Result.success();
    }

    @GetMapping("/search")
    public Result<Enrollment> searchEnrollment(@RequestParam Integer id) {
        Enrollment enrollment = enrollmentService.getById(id);
        ThrowUtils.throwIf(enrollment == null, new BusinessException("未找到选课记录", 400));
        return Result.success(enrollment);
    }
}
