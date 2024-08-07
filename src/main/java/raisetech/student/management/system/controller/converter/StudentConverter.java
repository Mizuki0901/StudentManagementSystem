package raisetech.student.management.system.controller.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import raisetech.student.management.system.data.Student;
import raisetech.student.management.system.data.StudentCourse;
import raisetech.student.management.system.domain.StudentDetail;

@Component
public class StudentConverter {

  //converter
  public List<StudentDetail> convertStudentDetails(List<Student> students,
      List<StudentCourse> studentCourses) {
    List<StudentDetail> studentDetails = new ArrayList<>();
    students.forEach(student -> {
      StudentDetail studentDetail = new StudentDetail();
      studentDetail.setStudent(student);

      List<StudentCourse> convertStudentCourse = studentCourses.stream()
          .filter(studentCourse -> student.getStudentId() == studentCourse.getStudentId())
          .collect(Collectors.toList());
      studentDetail.setStudentCourses(convertStudentCourse);
      studentDetails.add(studentDetail);
    });
    return studentDetails;
  }
}
