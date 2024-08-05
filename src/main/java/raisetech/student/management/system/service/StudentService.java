package raisetech.student.management.system.service;

import java.time.LocalDate;
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
   * 受講生詳細の一覧検索です。 全件検索を行うので条件指定は行いません。
   *
   * @return　受講生詳細（一覧）
   */
  public List<StudentDetail> searchStudentList() {
    List<Student> studentList = repository.searchStudents();
    List<StudentCourse> studentCoursesList = repository.searchCourses();
    return converter.convertStudentDetails(studentList, studentCoursesList);
  }

  /**
   * is_deleted=true(論理削除)のデータ一覧です。
   *
   * @return　退会した受講生詳細(一覧)
   */
  public List<StudentDetail> searchDeletedStudentList() {
    List<Student> studentList = repository.searchDeleteStudents();
    List<StudentCourse> studentCourseList = repository.searchCourses();
    return converter.convertStudentDetails(studentList, studentCourseList);
  }

  /**
   * 受講生詳細の登録を行います。 受講生と受講コース情報を個別に登録し、受講生コース情報には受講生情報と紐付ける為の値と受講開始日およびサポート期限を設定します。
   *
   * @param studentDetail
   * @return 登録情報を付与した受講生詳細
   */
  @Transactional
  public StudentDetail registerStudent(StudentDetail studentDetail) {
    Student student = studentDetail.getStudent();

    repository.insertStudent(student);
    studentDetail.getStudentCourseList().forEach(studentCourse -> {
      initStudentsCourse(studentCourse, student);
      repository.insertCourse(studentCourse);
    });
    return studentDetail;
  }

  /**
   * 受講生コース情報を登録する際の初期設定
   *
   * @param studentCourse
   * @param student
   */
  private void initStudentsCourse(StudentCourse studentCourse, Student student) {
    studentCourse.setStudentId(student.getStudentId());
    studentCourse.setDateStart(LocalDate.now());
    studentCourse.setDateFinish(LocalDate.now().plusYears(1));
  }

  /**
   * 受講生詳細の単一検索です。 student_idに紐づく受講生情報を取得したあと、その受講生に紐づくコース情報を取得します。
   *
   * @param studentId(受講生id)
   * @return　受講生情報の詳細
   */
  public StudentDetail searchStudentById(int studentId) {
    Student student = repository.searchStudentById(studentId);
    List<StudentCourse> studentCourseList = repository.searchCourseById(student.getStudentId());
    return new StudentDetail(student, studentCourseList);
  }

  /**
   * 受講生詳細の更新を行います。 受講生と受講生コース情報をそれぞれ更新します。
   *
   * @param studentDetail
   */
  @Transactional
  public void updateStudent(StudentDetail studentDetail) {
    repository.updateStudent(studentDetail.getStudent());
    studentDetail.getStudentCourseList()
        .forEach(studentCourse -> repository.updateCourse(studentCourse));
  }
}
