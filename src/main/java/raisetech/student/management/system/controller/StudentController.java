package raisetech.student.management.system.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
   * @return　受講生情報の一覧を表示させる
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
   * 新規登録画面を表示
   *
   * @param model
   * @return　登録処理
   */

  @GetMapping("/newStudent")
  public String newStudent(Model model) {
    model.addAttribute("studentDetail", new StudentDetail());
    return "registerStudent";
  }

  /**
   * 新規登録処理
   *
   * @param studentDetail
   * @param result
   * @return　再度一覧を表示
   */

  @PostMapping("/registerStudent")
  public String registerStudent(@ModelAttribute StudentDetail studentDetail, BindingResult result) {
    if (result.hasErrors()) {
      return "registerStudent";
    }
    service.insertStudents(studentDetail.getStudent(),
        studentDetail.getStudentCourses().getFirst());
    return "redirect:/studentList";

  }

  /**
   * クリックされた名前のstudent_idに該当する情報の更新画面を表示
   *
   * @param studentId
   * @param model
   * @return　更新処理
   */

  @GetMapping("/students/{studentId}")
  public String setStudent(@PathVariable int studentId, Model model) {
    StudentDetail studentDetail = service.getStudentById(studentId);
    model.addAttribute("studentDetail", studentDetail);
    return "updateStudent";
  }

  @PostMapping("/updateStudent")
  public ResponseEntity<String> updateStudent(@RequestBody StudentDetail studentDetail) {
    service.updateStudents(studentDetail);
    return ResponseEntity.ok("更新処理が成功しました");
  }
}
