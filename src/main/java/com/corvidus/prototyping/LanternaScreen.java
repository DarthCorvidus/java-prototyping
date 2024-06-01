package com.corvidus.prototyping;

import com.corvidus.Lanterna.Input;
import com.corvidus.Lanterna.InputObserver;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalResizeListener;
import java.io.IOException;

public class LanternaScreen implements TerminalResizeListener, InputObserver {
	//com.googlecode.lanterna.terminal.Terminal terminal = null;
	TextGraphics textGraphics = null;
	Input input;
	Screen screen;
	public LanternaScreen() {
		DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
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
	public static void main(String[] args) {
		LanternaScreen lanterna = new LanternaScreen();
		lanterna.run();
	}

	public void run() {
		//this.input.start();
		try {
			this.textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
			this.textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);
			this.textGraphics.putString(2, 1, "Lanterna Tutorial 2 - Press ESC to exit", SGR.BOLD);
			this.textGraphics.setForegroundColor(TextColor.ANSI.DEFAULT);
			this.textGraphics.setBackgroundColor(TextColor.ANSI.DEFAULT);
			this.textGraphics.putString(5, 3, "Terminal Size: ", SGR.BOLD);
			this.textGraphics.putString(5 + "Terminal Size: ".length(), 3, this.screen.getTerminalSize().toString());
			this.textGraphics.putString(5, 4, "Last Keystroke: ", SGR.BOLD);
			this.textGraphics.putString(5 + "Last Keystroke: ".length(), 4, "<Pending>");
			KeyStroke keyStroke;
			this.screen.refresh();
			while(true) {
				this.screen.doResizeIfNecessary();
				keyStroke = this.screen.pollInput();
				if(keyStroke == null) {
					continue;
				}
				if(keyStroke.getKeyType() == KeyType.Escape) {
					break;
				}
				if(keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter().toString().equals("x")) {
					break;
				}
				this.textGraphics.drawLine(5, 4, this.screen.getTerminalSize().getColumns() - 1, 4, ' ');
				this.textGraphics.drawLine(5, 5, this.screen.getTerminalSize().getColumns() - 1, 4, ' ');
				this.textGraphics.putString(5, 4, "Last Keystroke: ", SGR.BOLD);
				this.textGraphics.putString(5 + "Last Keystroke: ".length(), 4, keyStroke.toString());
				if(keyStroke.getKeyType() == KeyType.Character) {
					this.textGraphics.putString(5, 5, "Last Charakter: ", SGR.BOLD);
					this.textGraphics.putString(5 + "Last Charakter: ".length(), 5, keyStroke.getCharacter().toString());
				}
				
				this.screen.refresh();
			}
			/*
			KeyStroke keyStroke = this.terminal.readInput();
			while (keyStroke.getKeyType() != KeyType.Escape) {
				this.textGraphics.drawLine(5, 4, this.terminal.getTerminalSize().getColumns() - 1, 4, ' ');
				this.textGraphics.putString(5, 4, "Last Keystroke: ", SGR.BOLD);
				this.textGraphics.putString(5 + "Last Keystroke: ".length(), 4, keyStroke.toString());
				this.terminal.flush();
				keyStroke = terminal.readInput();
			}
			*/
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (this.screen != null) {
				try {
					/*
                    The close() call here will exit private mode
					 */
					this.screen.stopScreen();
					this.screen.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public void onResized(Terminal trmnl, TerminalSize ts) {
		// Be careful here though, this is likely running on a separate thread. Lanterna is threadsafe in
		// a best-effort way so while it shouldn't blow up if you call terminal methods on multiple threads,
		// it might have unexpected behavior if you don't do any external synchronization
		this.textGraphics.drawLine(5, 3, ts.getColumns() - 1, 3, ' ');
		this.textGraphics.putString(5, 3, "Terminal Size: ", SGR.BOLD);
		this.textGraphics.putString(5 + "Terminal Size: ".length(), 3, ts.toString());
		try {
			trmnl.flush();
		} catch (IOException e) {
			// Not much we can do here
			throw new RuntimeException(e);
		}
	}

	@Override
	public void onInput(KeyStroke keyStroke) {
	}
}
