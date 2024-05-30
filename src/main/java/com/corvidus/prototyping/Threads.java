package com.corvidus.prototyping;

import com.corvidus.input.Input;
import com.corvidus.input.InputObserver;
import com.corvidus.prototyping.threads.StringWriter;
public class Threads implements InputObserver {
	private StringWriter sw01;
	private StringWriter sw02;
	private Input input;
	public Threads() {
		this.sw01 = new StringWriter("I like dogs.");
		this.sw02 = new StringWriter("I like cats.");
		this.input = new Input();
	}
	
	public void run() {
		this.input.setInputObserver(this);
		this.sw01.start();
		this.sw02.start();
		this.input.start();
	}
	public static void main(String[] args) {
		Threads threads = new Threads();
		threads.run();
    }

	@Override
	public void onInput(Input input, String inString) {
		if(inString.equals("x")) {
			System.exit(0);
		}
	}
}
