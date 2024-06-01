package com.corvidus.prototyping;

import com.corvidus.input.Input;
import com.corvidus.input.InputObserver;
import com.corvidus.terminal.TerminalWindow;

public class Terminal implements InputObserver {
	private Input input;
	private TerminalWindow tw;
	private int delay = 1000;
	public Terminal() {
		this.input = new Input();
		this.tw = new TerminalWindow(80, 24);
	}
	
	public void run() {
		this.input.addInputObserver(this);
		this.input.start();
		while(true) {
			this.tw.draw();
			System.out.print((char)27+"[H");
			System.out.print(this.delay);
			try {
				Thread.sleep(this.delay, 0);
			} catch(InterruptedException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
	}

	public static void main(String[] args) {
		Terminal terminal = new Terminal();
		terminal.run();
    }

	@Override
	public void onInput(Input input, char c) {
		if(c == 'x' || c == (char)3) {
			this.input.interrupt();
			System.out.print((char)27+"[2J");
			System.out.print((char)27+"[H");
			System.exit(0);
		}
		if(c == '+' && this.delay > 100) {
			this.delay -= 100;
		}

		if(c == '+' && this.delay > 10 && this.delay <= 100) {
			this.delay -= 10;
		}

		if(c == '-' && this.delay <= 100) {
			this.delay += 10;
		}
		
		if(c == '-' && this.delay > 100) {
			this.delay += 100;
		}
	}
}
