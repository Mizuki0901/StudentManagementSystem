package raisetech.student.management.system.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.student.management.system.data.Student;
import raisetech.student.management.system.data.StudentCourse;
import raisetech.student.management.system.domain.StudentDetail;
import raisetech.student.management.system.repository.StudentRepository;

@Service
public class StudentService {

  private StudentRepository repository;

  @Autowired
  public StudentService(StudentRepository repository) {
    this.repository = repository;
  }

  public List<Student> searchStudentList() {
    return repository.searchStudent();
  }

  public List<StudentCourse> searchCourseList() {
    return repository.searchCourse();
  }

  /**
   * 入力された情報をDBに登録させる
   *
   * @param student
   * @param studentCourse
   */

  @Transactional
  public void insertStudents(Student student, StudentCourse studentCourse) {
    repository.insertStudent(student);
    studentCourse.setStudentId(student.getStudentId());
    repository.insertCourse(studentCourse);
  }

  /**
   * URLのstudent_idへ来たリクエストから、該当のデータをstudentDetailにセット
   *
   * @param studentId
   * @return　該当するstudent_idの受講生と受講コースの情報
   */

  public StudentDetail getStudentById(int studentId) {
    Student student = repository.findStudentById(studentId);
    List<StudentCourse> studentCourseList = repository.findCourseById(studentId);
    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(student);
    studentDetail.setStudentCourses(studentCourseList);
    return studentDetail;
  }

  /**
   * 入力された受講生情報の更新
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
