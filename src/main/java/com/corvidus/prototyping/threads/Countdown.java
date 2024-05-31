package com.corvidus.prototyping.threads;

import java.time.Duration;
import java.time.Instant;

public class Countdown extends Thread {
	private Instant start = Instant.now();
	private int milliseconds = 0;
	private CountdownObserver countdownObserver;
	public Countdown(int milliseconds) {
		this.milliseconds = milliseconds;
	}
	
	public void setCountdownObserver(CountdownObserver countdownObserver) {
		this.countdownObserver = countdownObserver;
	}
	
	@Override
	public void run() {
		if(this.countdownObserver == null) {
			return;
		}
		Duration deltaTime;
		while(true) {
			deltaTime = Duration.between(start, Instant.now());
			long milli = deltaTime.toMillis();
			if(milli > this.milliseconds && this.countdownObserver != null) {
				this.countdownObserver.onEnd(this);
			return;
			}
			if(this.isInterrupted()) {
				return;
			}
		}

	}
}
