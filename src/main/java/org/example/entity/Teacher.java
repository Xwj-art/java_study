package org.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO: 老师简介这样的信息需要添加
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("teacher")
public class Teacher {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String department;
    private String title;
}