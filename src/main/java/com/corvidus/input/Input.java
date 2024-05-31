package com.corvidus.input;
import java.io.IOException;
import java.util.Scanner;
public class Input extends Thread {
	private InputObserver inputObserver;
	public Input() {
		
	}
	public void setInputObserver(InputObserver inputObserver) {
		this.inputObserver = inputObserver;
	}
	
	@Override
	public void run() {
		while(true) {
			if(this.isInterrupted()) {
				return;
			}
			int read = 0;
			try {
				read = RawConsoleInput.read(true);
			} catch (IOException e) {
				System.exit(0);
			}
			this.inputObserver.onInput(this, (char)read);
			/*
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine().strip();
			if(this.inputObserver != null) {
				this.inputObserver.onInput(this, input);
			}
			*/
		}
	}
}
