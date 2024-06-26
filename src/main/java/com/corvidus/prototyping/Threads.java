package com.corvidus.prototyping;

import com.corvidus.input.Input;
import com.corvidus.input.InputObserver;
import com.corvidus.prototyping.threads.Countdown;
import com.corvidus.prototyping.threads.CountdownObserver;
import com.corvidus.prototyping.threads.StringWriter;
public class Threads implements InputObserver, CountdownObserver {
	private StringWriter sw01;
	private StringWriter sw02;
	private Input input;
	private Countdown countdown;
	public Threads() {
		this.sw01 = new StringWriter("I like dogs.");
		this.sw02 = new StringWriter("I like cats.");
		this.input = new Input();
		this.countdown = new Countdown(10000);
	}
	
	public void run() {
		this.input.addInputObserver(this);
		this.countdown.setCountdownObserver(this);
		this.sw01.start();
		this.sw02.start();
		this.input.start();
		this.countdown.start();
	}

	public static void main(String[] args) {
		Threads threads = new Threads();
		threads.run();
    }

	@Override
	public void onInput(Input input, char c) {
		if(c == 'x' || c == (char)3) {
			this.countdown.interrupt();
			this.sw01.interrupt();
			this.sw02.interrupt();
			this.input.interrupt();
		}
	}

	@Override
	public void onEnd(Countdown countdown) {
		//System.out.println();
		//System.exit(0);
	}
}
