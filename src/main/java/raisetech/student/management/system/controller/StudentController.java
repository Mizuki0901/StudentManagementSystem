package raisetech.student.management.system.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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


  @GetMapping("/studentList")
  public List<StudentDetail> getStudentList() {
    List<Student> students = service.searchStudentList();
    List<StudentCourse> studentsCourses = service.searchCourseList();

    return converter.convertStudentDetails(students, studentsCourses);
  }

  @GetMapping("/courseList")
  public List<StudentCourse> getCourseList() {
    return service.searchCourseList();
  }

}
