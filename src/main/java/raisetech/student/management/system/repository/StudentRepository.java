package raisetech.student.management.system.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import raisetech.student.management.system.data.Student;
import raisetech.student.management.system.data.StudentCourse;

/**
 * 受講生情報を扱うリポジトリ。
 *
 * 受講生、コースの情報の検索ができるクラスです。
 */

@Mapper
public interface StudentRepository {

  @Select("SELECT * FROM students")
  List<Student> searchStudent();

  @Select("SELECT * FROM students_courses")
  List<StudentCourse> searchCourse();

  @Insert("INSERT INTO students(fullname,nickname,age,gender,mailaddress,area,remark)"
      + " VALUES(#{fullname},#{nickname},#{age},#{gender},#{mailaddress},#{area},#{remark}")
  void insertStudent(Student student);
}
