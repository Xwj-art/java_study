package org.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("teacher")
public class Teacher extends BaseUser{
    private String workNo;                 // 工号
    private String title;                  // 职称
    private String officeLocation;         // 办公室
    private String description;            // 简介
}