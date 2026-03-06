package org.example.controller;

import org.example.utils.Result;
import org.example.entity.Student;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @PostMapping("/add")
    public Result<Boolean> addByStudent(@RequestBody Student student) {
        return Result.success(studentService.save(student));
    }
    @DeleteMapping("/del")
    public Result<Boolean> deleteById(@RequestParam Integer id) {
        return Result.success(studentService.removeById(id));
    }
    @PostMapping("/update")
    public Result<Boolean> updateByStudent(@RequestBody Student student) {
        return Result.success(studentService.updateById(student));
    }
    @GetMapping("/search")
    public Result<Student> getById(@RequestParam Integer id) {
        return Result.success(studentService.getById(id));
    }
}