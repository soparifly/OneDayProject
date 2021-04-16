package com.callor.word;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.callor.word.model.WordVO;
import com.callor.word.service.WordGameService;

public class WordGameV1 implements WordGameService {

	protected List<WordVO> wordList;
	protected String wordFile;
	protected Integer userPoint;
	protected Scanner scan;
	protected String userName;
	protected Random rnd;
	protected String[] strWords;

	public WordGameV1() {
		// TODO 생성자
		this("src/com/callor/word/Word.txt");
	}

	public WordGameV1(String fileName) {

		rnd = new Random();
		scan = new Scanner(System.in);
		wordList = new ArrayList<WordVO>();
		this.wordFile = fileName;
		this.loadWord();
		this.selectMenu();
	}

	@Override
	public String mixWord() {
		// TODO Auto-generated method stub

		WordVO word = getWord(); // getword에서 추출한 문자를

		String strEng = word.getEnglish();

		strWords = strEng.split("");

		for (int i = 0; i < 10; i++) {

			int index1 = rnd.nextInt(strWords.length);
			int index2 = rnd.nextInt(strWords.length);
			String temp = strWords[index1];
			strWords[index1] = strWords[index2];
			strWords[index2] = temp;
		}

		System.out.println(strEng.toString()); // 섞은 문자열 보여주기

		return strEng;
	}

	@Override
	public WordVO getWord() {
		// TODO Auto-generated method stub
		int nSize = wordList.size();
		int num = rnd.nextInt(nSize);

		WordVO wordVO = wordList.get(num);

		return wordVO;

	}

	@Override
	public void saveGame() {

		System.out.println("저장 하시겠습니까?");
		System.out.println("저장할 이름을 입력하세요");
		System.out.print(" >> ");
		String userName = scan.nextLine();

		FileWriter fileWriter = null;
		PrintWriter out = null;

		try {
			fileWriter = new FileWriter("src/com/callor/word/saveLog.txt", true);
			out = new PrintWriter(fileWriter);

			out.println(userName + ":" + userPoint);
			out.flush();
			out.close();
			System.out.println("저장을 완료하였습니다 다음 로그인시 이름을 입력하세요");
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("로그를 저장하는 중에 문제가 발생했습니다");
		}
	}

	@Override
	public void inputgame() {

//		System.out.println(strEng.toString()); // 섞은 문자열 보여주기
		if (userPoint == null) {

			this.mixWord();
			System.out.println("=".repeat(50));
			System.out.println("  WORD GAME 을 시작하겠습니다. ");
			System.out.println("              :              ");
			System.out.println("              :              ");
			System.out.println("     	      :              ");
			System.out.println("-".repeat(50));
			System.out.println("다음 단어를 올바르게 배열하세요");
			System.out.println(Arrays.toString(strWords));
			System.out.println("=".repeat(50));
			System.out.print(" > > ");
			scan = new Scanner(System.in);
			while (true) {
				String strInput = scan.nextLine();
				if (this.mixWord().equalsIgnoreCase(strInput)) {
					this.win();
				} else {
					Integer losePoint = -50;
					System.out.println("... T  ^T");
					userPoint += losePoint;
					System.out.println(" 틀렸어요 .. 점수를 뺏깁니다 --cheer up-- 현재 포인트" + userPoint);
					System.out.println("                 :       ");
					System.out.println("                 :       ");
					System.out.println("힌트를 좀 드려볼까요? 힌트 판매(15점차감)");

					System.out.println("Yes(1)/ No(2)");
					System.out.print(" >> ");
					String enter = scan.nextLine();
					try {
						Integer intEnt = Integer.valueOf(enter);

						if (intEnt == 1) {
							Integer hintPoint = 15;
							System.out.print(" " + strWords[0] + " ");
							System.out.print(" " + strWords[1] + " ");
							for (int i = 0; i < strWords.length - 2; i++) {
								System.out.print(" ○ ");
							}
							System.out.println("\n 글자수는 ......." + strWords.length + "개에요!!");
							System.out.println();
							System.out.println();
							System.out.printf("현재 포인트는 %d ----> %d점 구매 ===> %d\n", userPoint, hintPoint,
									(userPoint - hintPoint));
						} else if (intEnt == 2) {
							this.saveGame();
							return;

						}

					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
//				e.printStackTrace();
						System.out.println("Yes 1..No 2...");

						continue;
					}
					System.out.println("다시 답을 입력합니다");
					System.out.print(">>");
					continue;

				}
				this.selectMenu();
			}
		}
	}

