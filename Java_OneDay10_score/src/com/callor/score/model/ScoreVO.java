package com.callor.score.model;

public class ScoreVO {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private Integer kor;
	private Integer eng;
	private Integer math;
	private Integer science;
	private Integer history;
	private Integer totalScore;
	private Float totalAvg;

	public Integer getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}

	public Float getTotalAvg() {
		return totalAvg;
	}

	public void setTotalAvg(Float totalAvg) {
		this.totalAvg = totalAvg;
	}

	public Integer getKor() {
		return kor;
	}

	public void setKor(Integer kor) {
		this.kor = kor;
	}

	public Integer getEng() {
		return eng;
	}

	public void setEng(Integer eng) {
		this.eng = eng;
	}

	public Integer getMath() {
		return math;
	}

	public void setMath(Integer math) {
		this.math = math;
	}

	public Integer getScience() {
		return science;
	}

	public void setScience(Integer science) {
		this.science = science;
	}

	public Integer getHistory() {
		return history;
	}

	public void setHistory(Integer history) {
		this.history = history;
	}

}
