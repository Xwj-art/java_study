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
    @PostMapping("/add")
    public Result<Boolean> addEnrollmentacher(@RequestBody Enrollment enrollment) {
        return Result.success(enrollmentService.save(enrollment));
    }

    @DeleteMapping("/del")
    public Result<Boolean> deleteEnrollmentacher(@RequestParam Integer id) {
        return Result.success(enrollmentService.removeById(id));
    }

    @PostMapping("/update")
    public Result<Boolean> updateEnrollmentacher(@RequestBody Enrollment enrollment) {
        return Result.success(enrollmentService.updateById(enrollment));
    }

    @GetMapping("/search")
    public Result<Enrollment> searchEnrollment(@RequestParam Integer id) {
        return Result.success(enrollmentService.getById(id));
    }

    @PostMapping("/enroll")
    public Result<Boolean> studentEnrollCourse(@RequestParam Integer studentId, @RequestParam Integer courseId) {
        return Result.success(enrollmentService.enrollCourse(studentId, courseId));
    }
}
