package org.example.controller;

import org.example.entity.Teacher;
import org.example.service.TeacherService;
import org.example.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @PostMapping("/add")
    public Result<Boolean> addTeacher(@RequestBody Teacher teacher) {
        return Result.success(teacherService.save(teacher));
    }

    @DeleteMapping("/del")
    public Result<Boolean> deleteTeacher(@RequestParam Integer id) {
        return Result.success(teacherService.removeById(id));
    }

    @PostMapping("/update")
    public Result<Boolean> updateTeacher(@RequestBody Teacher teacher) {
        return Result.success(teacherService.updateById(teacher));
    }

    @GetMapping("/search")
    public Result<Teacher> searchTeacher(@RequestParam Integer id) {
        return Result.success(teacherService.getById(id));
    }
}