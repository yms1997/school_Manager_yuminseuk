package School_유민석;

public class Student {
  int studentNo;
  String stuId;
  String stuName;

  public Student(int studentNo, String stuName, String stuId) {
    this.studentNo = studentNo;
    this.stuName = stuName;
    this.stuId = stuId;
  }

  @Override
  public String toString() {
    return studentNo + "\t" +  stuName + "\t" + stuId;
  }

  String saveToData() {
    return "%d/%s/%s\n".formatted(studentNo,stuName,stuId);
  }
}
