package raisetech.student.management.system.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
import raisetech.student.management.system.exception.ResourceNotFoundException;
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

  @Operation(summary = "受講生一覧", description = "受講生の一覧を取得します。")
  @GetMapping("/studentList")
  public List<StudentDetail> getStudentList() {
    return service.searchStudentList();
  }

  @Operation(summary = "使用していないURL", responses = {
      @ApiResponse(responseCode = "400", description = "TestExceptionを発生させるAPIです。",
          content = @Content(mediaType = "text/plain", schema = @Schema(implementation = Exception.class)))})
  @GetMapping("/allStudents")
  public List<StudentDetail> getAllStudent() throws TestException {
    throw new TestException("URLは「studentList」を利用してください。");
  }

  @Operation(summary = "退会者一覧", description = "論理削除された受講生を検索し、詳細の一覧を取得します。")
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
  @Operation(summary = "受講生単一検索", responses = {
      @ApiResponse(responseCode = "200", description = "idに紐づく受講生情報を取得します。"),
      @ApiResponse(responseCode = "404", description = "idに紐づく受講生情報が存在しない場合はエラーメッセージが表示されます。",
          content = @Content(mediaType = "text/plain", schema = @Schema(implementation = Exception.class)))}
  )
  @GetMapping("/student/{studentId}")
  public StudentDetail getStudent(@PathVariable int studentId) throws ResourceNotFoundException {
    return service.searchStudentById(studentId);
  }

  /**
   * 受講生詳細の新規登録を行います。
   *
   * @param studentDetail
   * @return 実行結果
   */
  @Operation(summary = "受講生登録", description = "受講生の新規登録を行います。", responses = {
      @ApiResponse(responseCode = "200", description = "新規登録が完了したメッセージを表示します。"),
      @ApiResponse(responseCode = "400", description = "入力内容に不備がある場合にエラーメッセージを表示します。",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class)))})
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
  @Operation(summary = "受講生情報の更新", description = "受講生情報の更新を行います。退会フラグ（論理削除）の更新もここで行います。")
  @PutMapping("/updateStudent")
  public ResponseEntity<String> updateStudent(@RequestBody @Valid StudentDetail studentDetail) {
    service.updateStudent(studentDetail);
    return ResponseEntity.ok("更新処理が成功しました");
  }
}
