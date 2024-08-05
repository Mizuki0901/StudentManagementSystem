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
 * students(受講生)テーブルとstudents_courses(受講コース)テーブルに紐づくRepositoryです。
 */
@Mapper
public interface StudentRepository {

  /**
   * studentsテーブルのうちis_deleted=falseのデータを検索します。
   *
   * @return　現在在籍する受講生情報(一覧)
   */
  @Select("SELECT * FROM students WHERE is_deleted = 0")
  List<Student> searchStudents();

  /**
   * studentsテーブルのうちis_deleted=trueのデータを検索します。
   *
   * @return　退会した受講生情報(一覧)
   */
  @Select("SELECT * FROM students WHERE is_deleted = 1")
  List<Student> searchDeleteStudents();

  /**
   * @param studentId(受講生id)
   * @return 受講生情報(単一)
   */
  @Select("SELECT * FROM students WHERE student_id = #{studentId}")
  Student searchStudentById(@Param("studentId") int studentId);

  /**
   * students_coursesテーブルの全件取得を行います。
   *
   * @return　受講生コース情報(全件)
   */
  @Select("SELECT * FROM students_courses")
  List<StudentCourse> searchCourses();

  /**
   * student_idに紐づくコース情報を検索します。
   *
   * @param studentId(受講生id)
   * @return 受講生idに紐づくコース情報
   */
  @Select("SELECT * FROM students_courses WHERE student_id = #{studentId}")
  List<StudentCourse> searchCourseById(@Param("studentId") int studentId);

  /**
   * studentsテーブルに新規受講生情報を登録します。 idは自動採番を行う
   *
   * @param student
   */
  @Insert(
      "INSERT INTO students(fullname,furigana,nickname,age,gender,mailaddress,area,remark,is_deleted)"
          + " VALUES( #{fullname}, #{furigana}, #{nickname}, #{age}, #{gender}, "
          + "#{mailaddress}, #{area}, #{remark}, false)")
  @Options(useGeneratedKeys = true, keyProperty = "studentId", keyColumn = "student_id")
  void insertStudent(Student student);

  /**
   * students_coursesテーブルに新規受講生コース情報を登録します。 コースidは自動採番
   *
   * @param studentCourse
   */
  @Insert("INSERT INTO students_courses(student_id,course_name,date_start)"
      + " VALUES( #{studentId}, #{courseName}, #{dateStart})")
  @Options(useGeneratedKeys = true, keyProperty = "courseId", keyColumn = "course_id")
  void insertCourse(StudentCourse studentCourse);

  /**
   * 受講生情報を更新します。
   *
   * @param student
   */
  @Update(
      "UPDATE students SET fullname=#{fullname}, furigana=#{furigana}, nickname=#{nickname}, age=#{age}, mailaddress=#{mailaddress},"
          + "area=#{area}, remark=#{remark}, is_deleted=#{isDeleted} WHERE student_id=#{studentId}")
  void updateStudent(Student student);

  /**
   * 受講生コース情報のコース名を更新します。
   */
  @Update(
      "UPDATE students_courses SET course_id=#{courseId}, course_name=#{courseName}, date_start=#{dateStart},"
          + "date_finish=#{dateFinish} WHERE course_id=#{courseId}")
  void updateCourse(StudentCourse studentCourse);

}
