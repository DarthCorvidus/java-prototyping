package com.corvidus.input;
import java.io.IOException;
import java.util.ArrayList;
public class Input extends Thread {
	private ArrayList<InputObserver> inputObservers = new ArrayList<InputObserver>();
	public Input() {
		
	}
	public void addInputObserver(InputObserver inputObserver) {
		this.inputObservers.add(inputObserver);
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
			for(InputObserver inputObserver : this.inputObservers) {
				inputObserver.onInput(this, (char)read);
			}
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
