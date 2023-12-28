package School;

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
    String data = "[";
    data += "학번" + "\t" + "학생이름" + "\t" + "학생아이디";
    data += "]";
    String data1 = data + "\n" + studentNo + "\t" + stuName + "\t" + stuId;
    return data1;
  }

  String saveToData() {
    return "%d/%s/%s\n".formatted(studentNo,stuName,stuId);
  }
}
