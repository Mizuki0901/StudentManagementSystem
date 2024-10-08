package raisetech.student.management.system.data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "受講生")
@Getter
@Setter

public class Student {

  @NotNull
  private int studentId;

  @NotBlank
  private String fullname;

  @NotBlank
  @Pattern(regexp = "^[\\u3040-\\u309F]+$", message = "ふりがなはひらがなで入力してください")
  private String furigana;

  @NotBlank
  private String nickname;

  @NotNull
  private int age;

  @NotBlank
  private String gender;

  @NotBlank
  private String mailaddress;

  @NotBlank
  private String area;

  private String remark;

  private boolean isDeleted;
}
