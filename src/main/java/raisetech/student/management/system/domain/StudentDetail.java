package raisetech.student.management.system.domain;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import raisetech.student.management.system.data.Student;
import raisetech.student.management.system.data.StudentCourse;

@Getter
@Setter
public class StudentDetail {

  private Student student;
  private List<StudentCourse> studentCourses;

}
