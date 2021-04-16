//package com.callor.word;
//
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Random;
//import java.util.Scanner;
//
//import com.callor.word.model.WordVO;
//
//public class InputGame  extends WordGameV1{
////단어목록을 영어, 한글 따로 배열에 저장함
//	protected List<WordVO> wordList;
//	protected String wordFile;
//	protected Integer userPoint;
//	protected Scanner scan;
//	protected String userName;
//
//	public InputGame() {
//
//		this("src/com/callor/word/Word.txt");
//
//	}
//
//	public InputGame(String fileName) {
//		userPoint = 100;
//		this.wordFile = fileName;
//		wordList = new ArrayList<WordVO>();
//		this.loadword();
//		this.mixWord();
//	}
//
//	public void loadword() {
//
//		FileReader fileReader = null;
//		BufferedReader buffer = null;
//
//		try {
//
//			fileReader = new FileReader(this.wordFile);
//			buffer = new BufferedReader(fileReader);
//
//			while (true) {
//				String reader;
//
//				reader = buffer.readLine();
//
//				if (reader == null)
//					break;
//
//				String[] word = reader.split(":");
//				WordVO wordVO = new WordVO(); // wordVO 객체 추가함
//				wordVO.setEnglish(word[0]);
//				wordVO.setKorean(word[1]);
//				wordList.add(wordVO);
//			}
//			buffer.close();
//		} catch (FileNotFoundException e) {
//			System.out.println(this.wordFile + "파일이 없음");
//		} catch (IOException e) {
//			System.out.println("파일을 읽는 동안 문제발생");
//		}
//	} // while end
//
//	
//	public void mixWord() { // word문자 섞기
//		Random rnd = new Random();
//
//		WordVO word = this.getWord(); // getword에서 추출한 문자를
//
//		String strEng = word.getEnglish();
//
//		String[] strWords = strEng.split("");
//		for (int i = 0; i < 10; i++) {
//			int index1 = rnd.nextInt(strWords.length);
//			int index2 = rnd.nextInt(strWords.length);
//			String temp = strWords[index1];
//			strWords[index1] = strWords[index2];
//			strWords[index2] = temp;
//		}
//		System.out.println(strEng.toString()); // 섞은 문자열 보여주기
//		System.out.println("=".repeat(50));
//		System.out.println("  WORD GAME 을 시작하겠습니다. ");
//		System.out.println("              :              ");
//		System.out.println("              :              ");
//		System.out.println("     	      :              ");
//		System.out.println("-".repeat(50));
//		System.out.println("다음 단어를 올바르게 배열하세요");
//		System.out.println(Arrays.toString(strWords));
//		System.out.println("=".repeat(50));
//		System.out.print(" > > ");
//		scan = new Scanner(System.in);
//		while (true) {
//			String strInput = scan.nextLine();
//			if (strEng.equalsIgnoreCase(strInput)) {
//				this.win();
//			} else {
//				Integer losePoint = -50;
//				System.out.println("... T  ^T");
//				userPoint += losePoint;
//				System.out.println(" 틀렸어요 .. 점수를 뺏깁니다 --cheer up-- 현재 포인트" + userPoint);
//				System.out.println("                 :       ");
//				System.out.println("                 :       ");
//				System.out.println("힌트를 좀 드려볼까요? 힌트 판매(15점차감)");
//
//				System.out.println("Yes(1)/ No(2)");
//				System.out.print(" >> ");
//				String enter = scan.nextLine();
//				try {
//					Integer intEnt = Integer.valueOf(enter);
//			
//					if (intEnt == 1) {
//						Integer hintPoint = 15;
//						System.out.print(" " + strWords[0] + " ");
//						System.out.print(" " + strWords[1] + " ");
//						for (int i = 0; i < strWords.length - 2; i++) {
//							System.out.print(" ○ ");
//						}
//						System.out.println("\n 글자수는 ......." + strWords.length + "개에요!!");
//						System.out.println();
//						System.out.println();
//						System.out.printf("현재 포인트는 %d ----> %d점 구매 ===> %d\n", userPoint, hintPoint,
//								(userPoint - hintPoint));
//					} else if (intEnt == 2){
//						this.saveGame();
//						return;
//						
//					}
//
//				} catch (NumberFormatException e) {
//					// TODO Auto-generated catch block
////				e.printStackTrace();
//					System.out.println("Yes 1..No 2...");
//
//					continue;
//				}
//				System.out.println("다시 답을 입력합니다");
//				System.out.print(">>");
//				continue;
//
//			}
//			this.selectMenu();
//		}
//	}
//
//	public Integer win() {
//		Integer winPoint = 50;
//		scan = new Scanner(System.in);
//		System.out.println(".....");
//		userPoint += winPoint;
//		System.out.println(" 정답!! 점수를 추가합니다 ** good ** 현재점수 " + userPoint);
//		System.out.println("           :    ");
//		System.out.println("           :    ");
//		System.out.println("다음게임을 시작할까요?");
//
//		System.out.println("Yes(1) / No(2)");
//		System.out.print(" >> ");
//
//		String enter = scan.nextLine();
//
//		try {
//			Integer intEnt = Integer.valueOf(enter);
//			if (intEnt == 1) {
//				this.mixWord();
//			} else {
//				System.out.println(".. 당신의 점수는" + userPoint + " 입니다");
//			}
//
//		
//			this.saveGame();
//
//		} catch (NumberFormatException e) {
//			System.out.println("Yes 1..No 2...");
//		}
//		return winPoint;
//	}
//
//	public WordVO getWord() {// 단어 한개 축출하기
//		return null;
//	}
//
//	public void saveGame() {
//		
//		System.out.println("저장 하시겠습니까?");
//		
//		
//		FileWriter fileWriter = null;
//		PrintWriter out = null;
//		
//		
//		try {
//			fileWriter = new FileWriter("C:Temp/wordgamesavefile.log",true);
//			out = new PrintWriter(fileWriter);
//			
//			out.println(userName+":"+userPoint);
//			out.flush();
//			out.close();
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
////			e.printStackTrace();
//			System.out.println("파일을 저장하는 중에 문제가 발생했습니다");
//		}
////		
////		this.selectMenu();
////		
//		
//
//	}
//
//}// class end