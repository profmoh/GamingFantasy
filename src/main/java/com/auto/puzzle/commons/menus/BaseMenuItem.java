package com.auto.puzzle.commons.menus;

public interface BaseMenuItem<T> {

	T getValue(int value);

	String getDescription();
}
