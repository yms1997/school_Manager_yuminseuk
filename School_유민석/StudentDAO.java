package School_유민석;

import java.util.ArrayList;

public class StudentDAO {
  ArrayList<Student> stuList;
  Util ut;
  int maxNo;
  int cnt;
  StudentDAO(){
    ut = new Util();
    maxNo = 1001;
  }

  void addStudentfromData(String data){ // 로드된 파일 다시 분할해서 배열에 넣기
      String[] temp = data.split("\n");
      stuList = new ArrayList<Student>(temp.length);
      cnt = temp.length;
    for (int i = 0; i < temp.length; i++) {
      String[] info = temp[i].split("/");
      stuList.add(new Student(Integer.parseInt(info[0]), info[1], info[2]));
    }
  }

  String saveAsFileData() { // 파일 저장
    if(cnt == 0) return "";
    String data = "";
    for(Student s : stuList) {
      data += s.saveToData();
    }
    return data;
  }

  Student getOneStudentById(String id) { // 아이디중복검사
    if(cnt == 0) return null;
    for(Student stu : stuList) {
      if(stu.stuId.equals(id)) {
        return stu;
      }
    }
    return null;
  }

  boolean hasStudentData(){ // 데이터 확인
    if(stuList == null){
      System.out.println("데이터가 존재하지 않습니다");
      return true;
    }
    return false;
  }

  void updateMaxStuNo() { // 학생번호 최신화
    if(cnt == 0) return;
    int maxNo = 0;
    for(Student s : stuList) {
      if(maxNo < s.studentNo) {
        maxNo = s.studentNo;
      }
    }
    this.maxNo = maxNo;
  }

  Student findOneStudentByStuNum() { // 학번찾기
    int num = ut.getValue("학번", 1001, maxNo);
    for(Student stu : stuList) {
      if(num == stu.studentNo) {
        return stu;
      }
    }
    return null;
  }

  void addOneStudent(){ // 학생추가
    String id = ut.getValue("[추가] id");
    Student s = getOneStudentById(id);
    if(s != null){
      System.out.println("중복된 아이디 입니다");
      return;
    }
    String name = ut.getValue("[추가] 이름");
    stuList.add(new Student(++maxNo, name, id));
    cnt += 1;
    System.out.println("학생 추가 완료!");
  }

  void deleteOneStudent(SubjectDAO subDao){ // 학생삭제
    if(hasStudentData()) return;
    String id = ut.getValue("[삭제] id");
    Student s = getOneStudentById(id);
    if(s == null){
      System.out.println("존재 하지 않는 id 입니다");
      return;
    }
    stuList.remove(s);
    subDao.deleteAllSubjectsInOneStudent(s); // 과목삭제도 같이
    cnt -= 1;
    System.out.println("학생 삭제 완료!");
  }

  void addOneSubjectToOneStudent(SubjectDAO subDAO){ // 과목 추가
    if(hasStudentData()) return;
    Student stu = findOneStudentByStuNum();
    if(stu == null){
      System.out.println("해당 학번 학생이 없습니다");
      return;
    }
    subDAO.addOneSubjectToOneStudent(stu);
  }

  void deleteOneSubjectToOneStudent(SubjectDAO subDAO){ // 과목 삭제
    if(hasStudentData()) return;
    Student stu = findOneStudentByStuNum();
    if(stu == null){
      System.out.println("해당 학번 학생이 없습니다");
      return;
    }
    subDAO.deleteOneSubjectToOneStudent(stu);
  }

  void printAllStudent(SubjectDAO subDao){
    ArrayList<Student> temp = new ArrayList<Student>();
    ArrayList<Double> scores = new ArrayList<Double>();
    for (int i = 0; i < cnt; i++) {
      temp = stuList;
      scores.add(subDao.getAvgSubScoreByStudent(stuList.get(i)));
    }

    for (int i = 0; i < cnt; i++) {
      double max = 0;
      for (int j = i; j < cnt; j++) {
         if(max < scores.get(j)){
           max = scores.get(j);

           Student stu = temp.get(i);
           temp.set(i, temp.get(j));
           temp.set(j, stu);

           Double temp1 = scores.get(i);
           scores.set(i, scores.get(j));
           scores.set(j, temp1);
         }
      }
    }

    for(int i =0; i < cnt; i+=1) {
      if(scores.get(i) > 0){
        System.out.printf("%s 평균 %.2f점 %n" , temp.get(i) , scores.get(i));
        System.out.println("--------------");
      }
    }
  }

  void printStudentByOneSubject(int[] stuNoList) {

    Student[] temp = new Student[stuNoList.length];

    for(int i =0; i < temp.length;i+=1) {
      temp[i] = getStudentByStuNo(stuNoList[i]);
    }

    for(int i =0; i < temp.length;i+=1) {
      String name = temp[i].stuName;
      for(int k =i; k < temp.length;k+=1) {
        if(temp[k].stuName.compareTo(name) < 0) {
          name = temp[k].stuName;
          Student student = temp[k];
          temp[k] = temp[i];
          temp[i] = student;
        }
      }
    }
    for(Student s : temp) {
      System.out.println(s);
    }
  }

  Student getStudentByStuNo(int stuNo) {
    if(cnt == 0) return null;
    for(Student s : stuList) {
      if(stuNo == s.studentNo) {
        return s;
      }
    }
    return null;
  }
}
