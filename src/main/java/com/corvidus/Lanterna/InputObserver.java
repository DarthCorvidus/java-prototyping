package com.corvidus.Lanterna;
import com.googlecode.lanterna.input.KeyStroke;
public interface InputObserver {
	public void onInput(KeyStroke keyStroke);
}
