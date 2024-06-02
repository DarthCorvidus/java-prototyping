package com.corvidus.prototyping;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import java.io.IOException;

public class LanternaBenchmark {
	TextGraphics textGraphics = null;
	Screen screen;
	private int delay = 1000;
	private char[] alpha = {'A', 'B', 'C'};
	private int page = 0;
	public LanternaBenchmark() {
		DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
		System.out.println("Starting");
		try {
			//this.terminal = defaultTerminalFactory.createTerminal();
			this.screen = defaultTerminalFactory.createScreen();
			this.screen.startScreen();
			this.textGraphics = this.screen.newTextGraphics();
			//this.input = new Input(this.terminal);
			//this.input.addInputObserver(this);
		} catch(IOException e) {
			e.printStackTrace();
			System.exit(0);
		}

	}
	
	public void run() {
		KeyStroke keyStroke;
		try {
			while(true) {
				//this.screen.doResizeIfNecessary();
				keyStroke = this.screen.pollInput();
				if(keyStroke != null && keyStroke.getKeyType() == KeyType.Escape) {
					break;
				}
				if(keyStroke != null && keyStroke.getKeyType() == KeyType.Character) {
					this.onInput(keyStroke.getCharacter());
				}
				for(int row = 0; row < this.screen.getTerminalSize().getRows(); row++) {
					for(int col = 0; col < this.screen.getTerminalSize().getColumns(); col++) {
						this.textGraphics.setCharacter(col, row, this.alpha[this.page]);
					}
				}
				this.textGraphics.putString(0, 0, String.valueOf(this.delay));
				
				if(this.page == this.alpha.length -1) {
					this.page = 0;
				} else {
					this.page++;
				}
				this.screen.refresh();
				Thread.sleep(this.delay, 0);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		LanternaBenchmark terminal = new LanternaBenchmark();
		terminal.run();
    }

	public void onInput(char c) throws IOException {
		if(c == 'x' || c == (char)3) {
			//this.screen.close();
			System.exit(0);
		}
		if(c == '+' && this.delay > 100) {
			this.delay -= 100;
			return;
		}

		if(c == '+' && this.delay > 10 && this.delay <= 100) {
			this.delay -= 10;
			return;
		}

		if(c == '-' && this.delay < 100) {
			this.delay += 10;
			return;
		}
		
		if(c == '-' && this.delay >= 100) {
			this.delay += 100;
			return;
		}
	}
}
