package School_유민석;

public class Subject {
  int stuNo;
  String subName;
  int score;

  public Subject(int stuNo, String subName, int score) {
    this.stuNo = stuNo;
    this.subName = subName;
    this.score = score;
  }

  @Override
  public String toString() {
    String data = "[";
    data += "%d, %s, %d".formatted(stuNo, subName, score);
    data += "]";
    return data;
  }

  String saveToData() {
    return "%d/%s/%d\n".formatted(stuNo,subName,score);
  }
}
