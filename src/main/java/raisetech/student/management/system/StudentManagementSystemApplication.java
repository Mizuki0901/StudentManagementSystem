package raisetech.student.management.system;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import raisetech.student.management.system.data.Student;
import raisetech.student.management.system.data.StudentCourse;
import raisetech.student.management.system.repository.StudentRepository;

@SpringBootApplication
@RestController

public class StudentManagementSystemApplication {


  public static void main(String[] args) {
    SpringApplication.run(StudentManagementSystemApplication.class, args);
  }

}
