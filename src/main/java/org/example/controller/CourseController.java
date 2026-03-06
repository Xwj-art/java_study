package org.example.controller;

import org.example.entity.Course;
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
    public Result<Boolean> addCourse(@RequestBody Course course) {
        return Result.success(courseService.save(course));
    }

    @DeleteMapping("/del")
    public Result<Boolean> deleteCourse(@RequestParam Integer id) {
        return Result.success(courseService.removeById(id));
    }

    @PostMapping("/update")
    public Result<Boolean> updateCourse(@RequestBody Course course) {
        return Result.success(courseService.updateById(course));
    }

    @GetMapping("/search")
    public Result<Course> searchCourse(@RequestParam Integer id) {
        return Result.success(courseService.getById(id));
    }
}