package raisetech.student.management.system.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.student.management.system.controller.converter.StudentConverter;
import raisetech.student.management.system.data.Student;
import raisetech.student.management.system.data.StudentCourse;
import raisetech.student.management.system.domain.StudentDetail;
import raisetech.student.management.system.repository.StudentRepository;

/**
 * 受講生の検索や登録、更新などを行うREST APIとして受け付けるControllerです。
 */
@Service
public class StudentService {

  private StudentRepository repository;
  private StudentConverter converter;

  @Autowired
  public StudentService(StudentRepository repository, StudentConverter converter) {
    this.repository = repository;
    this.converter = converter;
  }

  /**
   * 受講生の一覧検索です。 全件検索を行うので条件指定は行いません。
   *
   * @return　受講生情報（一覧）
   */
  public List<StudentDetail> searchStudentList() {
    List<Student> studentList = repository.searchStudent();
    List<StudentCourse> studentsCoursesList = repository.searchCourse();
    return converter.convertStudentDetails(studentList, studentsCoursesList);
  }

  /**
   * is_deleted=true(論理削除)のデータ一覧です。
   *
   * @return　退会した受講生情報(一覧)
   */
  public List<StudentDetail> deleteStudentList() {
    List<Student> students = repository.searchDeleteStudent();
    List<StudentCourse> studentsCourses = repository.searchCourse();
    return converter.convertStudentDetails(students, studentsCourses);
  }

  /**
   * 新規登録
   *
   * @return 登録した内容
   */
  @Transactional
  public StudentDetail insertStudents(StudentDetail studentDetail) {
    repository.insertStudent(studentDetail.getStudent());
    for (StudentCourse studentCourse : studentDetail.getStudentCourses()) {
      studentCourse.setStudentId(studentDetail.getStudent().getStudentId());
      repository.insertCourse(studentCourse);
    }
    return studentDetail;
  }

  /**
   * 受講生情報の単一検索です。 student_idに紐づく受講生情報を取得したあと、その受講生に紐づくコース情報を取得します。
   *
   * @param studentId(受講生id)
   * @return　受講生と受講コースの情報
   */
  public StudentDetail getStudentById(int studentId) {
    Student student = repository.findStudentById(studentId);
    List<StudentCourse> studentCourseList = repository.findCourseById(student.getStudentId());
    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(student);
    studentDetail.setStudentCourses(studentCourseList);
    return studentDetail;
  }

  /**
   * 受講生情報の更新
   *
   * @param studentDetail
   */
  @Transactional
  public void updateStudents(StudentDetail studentDetail) {
    repository.updateStudent(studentDetail.getStudent());
    for (StudentCourse studentCourse : studentDetail.getStudentCourses()) {
      repository.updateCourse(studentCourse);
    }
  }
}
