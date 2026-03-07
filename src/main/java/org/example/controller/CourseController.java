package org.example.controller;

import org.example.entity.Course;
import org.example.exception.BusinessException;
import org.example.service.CourseService;
import org.example.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class CourseController {
    @Autowired
    private CourseService courseService;
    @PostMapping("/add")
    public Result<Void> addCourse(@RequestBody Course course) {
        boolean success = courseService.save(course);
        if (!success) {
            throw new BusinessException("增课程失败", 400);
        }
        return Result.success();
    }

    @DeleteMapping("/del")
    public Result<Boolean> deleteCourse(@RequestParam Integer id) {
        boolean success = courseService.removeById(id);
        if (!success) {
            throw new BusinessException("删课程失败", 400);
        }
        return Result.success();
    }

    @PostMapping("/update")
    public Result<Boolean> updateCourse(@RequestBody Course course) {
        boolean success = courseService.updateById(course);
        if (!success) {
            throw new BusinessException("删课程失败", 400);
        }
        return Result.success();
    }

    @GetMapping("/search")
    public Result<Course> searchCourse(@RequestParam Integer id) {
        Course course = courseService.getById(id);
        if (course == null) {
            throw new BusinessException("未找到课程信息", 400);
        }
        return Result.success(course);
    }
}