package raisetech.StudentManagementSystem;

import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentCourse {

  private String courseId;
  private String studentId;
  private String courseName;
  private Date dateStart;
  private Date dateFinish;

}
