package com.corvidus.prototyping;

import com.corvidus.input.Input;
import com.corvidus.input.InputObserver;
import com.corvidus.terminal.TerminalWindow;

public class Terminal implements InputObserver {
	private Input input;
	private TerminalWindow tw;
	public Terminal() {
		this.input = new Input();
		this.tw = new TerminalWindow(80, 24);
	}
	
	public void run() {
		this.input.setInputObserver(this);
		while(true) {
			this.tw.draw();
			try {
				Thread.sleep(1000, 0);
			} catch(InterruptedException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
	}

	public static void main(String[] args) {
		Terminal threads = new Terminal();
		threads.run();
    }

	@Override
	public void onInput(Input input, char c) {
		if(c == 'x' || c == (char)3) {
			this.input.interrupt();
			System.exit(0);
		}
	}
}
