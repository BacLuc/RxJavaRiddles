package com.vanniktech.rxriddles.util;

public class Unit {
	@SuppressWarnings("InstantiationOfUtilityClass")
	public static Unit create() {
		return new Unit();
	}

	private Unit() {
	}
}