	@Override
	public void loadGame() {

		String fileName = "src/com/callor/word/saveLog.txt";
		FileReader fileReader = null;
		BufferedReader buffer = null;
		try {
			fileReader = new FileReader(fileName);
			buffer = new BufferedReader(fileReader);
			while (true) {

				try {
					String reader = buffer.readLine();
					if (reader == null)
						break;

					String[] log = reader.split(":");
					userPoint = Integer.valueOf(log[2]);

					System.out.println("현재 당신의 포인트는" + log[2] + "입니다");
				} catch (IOException e) {
					// TODO Auto-generated catch block
//				e.printStackTrace();
					System.out.println("파일에 문제가있습니다");
				}

			} // while end
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("저장된 정보가 없습니다");
		}
		this.selectMenu();
	}

	@Override
	public Integer win() {

		Integer winPoint = 50;
		scan = new Scanner(System.in);
		System.out.println(".....");
		userPoint += winPoint;
		System.out.println(" 정답!! 점수를 추가합니다 ** good ** 현재점수 " + userPoint);
		System.out.println("           :    ");
		System.out.println("           :    ");
		System.out.println("다음게임을 시작할까요?");

		System.out.println("Yes(1) / No(2)");
		System.out.print(" >> ");

		String enter = scan.nextLine();

		try {
			Integer intEnt = Integer.valueOf(enter);
			if (intEnt == 1) {
				this.mixWord();
			} else {
				System.out.println(".. 당신의 점수는" + userPoint + " 입니다");
			}

			this.saveGame();

		} catch (NumberFormatException e) {
			System.out.println("Yes 1..No 2...");
		}
		return winPoint;
	}

	@Override
	public void loadWord() {

		FileReader fileReader = null;
		BufferedReader buffer = null;

		try {

			fileReader = new FileReader(wordFile);
			buffer = new BufferedReader(fileReader);

			while (true) {
				String reader;

				reader = buffer.readLine();

				if (reader == null)
					break;

				String[] word = reader.split(":");
				WordVO wordVO = new WordVO(); // wordVO 객체 추가함
				wordVO.setEnglish(word[0]);
				wordVO.setKorean(word[1]);
				wordList.add(wordVO);
			}
			buffer.close();
		} catch (FileNotFoundException e) {
			System.out.println(this.wordFile + "파일이 없음");
		} catch (IOException e) {
			System.out.println("파일을 읽는 동안 문제발생");
		}
	} // while end

	@Override
	public void selectMenu() {
		// TODO 게임 메인화면을 출력한다, 여기서 메뉴선택을 할경우 다음메뉴로 넘어간다
		System.out.println("=".repeat(50));
		System.out.println(" Welcome to WORDGAME!!");
		System.out.println("=".repeat(50));
		System.out.println();
		System.out.println("선택!!..");

		System.out.println("1. 이전게임 다시시작하기");
		System.out.println("2. 새로운 게임하기");
		System.out.println("3. 나가기");
		System.out.println();
		System.out.println();
		System.out.println("-".repeat(50));
		System.out.print(">>");

		String enter = scan.nextLine();
		try {
			Integer intEnter = Integer.valueOf(enter);
			if (intEnter == 2) {
				this.loadGame();

			} else if (intEnter == 1) {
				
				this.inputgame();
			}

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}