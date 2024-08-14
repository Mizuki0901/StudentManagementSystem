package raisetech.student.management.system.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
  List<Student> searchStudents();

  /**
   * studentsテーブルのうちis_deleted=trueのデータを検索します。
   *
   * @return　退会した受講生情報(一覧)
   */
  List<Student> searchDeleteStudents();

  /**
   * @param studentId(受講生id)
   * @return 受講生情報(単一)
   */
  Student searchStudentById(@Param("studentId") int studentId);

  /**
   * students_coursesテーブルの全件取得を行います。
   *
   * @return　受講生コース情報(全件)
   */
  List<StudentCourse> searchCourses();

  /**
   * student_idに紐づくコース情報を検索します。
   *
   * @param studentId(受講生id)
   * @return 受講生idに紐づくコース情報
   */
  List<StudentCourse> searchCourseById(@Param("studentId") int studentId);

  /**
   * studentsテーブルに新規受講生情報を登録します。 idは自動採番を行う
   *
   * @param student
   */
  void insertStudent(Student student);

  /**
   * students_coursesテーブルに新規受講生コース情報を登録します。 コースidは自動採番
   *
   * @param studentCourse
   */
  void insertCourse(StudentCourse studentCourse);

  /**
   * 受講生情報を更新します。
   *
   * @param student
   */
  void updateStudent(Student student);

  /**
   * 受講生コース情報のコース名を更新します。
   */
  void updateCourse(StudentCourse studentCourse);

}
