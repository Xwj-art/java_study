package org.example.controller;

import org.example.common.Result;
import org.example.entity.Student;
import org.example.mapper.StudentMapper;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student") // 统一路径为student
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping("/all")
    public Result<List<Student>> getAllStudent(){
        List<Student> s = studentService.list();
        return Result.success(s);
    }
    @GetMapping("/{id}")
    public Result<Student> getStudentById(@PathVariable String id){
        Student s = studentService.getById(id);
        return Result.success(s);
    }
    @PostMapping("/add")
    public Result addStudent(@RequestBody Student student){
        boolean flag = studentService.save(student);
        return flag?Result.success(true):Result.fail(500);
    }
}
