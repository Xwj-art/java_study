package org.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO: 学生和老师需要添加密码信息、邮箱、手机号
// TODO: 学生信息和老师共有信息属性抽象出来，使用继承
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("student")
public class Student {
    @TableId(type=IdType.AUTO)
    Integer id;
    String name;
    Integer age;
}