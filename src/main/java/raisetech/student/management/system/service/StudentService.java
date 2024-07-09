package raisetech.student.management.system.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.student.management.system.data.Student;
import raisetech.student.management.system.data.StudentCourse;
import raisetech.student.management.system.repository.StudentRepository;

@Service
public class StudentService {

  private StudentRepository repository;

  @Autowired
  public StudentService(StudentRepository repository) {
    this.repository = repository;
  }

  /**
   * 20代の受講生を抽出
   *
   * @return　20代の受講生の情報一覧
   */
  public List<Student> searchStudentList() {
    return repository.searchStudent().stream()
        .filter(student -> student.getAge() >= 20 && student.getAge() < 30)
        .collect(Collectors.toList());
  }

  /**
   * Javaコースの情報を抽出
   *
   * @return　Javaコースの情報一覧
   */
  public List<StudentCourse> searchCourseList() {
    return repository.searchCourse().stream()
        .filter(student -> student.getCourseName().equals("Java"))
        .collect(Collectors.toList());
  }

}
