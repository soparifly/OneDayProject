package com.callor.score.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InpuScoreV1A {
	Scanner scan;
	List<ScoreVOA> scoreList;
	String name;
	String[] objects;
			
	public InpuScoreV1A() { // 생성자

		scoreList = new ArrayList<ScoreVOA>();
		scan = new Scanner(System.in);

	}

	public String h1 = "=".repeat(50); // 줄무늬 //

	public void mainmenu() {

		System.out.println(h1);
		System.out.println("빛고을 고등학교 성적처리 프로젝트 2021 ");
		System.out.println(h1);
		System.out.println(" 1. 학생별 성적입");

		System.out.println(" 2. 학생 성적리스트 출력 ");
		System.out.println(" QUIT .  업무종료 ");
		System.out.println(h1);
		System.out.println("업무선택 >> ");
		String enter;

		enter = scan.nextLine();
		Integer intEnter = Integer.valueOf(enter);
		if (intEnter == 1) {
			this.makeName();
		} else if (intEnter == 2) {
			this.printscore();
		} else if (intEnter == 0) {
			System.out.println("업무종료 ");
			System.out.println(h1);
			return;
		}

	}

	public String makeName() {
		System.out.println(h1);

		System.out.println("학생이름을 입력하세요 (입력을 중단하려면 QUIT");
		System.out.println(h1);
		System.out.println("이름  >>>   ");

		name = scan.nextLine();
		
		this.InputScore();
		
		while (true) {
			String exit = "QUIT";
			if (name.equals(exit)) {
			 System.out.println("업무종료");
				break;
			}
		}
		return name;
	}
	

	public void InputScore() {
		
		
		objects = new String[] {"국어","영어","수학","과학","역사"};
		
		System.out.println(h1);
		System.out.println( name + "학생의" +objects[0]+"점수를입력해주세요 ");
		System.out.println(h1);
		System.out.print(" >>>>> ");

		String strKor;
		strKor = scan.nextLine(); // 국어점수입력
		Integer intKor = Integer.valueOf(strKor);
		
		
		System.out.println(h1);
		System.out.println( name + "학생의" +objects[1]+"점수를입력해주세요 ");
		System.out.println(h1);
		System.out.print(" >>>>> ");
		String strEng;
		strEng = scan.nextLine();
		Integer intEng = Integer.valueOf(strEng);
		
		System.out.println(h1);
		System.out.println( name + "학생의" +objects[2]+"점수를입력해주세요 ");
		System.out.println(h1);
		System.out.print(" >>>>> ");
		String strMath;
		strMath = scan.nextLine();
		Integer intMath = Integer.valueOf(strMath);
		
		System.out.println(h1);
		System.out.println( name + "학생의" +objects[3]+"점수를입력해주세요 ");
		System.out.println(h1);
		System.out.print(" >>>>> ");
		String strscience;
		strscience = scan.nextLine();
		Integer intsci = Integer.valueOf(strscience);
		
		System.out.println(h1);
		System.out.println( name + "학생의" +objects[4]+"점수를입력해주세요 ");
		System.out.println(h1);
		System.out.print(" >>>>> ");
		String strhis;
		strhis = scan.nextLine();
		Integer inthis = Integer.valueOf(strhis);
		
		ScoreVOA sco = new ScoreVOA();
		//클래스에 배열의 인스턴스를 만들고 초기화하여 선언함
		sco.setKor(intKor);
		sco.setEng(intEng);
		sco.setMath(intMath);
		sco.setSci(intsci);
		sco.setHis(inthis);
		//과목별 점수를 데이터베이스클래스의 set메서드 매개변수에 대입시켜서 
		//점수를 저장
		scoreList.add(sco);
		//과목별 점수를 매개 인자를 통하여 sco 의 arraylist에 저장함
		this.printscore();`
		return;
	}
	public void printscore() { // 2번메뉴 선택시 나오는 화
		int nCount = 0;
		
		for(int i =0 ; i<scoreList.size() ; i++ ) {
			nCount++;
			ScoreVOA sco2 = scoreList.get(i);
			System.out.println("순번\t이름\t국어\t영어\t수학\t과학\t국어\t총점\t평균 ");
			System.out.print(nCount+"\t");
			System.out.print(sco2.getKor()+"\t");
			System.out.print(sco2.getEng()+"\t");
			System.out.print(sco2.getMath()+"\t");
			System.out.print(sco2.getSci()+"\t");
			System.out.print(sco2.getHis()+"\t");
			
			int intSum = sco2.getKor();
			intSum +=sco2.getEng();
			intSum +=sco2.getMath();
			intSum +=sco2.getSci();
			intSum +=sco2.getHis();
			//	
			System.out.print(intSum+"\t");
			float floatAvg = (float)intSum / 5;
			System.out.print(floatAvg);
		}
	}
}
