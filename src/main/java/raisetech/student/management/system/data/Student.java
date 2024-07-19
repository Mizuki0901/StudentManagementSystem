package raisetech.student.management.system.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Student {

  private int studentId;
  private String fullname;
  private String furigana;
  private String nickname;
  private int age;
  private String gender;
  private String mailaddress;
  private String area;
  private String remark;
  private boolean isDeleted;
}
