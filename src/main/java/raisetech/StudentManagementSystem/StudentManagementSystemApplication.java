package raisetech.StudentManagementSystem;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController

public class StudentManagementSystemApplication {


  public static void main(String[] args) {
    SpringApplication.run(StudentManagementSystemApplication.class, args);
  }

  Map<String, String> students = new HashMap<>();

  @GetMapping("/studentInfo")
  public Map<String,String> getStudentInfo() {
    return students;
  }

  @PostMapping("/add")
  public void setStudentInfo(Student student) {
    students.put(student.getName(), student.getAge());
  }
}
