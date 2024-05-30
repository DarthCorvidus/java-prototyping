package com.corvidus.prototyping;

import com.corvidus.prototyping.threads.StringWriter;
public class Threads {
	public static void main(String[] args) {
		StringWriter sw01 = new StringWriter("I like dogs.");
		StringWriter sw02 = new StringWriter("I like cats.");
		sw01.start();
		sw02.start();
    }
}
