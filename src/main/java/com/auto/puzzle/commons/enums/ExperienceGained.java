package com.auto.puzzle.commons.enums;

public enum ExperienceGained {

	LOSE(0), STEP(1), DRAW(2), WON(3);

	int score;

	private ExperienceGained(int score) {
		this.score = score;
	}

	public int getScore() {
		return this.score;
	}
}
