package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.entity.Enrollment;
import org.example.pojo.vo.CourseOfStudentVO;

@Mapper
public interface EnrollmentMapper extends BaseMapper<Enrollment> {
    // 使用IPage，将原先返回List的结果，封装成对象
    // 包含当前页数据、总条数 (Total)、每页大小 (Size)、当前页码
    // 为了使用Page，第一个参数需要是IPage
    @Select("select t.name as teacherName,\n" +
            "       c.name as courseName, \n" +
            "       c.credits as courseCredits,\n" +
            "       e.grade as grade, \n" +
            "       e.semester as enrollmentSemester\n" +
            "from enrollment e \n" +
            "join teacher t on e.teacher_id = t.id\n" +
            "join course c on e.course_id = c.id\n" +
            "where e.student_id = #{studentId}")
    IPage<CourseOfStudentVO> getStudentCoursePage(IPage<CourseOfStudentVO> page, @Param("studentId") Integer studentId);
}
