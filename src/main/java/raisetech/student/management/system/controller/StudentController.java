package raisetech.student.management.system.controller;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import raisetech.student.management.system.domain.StudentDetail;
import raisetech.student.management.system.exception.TestException;
import raisetech.student.management.system.service.StudentService;

/**
 * 受講生の検索や登録、更新などを行うREST APIとして実行されるControllerです。
 */
@Validated
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
   * 受講生詳細の一覧検索です。 全件検索を行うので条件指定は行わないです。
   *
   * @return　受講生詳細一覧
   */
  @GetMapping("/studentList")
  public List<StudentDetail> getStudentList() {
    return service.searchStudentList();
  }

  /**
   * 全件検索でTestExceptionを発生させるAPIです。
   */
  @GetMapping("/allStudents")
  public List<StudentDetail> getAllStudent() throws TestException {
    throw new TestException("URLは「studentList」を利用してください。");
  }

  /**
   * 論理削除された受講生を検索し詳細の一覧を表示します。
   *
   * @return 退会した受講生詳細一覧
   */
  @GetMapping("/deletedStudentList")
  public List<StudentDetail> getDeleteStudentList() {
    return service.searchDeletedStudentList();
  }

  /**
   * 受講生検索です。 student_idに紐づく受講生の情報を取得します。
   *
   * @param studentId(受講生id)
   * @return　受講生詳細
   */
  @GetMapping("/student/{studentId}")
  public StudentDetail getStudent(@PathVariable int studentId) throws TestException {
    return service.searchStudentById(studentId);
  }

  /**
   * 受講生詳細の新規登録を行います。
   *
   * @param studentDetail
   * @return 実行結果
   */
  @PostMapping("/registerStudent")
  public ResponseEntity<String> registerStudent(@RequestBody @Valid StudentDetail studentDetail) {
    StudentDetail responseStudentDetail = service.registerStudent(studentDetail);
    return ResponseEntity.ok("新規登録が完了しました");
  }

  /**
   * 受講生詳細の更新処理を行います。 退会フラグ（論理削除）の更新もここで行います。
   *
   * @param studentDetail
   * @return　メッセージ
   */
  @PutMapping("/updateStudent")
  public ResponseEntity<String> updateStudent(@RequestBody @Valid StudentDetail studentDetail) {
    service.updateStudent(studentDetail);
    return ResponseEntity.ok("更新処理が成功しました");
  }
}
