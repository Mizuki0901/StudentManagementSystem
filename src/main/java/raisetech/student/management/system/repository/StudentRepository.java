package raisetech.student.management.system.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import raisetech.student.management.system.data.Student;
import raisetech.student.management.system.data.StudentCourse;

/**
 * 受講生情報を扱うリポジトリ。
 * <p>
 * 受講生、コースの情報の検索ができるクラスです。
 */

@Mapper
public interface StudentRepository {

  /**
   * studentsテーブルの全件取得。
   *
   * @return　一覧をリストにして表示
   */

  @Select("SELECT * FROM students")
  List<Student> searchStudent();

  /**
   * students_coursesテーブルの全件取得
   *
   * @return　一覧をリストで表示
   */

  @Select("SELECT * FROM students_courses")
  List<StudentCourse> searchCourse();

  /**
   * studentsテーブルに新規データを登録
   *
   * @param student
   */

  @Insert(
      "INSERT INTO students VALUES(#{studentId}, #{fullname}, #{furigana}, #{nickname}, #{age}, #{gender}, "
          + "#{mailaddress}, #{area}, #{remark}, #{isDeleted})")
  @Options(useGeneratedKeys = true, keyProperty = "studentId", keyColumn = "student_id")
  void insertStudent(Student student);

  /**
   * students_coursesテーブルに新規データを登録
   */

  @Insert("INSERT INTO students_courses VALUES(#{courseId}, #{studentId}, #{courseName}, #{dateStart}, #{dateFinish})")
  @Options(useGeneratedKeys = true, keyProperty = "courseId", keyColumn = "course_id")
  void insertCourse(StudentCourse studentCourse);

}
