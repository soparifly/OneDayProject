package com.callor.word.service;

import com.callor.word.UserVO;

public interface WordGameService {
	
	
	public void selectMenu();
	public String inputGame();
	public void mainGame();
	public void saveGame();
	public UserVO getUserID(String id);
	public void loadWord();

	public Integer win();
	public Integer lose();

	
}
