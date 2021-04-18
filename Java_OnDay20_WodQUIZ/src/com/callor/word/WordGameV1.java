package com.callor.word;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
	protected String[] strWords;
	protected String questionWords;
	protected final int 유저명 = 0;
	protected final int 저장된점수 = 1;
	protected String koreanWords;
	protected int num;
	protected List<UserVO> userList;

	public WordGameV1() {
		// TODO 생성자
		this("src/com/callor/word/Word.txt");
	}

	public WordGameV1(String fileName) {
		scan = new Scanner(System.in);
		wordList = new ArrayList<WordVO>();
		wordFile = fileName;
		num = 0;
		userList = new ArrayList<UserVO>();

		loadlog();
		loadWord();
		mixWord();
		this.selectMenu();
	}

	public String mixWord() {// TODO getword()에서 제공받은 문자를 알파벳단위로 쪼갭니다 (횟수10번) 리턴값 : questionWords
		Random rnd = new Random();
		int nSize = wordList.size();
		num = rnd.nextInt(nSize);
		WordVO word = wordList.get(num);
		questionWords = word.getEnglish();
		koreanWords = word.getKorean();
		strWords = questionWords.split("");

		for (int i = 0; i < 10; i++) {
			int index1 = rnd.nextInt(strWords.length);
			int index2 = rnd.nextInt(strWords.length);
			String temp = strWords[index1];
			strWords[index1] = strWords[index2];
			strWords[index2] = temp;
		}
		return questionWords;
	}

	@Override
	public void saveGame() {// TODO 게임을 저장합니다, 로그에 저장함 userName 필요함
		System.out.println("진행상황을 저장 하시겠습니까?");
		System.out.println("Yes(1) / No(2)");
		while (true) {
			Integer enter = scan.nextInt();
			if (enter == 2) {
				break;
			} else if (enter == 1) {
				FileWriter fileWriter = null;
				PrintWriter out = null;
				try {
					fileWriter = new FileWriter("src/com/callor/word/saveLog.log", true);
					out = new PrintWriter(fileWriter);

					out.println(userName + ":" + userPoint);
					out.flush();
					out.close();
					System.out.println("저장을 완료하였습니다 다음 로그인시 이름을 입력하세요");
					break;
				} catch (IOException e) {
					System.out.println("로그를 저장하는 중에 문제가 발생했습니다");
					continue;
				}
			} // while end
		}
		System.out.println("메인메뉴로 진입합니다");
		this.selectMenu();
	}

	@Override
	public String inputGame() {
		// TODO 게임시작 메인
		while (true) {
			System.out.println("사용할 이름 입력하세요 (나가기 : QUIT)");
			System.out.print(">>");
			userName  = scan.nextLine();
			if (userName.equals("QUIT")) {
				return null;
			}
			
			return userName;
		} // while end
	}

	public UserVO getUserID(String id) {
		// TODO 진입시 입력한 user의이름으로 log를 조회하여 유저정보를 return
		if (id.equals(null)) {
			System.out.println("저장된 ID가 없습니다");
			return null;
		}
		int nSize = userList.size();
		for (int i = 0; i < nSize; i++) {
			UserVO vo = userList.get(i);
			if (vo.getSaveName().equals(id)) {
				userName = vo.getSaveName();
				userPoint = Integer.valueOf(vo.getSaveScore());
				return vo;
			}
		}
		return null;
	}

	private void loadlog() {
		String fileName = "src/com/callor/word/saveLog.log";
		FileReader fileReader = null;
		BufferedReader buffer = null;
		try {
			fileReader = new FileReader(fileName);
			buffer = new BufferedReader(fileReader);
			while (true) {
				String reader = buffer.readLine();
				if (reader == null)
					break;
				String[] user = reader.split(":");
				UserVO vo = new UserVO();
				vo.setSaveName(user[유저명]);
				vo.setSaveScore(user[저장된점수]);
				userList.add(vo);
			}
			buffer.close();
		} catch (FileNotFoundException e) {
			System.out.println("저장된 정보가 없습니다");
		} catch (IOException e) {
			System.out.println("파일에 문제가 있습니다");
		}

	}

	public Integer win() {
		Integer winPoint = 50;

		System.out.println("..... 정답!! 점수를 추가합니다 ** good **");
		userPoint += winPoint;

		System.out.println(" 현재점수 " + userPoint);
		System.out.println("     :    ");
		System.out.println(questionWords + " : " + koreanWords);
		System.out.println("     :    ");
		System.out.println(userName + "님.. 당신의 점수는" + userPoint + " 입니다");
		System.out.println("다음게임을 시작할까요?");
		System.out.print("Yes (1) No (2)  > > ");
		while (true) {
			try {
				String enter = scan.nextLine();
				if (enter == null) {
					System.out.println("입력값이 없습니다 다시 입력하세요");
					continue;
				}
				Integer intEnt = Integer.valueOf(enter);
				if (intEnt == 1) {
					mixWord();
					mainGame();
					break;
				} else if (intEnt == 2) {
					saveGame();
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("Yes 1..No 2...");
				continue;
			}
		} // while end
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
		while (true) {

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
			while (true) {

				String enter = scan.nextLine();
				if (enter == null) {
					System.out.println("입력 값이 없습니다 다시입력하세요");
					continue;
				}
				try {
					Integer intEnter = Integer.valueOf(enter);
					if (intEnter == 1) {
						inputGame();
						getUserID(userName);
						System.out.println("현재 아이디 :   " + userName);
						System.out.println("현재 점수 :    "  + userPoint);
						printfirstgame();
						mainGame();
						break;
					} else if (intEnter == 2) {
						this.inputGame();// 이름입력받음
						if (userName.equals("QUIT")) {
							break; // 입력값 멈춤 다시처음으로
						}
						printfirstgame();
						mainGame(); // 그이름으로 메인게임을시작함
					} else if (intEnter == 3) {
						System.out.println("게임을 종료합니다");
						return;
					}

				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
//			e.printStackTrace();
					System.out.println("정확한 메뉴를 입력해주세요");
					System.out.println("1.load game, 2.new game, 3.QUIT..");
				}

			} // 메뉴 선택하는 while end

		} // select while end
	}

	@Override
	public Integer lose() {
		// TODO Auto-generated method stub
		Integer losePoint = -50;
		System.out.println("... T  ^T");
		userPoint += losePoint;
		System.out.println(" 틀렸어요 .. 점수를 뺏깁니다 -- 현재 포인트" + userPoint);
		System.out.println("                 :       ");
		System.out.println("                 :       ");
		System.out.println("힌트를 좀 드려볼까요? 힌트 판매(15점차감)");
		System.out.println("Yes(1)/ No(2)");
		System.out.print(">> ");
		while (true) {
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
					return userPoint;
				} else if (intEnt == 2) {
					this.saveGame();
				}
			} catch (NumberFormatException e) {
//	e.printStackTrace();
				System.out.println("Yes 1..No 2...");
				continue;
			}
			System.out.println("다시 답을 입력합니다");
			System.out.print(">>");

		}
	}

	public void printfirstgame() {
		System.out.println("  WORD GAME 을 시작하겠습니다. ");
		System.out.println("              :              ");
		System.out.println("              :              ");
		System.out.println("     	      :              ");
		System.out.println("-".repeat(50));

	}

	public void mainGame() {
//		mixWord();
		System.out.println("다음 단어를 올바르게 배열하세요");
//		System.out.println(questionWords.toString()); // 섞은 문자열 보여주기Í
		System.out.println("=".repeat(50));

		System.out.println(Arrays.toString(strWords));
		System.out.println("=".repeat(50));
		System.out.print(" > > ");
		while (true) {
			String enter = scan.nextLine();

			if (enter.equalsIgnoreCase(questionWords)) {
				win();
				break;
			} else {
				lose();
				break;
			}
		} // while end
	}

}