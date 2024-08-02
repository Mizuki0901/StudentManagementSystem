package raisetech.student.management.system.controller.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import raisetech.student.management.system.data.Student;
import raisetech.student.management.system.data.StudentCourse;
import raisetech.student.management.system.domain.StudentDetail;

/**
 * 受講生情報とコース情報を受講生詳細に変換するコンバーターです。 その逆の変換も可能です。
 */
@Component
public class StudentConverter {

  /**
   * 受講生に紐づくコース情報をマッピングします。 コース情報は一人の受講生に対して複数存在するので、ループして受講生詳細情報を組み立てます。
   *
   * @param students       　受講生一覧
   * @param studentCourses 　コース情報のリスト
   * @return 受講生詳細情報のリスト
   */
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
