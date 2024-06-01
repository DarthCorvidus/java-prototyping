package com.corvidus.prototyping;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalResizeListener;
import java.io.IOException;

public class Lanterna implements TerminalResizeListener {
	com.googlecode.lanterna.terminal.Terminal terminal = null;
	TextGraphics textGraphics = null;
	public Lanterna() {
		DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
		try {
			this.terminal = defaultTerminalFactory.createTerminal();
			this.terminal.enterPrivateMode();
			this.terminal.clearScreen();
			this.terminal.setCursorVisible(false);
			this.textGraphics = terminal.newTextGraphics();
		} catch(IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	public static void main(String[] args) {
		Lanterna lanterna = new Lanterna();
		lanterna.run();
	}

	public void run() {
		try {
			this.textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
			this.textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);
			this.textGraphics.putString(2, 1, "Lanterna Tutorial 2 - Press ESC to exit", SGR.BOLD);
			this.textGraphics.setForegroundColor(TextColor.ANSI.DEFAULT);
			this.textGraphics.setBackgroundColor(TextColor.ANSI.DEFAULT);
			this.textGraphics.putString(5, 3, "Terminal Size: ", SGR.BOLD);
			this.textGraphics.putString(5 + "Terminal Size: ".length(), 3, this.terminal.getTerminalSize().toString());
			this.terminal.flush();
			this.terminal.addResizeListener(this);
			this.textGraphics.putString(5, 4, "Last Keystroke: ", SGR.BOLD);
			this.textGraphics.putString(5 + "Last Keystroke: ".length(), 4, "<Pending>");
			this.terminal.flush();
			KeyStroke keyStroke = this.terminal.readInput();
			while (keyStroke.getKeyType() != KeyType.Escape) {
				this.textGraphics.drawLine(5, 4, this.terminal.getTerminalSize().getColumns() - 1, 4, ' ');
				this.textGraphics.putString(5, 4, "Last Keystroke: ", SGR.BOLD);
				this.textGraphics.putString(5 + "Last Keystroke: ".length(), 4, keyStroke.toString());
				this.terminal.flush();
				keyStroke = terminal.readInput();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (this.terminal != null) {
				try {
					/*
                    The close() call here will exit private mode
					 */
					this.terminal.close();
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
	
}
