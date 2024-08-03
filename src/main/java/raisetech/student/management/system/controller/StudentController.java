package raisetech.student.management.system.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import raisetech.student.management.system.domain.StudentDetail;
import raisetech.student.management.system.service.StudentService;

/**
 * 受講生の検索や登録、更新などを行うREST APIとして実行されるControllerです。
 */
@RestController
public class StudentController {

  private StudentService service;

  /**
   * コンストラクタ
   *
   * @param service
   */
  @Autowired
  public StudentController(StudentService service) {
    this.service = service;
  }

  /**
   * 受講生一覧検索です。 全件検索を行うので条件指定は行わないです。
   *
   * @return　受講生情報の一覧
   */
  @GetMapping("/studentList")
  public List<StudentDetail> getStudentList() {
    return service.searchStudentList();
  }

  /**
   * 論理削除された受講生を検索し一覧を表示します。
   *
   * @return 退会した受講生一覧
   */
  @GetMapping("/deletedStudentList")
  public List<StudentDetail> getDeleteStudentList() {
    return service.searchDeletedStudentList();
  }

  /**
   * 受講生検索です。 student_idに紐づく受講生の情報を取得します。
   *
   * @param studentId(受講生id)
   * @return　受講生情報
   */
  @GetMapping("/student/{studentId}")
  public StudentDetail getStudent(@PathVariable int studentId) {
    return service.searchStudentById(studentId);
  }

  /**
   * 新規登録処理
   *
   * @param studentDetail
   */
  @PostMapping("/registerStudent")
  public ResponseEntity<String> registerStudent(@RequestBody StudentDetail studentDetail) {
    StudentDetail responseStudentDetail = service.registerStudent(studentDetail);
    return ResponseEntity.ok("新規登録が完了しました");
  }

  /**
   * 更新処理
   *
   * @param studentDetail
   * @return　メッセージ
   */
  @PostMapping("/updateStudent")
  public ResponseEntity<String> updateStudent(@RequestBody StudentDetail studentDetail) {
    service.updateStudent(studentDetail);
    return ResponseEntity.ok("更新処理が成功しました");
  }
}
