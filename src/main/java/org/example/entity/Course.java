package org.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 课程信息
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("course")
public class Course {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;

    private Float credits;

    private String description;
    private Integer capacity;
}