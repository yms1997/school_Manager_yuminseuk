package School;

import java.util.ArrayList;
import java.util.Random;

public class SubjectDAO {
  ArrayList<Subject> subList;
  Util ut;
  int cnt;

  SubjectDAO(){ut = new Util();}

  void addSubjectsFromData(String data){ // 로드된 파일 다시 분할해서 배열에 넣기
    String[] temp = data.split("\n");
    subList = new ArrayList<Subject>(temp.length);
    cnt = temp.length;
    for (int i = 0; i < temp.length; i++) {
      String[] info = temp[i].split("/");
      subList.add(new Subject(Integer.parseInt(info[0]), info[1], Integer.parseInt(info[2])));
    }
  }

  String saveAsFileData() { // 파일저장
    if(cnt == 0) return "";
    String data = "";
    for(Subject sub : subList) {
      data += sub.saveToData();
    }
    return data;
  }

  Subject findSubNameBysubList(Student stu, String name){
    if(subList == null) return null;
    for (Subject s : subList) {
      if(stu.studentNo == s.stuNo && name.equals(s.subName)){
        return s;
      }
    }
    return null;
  }

  int getSubIdxBySubject(Subject sub) {
    for(int i = 0; i < cnt; i+=1) {
      if(sub == subList.get(i)) {
        return i;
      }
    }
    return -1;
  }

  void deleteAllSubjectsInOneStudent(Student stu) {
    if(cnt == 0) return;
    for(int i = 0; i < cnt; i+=1) {
      if(stu.studentNo == subList.get(i).stuNo) {
        subList.remove(subList.get(i));
        i--;
        cnt -= 1;
      }
    }
  }

  void addOneSubjectToOneStudent(Student stu){
    Random rd = new Random();

    String name = ut.getValue("[추가] 과목이름");
    Subject subject = findSubNameBysubList(stu, name);
    if(subject != null){
      System.out.println("해당 과목은 이미 있습니다");
      return;
    }
    subList.add(new Subject(stu.studentNo, name, rd.nextInt(51) + 50));
    cnt += 1;
    System.out.println("과목 추가 완료");
  }

  void deleteOneSubjectToOneStudent(Student stu){
    if(cnt == 0) return;
    String name = ut.getValue("[삭제] 과목이름");
    Subject subject = findSubNameBysubList(stu, name);
    if(subject == null) {
      System.out.println("해당 과목은 없는 과목입니다");
      return;
    }
    subList.remove(getSubIdxBySubject(subject));
    cnt -= 1;
    System.out.println("과목 삭제 완료");
  }

  double getAvgSubScoreByStudent(Student stu){
    double score = 0;
    if(cnt == 0) return 0;
    int count = 0;
    for (Subject s : subList) {
      if(stu.studentNo == s.stuNo){
        score += s.score;
        count += 1;
      }
    }
    return score * 1.0 / count;
  }

  void getStuListByOneSubject(StudentDAO stuDAO) {

    String name = ut.getValue("찾을 과목 이름");
    int cnt = 0;
    for(Subject sub : subList) {
      if(sub.subName.equals(name)) {
        cnt += 1;
      }
    }
    if(cnt == 0) {
      System.out.println("해당 과목은 학생 데이터가 없습니다");
      return;
    }

    ArrayList<Integer> stuList = new ArrayList<Integer>(cnt);
    for(Subject sub : subList) {
      if(sub.subName.equals(name)) {
        stuList.add(sub.stuNo);
      }
    }

    stuDAO.printStudentByOneSubject(stuList);
  }
}
