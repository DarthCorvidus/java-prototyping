package com.corvidus.prototyping;

import com.corvidus.input.Input;
import com.corvidus.input.InputObserver;
import com.corvidus.prototyping.threads.StringWriter;
public class Threads implements InputObserver {
	public static void main(String[] args) {
		StringWriter sw01 = new StringWriter("I like dogs.");
		StringWriter sw02 = new StringWriter("I like cats.");
		Input input = new Input();
		input.setInputObserver(new Threads());
		sw01.start();
		sw02.start();
		input.start();
		
    }

	@Override
	public void onInput(Input input, String inString) {
		if(inString.equals("x")) {
			System.exit(0);
		}
	}
}
