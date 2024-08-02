package raisetech.student.management.system.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import raisetech.student.management.system.controller.converter.StudentConverter;
import raisetech.student.management.system.data.Student;
import raisetech.student.management.system.data.StudentCourse;
import raisetech.student.management.system.domain.StudentDetail;
import raisetech.student.management.system.service.StudentService;

@RestController
public class StudentController {

  private StudentService service;
  private StudentConverter converter;

  @Autowired
  public StudentController(StudentService service, StudentConverter converter) {
    this.service = service;
    this.converter = converter;
  }

  /**
   * @return　受講生情報の一覧
   */

  @GetMapping("/studentList")
  public List<StudentDetail> getStudentList() {
    List<Student> students = service.searchStudentList();
    List<StudentCourse> studentsCourses = service.searchCourseList();
    return converter.convertStudentDetails(students, studentsCourses);
  }

  /**
   * @return 退会した受講生一覧
   */

  @GetMapping("/deletedStudentList")
  public List<StudentDetail> getDeleteStudentList() {
    List<Student> students = service.deleteStudentList();
    List<StudentCourse> studentsCourses = service.searchCourseList();
    return converter.convertStudentDetails(students, studentsCourses);
  }

  /**
   * 指定されたstudent_idに該当する情報を表示
   *
   * @param studentId
   * @return　受講生情報
   */

  @GetMapping("/student/{studentId}")
  public StudentDetail setStudent(@PathVariable int studentId) {
    return service.getStudentById(studentId);
  }

  /**
   * 新規登録処理
   *
   * @param studentDetail
   */

  @PostMapping("/registerStudent")
  public ResponseEntity<StudentDetail> registerStudent(@RequestBody StudentDetail studentDetail) {
    StudentDetail responseStudentDetail = service.insertStudents(studentDetail);
    return ResponseEntity.ok(responseStudentDetail);
  }

  /**
   * 更新処理
   *
   * @param studentDetail
   * @return　メッセージ
   */

  @PostMapping("/updateStudent")
  public ResponseEntity<String> updateStudent(@RequestBody StudentDetail studentDetail) {
    service.updateStudents(studentDetail);
    return ResponseEntity.ok("更新処理が成功しました");
  }

}
