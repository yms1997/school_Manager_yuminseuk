package School;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Util {
  Scanner sc = new Scanner(System.in);
  final String CUR_PATH = System.getProperty("user.dir") + "\\school_Manager_yuminseuk\\school_Manager_yuminseuk\\" + this.getClass().getPackageName() + "\\";

  int getValue(String msg, int start, int end) {
    while (true) {
      System.out.printf("▶ %s[%d-%d] 입력 :", msg, start, end);
      try {
        int num = sc.nextInt();
        sc.nextLine();
        if (num < start || num > end) {
          System.out.printf("%d - %d 사이값 입력해주세요 %n", start, end);
          continue;
        }
        return num;
      } catch (Exception e) {
        sc.nextLine();
        System.out.println("숫자값을 입력하세요");
      }
    }
  }

  String getValue(String msg) {
    System.out.printf("%s 입력:", msg);
    return sc.next();
  }

  String loadData(String fileName) {
    String data = "";
    try (FileReader fr = new FileReader(CUR_PATH + fileName); BufferedReader br = new BufferedReader(fr)) {
      while (true) {
        String line = br.readLine();
        if (line == null) {
          break;
        }
        data += line + "\n";
      }
      data = data.substring(0, data.length() - 1);
      System.out.println(fileName + " 로드 완료");

    } catch (IOException e) {
      System.out.println(fileName + " 로드 실패");
      e.printStackTrace();
    }
    return data;
  }

  void loadfromFile(StudentDAO studao, SubjectDAO subdao){
    String stuData = loadData("student.txt");
    String subData = loadData("subject.txt");
    studao.addStudentfromData(stuData);
    subdao.addSubjectsFromData(subData);
    studao.updateMaxStuNo();
  }

  void savetoFile(StudentDAO studao, SubjectDAO subdao){
    String stuData = studao.saveAsFileData();
    String subData = subdao.saveAsFileData();
    saveData("student.txt", stuData);
    saveData("subject.txt", subData);
  }

  void saveData(String fileName, String data) {
    try(FileWriter fw = new FileWriter(CUR_PATH + fileName)){
      fw.write(data);
      System.out.println(fileName + "저장 성공");
    } catch (IOException e) {
      System.out.println(fileName + "저장 실패");
      e.printStackTrace();
    }
  }
}
