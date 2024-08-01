package raisetech.student.management.system.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
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
   * studentsテーブルのうちis_deleted=falseのデータ。
   *
   * @return　一覧をリストにして表示
   */

  @Select("SELECT * FROM students WHERE is_deleted = 0")
  List<Student> searchStudent();

  /**
   * students_coursesテーブルの全件取得
   *
   * @return　一覧をリストで表示
   */
  @Select("SELECT * FROM students_courses")
  List<StudentCourse> searchCourse();

  /**
   * studentsテーブルのうちis_deleted=trueのデータ。
   *
   * @return　一覧をリストにして表示
   */

  @Select("SELECT * FROM students WHERE is_deleted = 1")
  List<Student> searchDeleteStudent();

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

  /**
   * student_idから受講生の情報を表示
   */
  @Select("SELECT * FROM students WHERE student_id = #{studentId}")
  Student findStudentById(@Param("studentId") int studentId);

  /**
   * student_idからコース情報を表示
   */
  @Select("SELECT * FROM students_courses WHERE student_id = #{studentId}")
  List<StudentCourse> findCourseById(@Param("studentId") int studentId);

  /**
   * studentsテーブルの情報を更新
   *
   * @param student
   */
  @Update(
      "UPDATE students SET fullname=#{fullname}, furigana=#{furigana}, nickname=#{nickname}, age=#{age}, mailaddress=#{mailaddress},"
          + "area=#{area}, remark=#{remark}, is_deleted=#{isDeleted} WHERE student_id=#{studentId}")
  void updateStudent(Student student);

  /**
   * students_coursesテーブルの情報を更新
   */
  @Update(
      "UPDATE students_courses SET course_id=#{courseId}, course_name=#{courseName}, date_start=#{dateStart},"
          + "date_finish=#{dateFinish} WHERE course_id=#{courseId}")
  void updateCourse(StudentCourse studentCourse);

}
