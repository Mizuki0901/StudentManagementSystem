package raisetech.student.management.system.data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentCourse {

  @NotNull
  private int courseId;

  @NotBlank
  private int studentId;

  @NotBlank
  private String courseName;

  private LocalDate dateStart;

  private LocalDate dateFinish;

}
