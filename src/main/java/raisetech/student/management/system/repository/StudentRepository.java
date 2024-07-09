package raisetech.student.management.system.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
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
   * studentsテーブルの全件取得をします。
   *
   * @return　受講生情報の一覧
   */
  @Select("SELECT * FROM students")
  List<Student> searchStudent();

  /**
   * students_coursesテーブルの全件取得をします。
   *
   * @return　受講生のコース情報の一覧
   */
  @Select("SELECT * FROM students_courses")
  List<StudentCourse> searchCourse();

}
