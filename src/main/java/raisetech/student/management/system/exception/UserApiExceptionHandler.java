package raisetech.student.management.system.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserApiExceptionHandler {

  /**
   * オブジェクトクラスで設定したバリデーションの例外処理をユーザーから見えるところに表示させます。
   *
   * @param ex
   * @return　エラーレスポンス
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException ex) {
    List<Map<String, String>> errors = new ArrayList<>();
    ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
      Map<String, String> error = new HashMap<>();
      error.put("field", fieldError.getField());
      error.put("message", fieldError.getDefaultMessage());
      errors.add(error);
    });
    ErrorResponse errorResponse =
        new ErrorResponse(HttpStatus.BAD_REQUEST, "validation error", errors);
    return ResponseEntity.badRequest().body(errorResponse);
  }

  /**
   * TestExceptionが発生した場合のハンドリングです。
   *
   * @param ex
   * @return エラーメッセージ
   */
  @ExceptionHandler(TestException.class)
  public ResponseEntity<String> handleTestException(TestException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  /**
   * ResourceNotFoundExceptionが発生した場合のハンドリングです。
   *
   * @param ex
   * @return
   */
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<String> handleNotFoundException(ResourceNotFoundException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
  }

  /**
   * エラーレスポンスのクラス
   */
  public static final class ErrorResponse {

    private final HttpStatus status;
    private final String message;
    private final List<Map<String, String>> errors;

    public ErrorResponse(HttpStatus status, String message, List<Map<String, String>> errors) {
      this.status = status;
      this.message = message;
      this.errors = errors;
    }

    public HttpStatus getStatus() {
      return status;
    }

    public String getMessage() {
      return message;
    }

    public List<Map<String, String>> getErrors() {
      return errors;
    }

  }
}
