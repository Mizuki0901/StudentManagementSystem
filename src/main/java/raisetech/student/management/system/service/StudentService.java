package raisetech.student.management.system.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

  public List<Student> searchStudentList() {
    return repository.searchStudent();
  }

  public List<StudentCourse> searchCourseList() {
    return repository.searchCourse();
  }

  @Transactional
  public void insertStudents(Student student, StudentCourse studentCourse) {
    repository.insertStudent(student);
    studentCourse.setStudentId(student.getStudentId());
    repository.insertCourse(studentCourse);
  }
}
