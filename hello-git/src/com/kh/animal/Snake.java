package com.kh.animal;

public class Snake {

	private double length;// master에 추가
	private String name; // master에서 추가
	private double weight; // animal에서추가

	
	public void move() {
		System.out.println("스르르륵~");
	}
	
	public void poisonAttack() {
		System.out.println("독침 물기!");
	}
}
