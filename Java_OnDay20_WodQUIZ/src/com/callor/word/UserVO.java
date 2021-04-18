package com.callor.word;

public class UserVO {

	private String saveName;
	private String saveScore;
	public String getSaveScore() {
		return saveScore;
	}
	public void setSaveScore(String saveScore) {
		this.saveScore = saveScore;
	}
	public String getSaveName() {
		return saveName;
	}
	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}
	@Override
	public String toString() {
		return "UserVO [saveName=" + saveName + ", saveScore=" + saveScore + "]";
	}
}
