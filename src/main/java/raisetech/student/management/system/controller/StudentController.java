package raisetech.student.management.system.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import raisetech.student.management.system.data.Student;
import raisetech.student.management.system.data.StudentCourse;
import raisetech.student.management.system.service.StudentService;

@RestController
public class StudentController {

  private StudentService service;

  @Autowired
  public StudentController(StudentService service) {
    this.service = service;
  }

  /**
   * studentsテーブルを検索する
   *
   * @return　リストで表示
   */
  @GetMapping("/studentList")
  public List<Student> getStudentList() {
    return service.searchStudentList();
  }

  /**
   * students_coursesテーブルを検索する
   *
   * @return　リストで表示
   */
  @GetMapping("/courseList")
  public List<StudentCourse> getCourseList() {
    return service.searchCourseList();
  }

}
