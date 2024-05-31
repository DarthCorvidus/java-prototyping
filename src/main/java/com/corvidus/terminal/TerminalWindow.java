package com.corvidus.terminal;
public class TerminalWindow {
	private int width;
	private int height;
	private String buffer;
	public TerminalWindow(int width, int height) {
		this.width = width;
		this.height = height;
		this.buffer = "A".repeat(this.width*this.height);
	}
	
	public void draw() {
		System.out.print((char)27+"[2J");
		System.out.print((char)27+"[H");
		System.out.print(this.buffer);
	}
}
