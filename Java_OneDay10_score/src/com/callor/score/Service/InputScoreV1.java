package com.callor.score.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.callor.score.model.ScoreVO;

public class InputScoreV1 {

	Scanner scan;
	List<ScoreVO> scoreList;

	public InputScoreV1() {

		scan = new Scanner(System.in);
		scoreList = new ArrayList<ScoreVO>();

		// TODO Auto-generated constructor stub
	}

	public void mainMenu() {
		while (true) {
			System.out.println("=".repeat(60));
			System.out.println("빛고을 고등학교 성적처리 프로젝트 2021");
			System.out.println("=".repeat(60));
			System.out.println(" 1. 학생별 성적처리");
			System.out.println(" 2. 학생 성적 리스트 출력");
			System.out.println(" QUIT . 업무종료");
			System.out.println("=".repeat(60));

			System.out.println("업무선택 >>");

			String strNum = scan.nextLine();
			Integer intNum = Integer.valueOf(strNum);
			if (strNum.equals("0")) {
				break;
			} else if (strNum.equals("1")) {
				this.inputScore();
			} else if (strNum.equals("2")) {
				this.scorePrint();
			}
		}
		System.out.println("업무종료!!");
	}

	public Integer inputScore() {
		System.out.println("=".repeat(50));
		System.out.println("학생의 이름을 입력하세요 (입력을 중단하려면 QUIT)");
		System.out.println("=".repeat(50));
		System.out.println("이름 >>");

		String name = scan.nextLine();

		if (name.equals("QUIT")) {
			return 0;
		}

		System.out.println("=".repeat(50));
		System.out.println(name + "학생의 성적을 입력하세요" + "(성적 범위 : 0~ 100, 입력을 중단하려면 QUIT");
		System.out.println("=".repeat(50));

		System.out.print("국어 >>   ");
		String strKor = scan.nextLine();
		Integer intKor = 0;
		intKor = Integer.valueOf(strKor);

		System.out.println("영어 >> ");
		String strEng = scan.nextLine();
		Integer intEng = 0;
		intEng = Integer.valueOf(strEng);

		System.out.println("수학 >> ");
		String strMath = scan.nextLine();
		Integer intMath = 0;
		intMath = Integer.valueOf(strMath);

		System.out.println("과학 >> ");
		String strScience = scan.nextLine();
		Integer intScience = 0;
		intScience = Integer.valueOf(strScience);

		System.out.println("역사 >>");
		String strHistory = scan.nextLine();
		Integer intHistory = 0;
		intHistory = Integer.valueOf(strHistory);

		ScoreVO scoreVO = new ScoreVO(); // 데이터형 V 객체이름 = 데이터형 new ();
		scoreVO.setName(name);
		scoreVO.setKor(intKor);
		scoreVO.setEng(intEng);
		scoreVO.setMath(intMath);
		scoreVO.setScience(intScience);
		scoreVO.setHistory(intHistory);
		scoreList.add(scoreVO);
		this.printscoreList(scoreVO);
		return 0;
	}

	public void scorePrint() {
		int nCount = 0;

		for (int i = 0; i < scoreList.size(); i++) {
			nCount++;
			ScoreVO sVO = scoreList.get(i);
			System.out.println("순번\t 이름\t 국어\t 영어\t 수학\t 과학\t 국어\t 총점\t 평균");
			System.out.print(nCount + "\t");
			System.out.print(sVO.getName() + "\t");
			System.out.print(sVO.getKor() + "\t");
			System.out.print(sVO.getEng() + "\t");
			System.out.print(sVO.getMath() + "\t");
			System.out.print(sVO.getScience() + "\t");
			System.out.print(sVO.getHistory() + "\t");
			
//			System.out.print(sVO.getTotalScore() + "\t");
//			System.out.print(sVO.getTotalAvg() + "\t");
			
			int intSum = sVO.getKor();
			intSum += sVO.getEng();
			intSum += sVO.getMath();
			intSum += sVO.getScience();
			intSum += sVO.getHistory();
			System.out.print(intSum+ "\t");
			
			float scoreAvg = (float)intSum / 5;
			System.out.printf("%3.2f + \n",scoreAvg);
			
		}
	}

	public void printscoreList(ScoreVO scoreVO) {

		System.out.println("=".repeat(50));
		System.out.println(scoreVO.getName() + "학생의 성적이 추가되었습니다");
		System.out.println("=".repeat(50));
		System.out.println("국어: " + scoreVO.getKor() + "\t");
		System.out.println("영어: " + scoreVO.getEng() + "\t");
		System.out.println("수학: " + scoreVO.getMath() + "\t");
		System.out.println("과학: " + scoreVO.getScience() + "\t");
		System.out.println("역사: " + scoreVO.getHistory() + "\t\n");
	}



}
