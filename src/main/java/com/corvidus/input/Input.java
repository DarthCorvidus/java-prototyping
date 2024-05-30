package com.corvidus.input;
import java.util.Scanner;
public class Input extends Thread {
	private InputObserver inputObserver;
	public void setInputObserver(InputObserver inputObserver) {
		this.inputObserver = inputObserver;
	}
	
	@Override
	public void run() {
		while(true) {
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine().strip();
			if(this.inputObserver != null) {
				this.inputObserver.onInput(this, input);
			}
		}
	}
}
