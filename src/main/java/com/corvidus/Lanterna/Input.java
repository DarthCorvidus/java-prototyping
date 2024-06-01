package com.corvidus.Lanterna;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;
import java.util.ArrayList;

public class Input extends Thread {
	Terminal terminal;
	ArrayList<InputObserver> inputObservers = new ArrayList<InputObserver>();
	public Input(Terminal terminal) {
		this.terminal = terminal;
	}
	
	public synchronized void addInputObserver(InputObserver inputObserver) {
		this.inputObservers.add(inputObserver);
	}
	
	@Override
	public void run() {
		KeyStroke keystroke;
		while(!this.isInterrupted()) {
			try {
				keystroke = this.terminal.pollInput();
				if(keystroke == null) {
					continue;
				}
				for(InputObserver inputObserver : this.inputObservers) {
					inputObserver.onInput(keystroke);
				}
			} catch(IOException e) {
				
			}
		}
	}
}
