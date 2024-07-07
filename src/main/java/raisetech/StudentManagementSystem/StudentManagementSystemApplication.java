package raisetech.StudentManagementSystem;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController

public class StudentManagementSystemApplication {

  @Autowired
  private StudentRepository studentRepository;
  private StudentCourseRepository courseRepository;

  public static void main(String[] args) {
    SpringApplication.run(StudentManagementSystemApplication.class, args);
  }

  @GetMapping("/studentList")
  public List<Student> getStudentList() {
    return studentRepository.searchStudent();
  }

  @GetMapping("/courseList")
  public List<StudentCourse> getCourseList() {
    return courseRepository.searchCourse();
  }
}
