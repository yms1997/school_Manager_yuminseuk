package School_유민석;

public class Controller {

  StudentDAO stuDao;
  SubjectDAO subDao;
  Util ut;

  Controller() {
    stuDao = new StudentDAO();
    subDao = new SubjectDAO();
    ut = new Util();
    ut.loadfromFile(stuDao, subDao);
  }
  void mainMenu() {
    System.out.println("[1]학생추가"); // 학번(1001) 자동증가 : 학생id 중복 불가
    System.out.println("[2]학생삭제"); // 학생 id 입력후 삭제 과목도 같이 삭제
    System.out.println("[3]과목추가"); //학번 입력후 점수 랜덤 50-100 : 과목이름 중복 저장불가능
    System.out.println("[4]과목삭제"); // 학번 입력후 과목삭제
    System.out.println("[5]전체학생목록"); // 점수로 출력
    System.out.println("[6]과목별학생목록"); // 과목이름 입력받아서 해당 과목 학생이름과 과목점수 출력 (오름차순 이름순)
    System.out.println("[7]파일저장");
    System.out.println("[8]파일로드");
    System.out.println("[0] 종료");
  }

  void run() {
    while(true) {
      mainMenu();
      int sel = ut.getValue("메뉴", 0, 8);
      if(sel == 1) {
        System.out.println("[ 학생 추가  ]");
        stuDao.addOneStudent();
      }else if(sel == 2) {
        System.out.println("[ 한 학생 삭제(과목까지 삭제) ]");
        stuDao.deleteOneStudent(subDao);
      }else if(sel == 3) {
        System.out.println("[ 학생의 한 과목 추가 ]");
        stuDao.addOneSubjectToOneStudent(subDao);
      }else if(sel == 4) {
        System.out.println("[ 한 학생의 한 과목 삭제 ]");
        stuDao.deleteOneSubjectToOneStudent(subDao);
      }else if(sel == 5) {
        System.out.println("[ 전체 학생 목록  ]");
        stuDao.printAllStudent(subDao);
      }else if(sel == 6) {
        System.out.println("[ 한 과목 학생 목록  ]");
        subDao.getStuListByOneSubject(stuDao);
      }else if(sel == 7) {
        System.out.println("[ 파일 저장 ]");
        ut.savetoFile(stuDao, subDao);
      }else if(sel == 8) {
        System.out.println("[ 파일 로드 ]");
        ut.loadfromFile(stuDao, subDao);
      }else {
        System.out.println("프로그램 종료");
        return;
      }
    }
  }
}
