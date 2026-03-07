package org.example.controller;

import org.example.entity.Enrollment;
import org.example.service.EnrollmentService;
import org.example.utils.Result;
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
        return Result.success(enrollmentService.updateById(enrollment));
    }

    @GetMapping("/search")
    public Result<Enrollment> searchEnrollment(@RequestParam Integer id) {
        return Result.success(enrollmentService.getById(id));
    }
}
