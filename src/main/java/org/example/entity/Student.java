package org.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("student")
public class Student extends BaseUser {
    private String studentNo;           // 学号
    private LocalDate enrollmentDate;   // 入学日期
    private String major;               // 专业
    private Integer classId;            // 班级ID

    public String getGradeName() {
        int year = LocalDate.now().getYear() - enrollmentDate.getYear() + 1;
        return switch (year) {
            case 1 -> "Freshman (大一)";
            case 2 -> "Sophomore (大二)";
            case 3 -> "Junior (大三)";
            case 4 -> "Senior (大四)";
            default -> "Graduated (已毕业)";
        };
    }
}