package raisetech.student.management.system.data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "受講生コース情報")
@Getter
@Setter
public class StudentCourse {

  @NotNull
  private int courseId;

  @NotNull
  private int studentId;

  @NotBlank
  private String courseName;

  private LocalDate dateStart;

  private LocalDate dateFinish;

}
