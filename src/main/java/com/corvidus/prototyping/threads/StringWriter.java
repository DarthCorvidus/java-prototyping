package com.corvidus.prototyping.threads;

import java.time.Duration;
import java.time.Instant;
/**
 * This is just an example for threads working on one resource. Printing to
 * stdout from two threads at once is possible, but, leads to interesting
 * results ;-), hence lock is used to allow only one Thread to write.
 * 
 * @author Claus-Christoph Kuethe
 */


public class StringWriter extends Thread {
	private String string;
	private Instant start = Instant.now();
	private static Object lock = new Object();
	private volatile boolean running = true;
	public StringWriter(String string) {
		this.string = string;
	}
	private void blockingPause(int milliseconds) {
		Duration deltaTime;
		while(true) {
			deltaTime = Duration.between(start, Instant.now());
			long milli = deltaTime.toMillis();
			if(milli > milliseconds) {
				this.start = Instant.now();
				return;
			}
		}
	}
	
	private void writeString() {
		char[] chars = this.string.toCharArray();
		for(char c: chars) {
			System.out.print(c);
			this.blockingPause(100);
		}
		System.out.println();
	}
	@Override
	public void run() {
		while(this.running) {
			synchronized(StringWriter.lock) {
				if(this.isInterrupted()) {
					lock.notify();
					return;
				}
				this.writeString();
				lock.notify();
				try {
					lock.wait();
				} catch (InterruptedException e) {
					//e.printStackTrace();
					return;
				}
			}
		}
	}
}
