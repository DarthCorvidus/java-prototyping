package com.corvidus.terminal;
public class TerminalWindow {
	private int width;
	private int height;
	private String rotate = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private int page = 0;
	public TerminalWindow(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	private String getBuffer() {
		String buffer = this.rotate.substring(this.page, this.page+1).repeat(this.width*this.height);
		if(this.page<this.rotate.length()-1) {
			this.page++;
		} else {
			this.page = 0;
		}
	return buffer;
	}
	
	public void draw() {
		System.out.print((char)27+"[2J");
		System.out.print((char)27+"[H");
		String buffer = this.getBuffer();
		String line;
		int beginIndex;
		for(int i = 0; i<this.height;i++) {
			beginIndex = this.width*i;
			line = buffer.substring(beginIndex, beginIndex+this.width);
			System.out.print(line);
			if(i < this.height-1) {
				System.out.println("");
			}		
		}
	}
}
