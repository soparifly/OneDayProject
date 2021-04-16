package com.callor.word.service;

import com.callor.word.model.WordVO;

public interface WordGameService {
	
	
	public void selectMenu();
	public void inputgame();
	
	public void saveGame();
	public void loadGame();
	public void loadWord();
	public String mixWord();
	public Integer win();
	public WordVO getWord();
	
}
