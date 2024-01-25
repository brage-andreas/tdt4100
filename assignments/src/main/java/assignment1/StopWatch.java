package assignment1;

import java.util.ArrayList;

public class StopWatch {
	/* [ start1, slutt1, start2, slutt2, start3, ... ] */
	private ArrayList<Integer> laps = new ArrayList<>();
	private boolean stopped = false;
	private int ticks = 0;

	public boolean isStarted() {
		return this.laps.size() != 0;
	}

	public boolean isStopped() {
		return this.stopped;
	}

	public int getTicks() {
		return this.ticks;
	}

	public int getTime() {
		if (!this.hasLaps()) {
			return -1;
		}

		int currentTicks = this.isStopped() ? this.lapsAt(-1) : this.getTicks();

		return currentTicks - this.lapsAt(0);
	}

	public int getLapTime() {
		if (this.isStopped()) {
			return 0;
		}

		if (!this.isStarted()) {
			return -1;
		}

		if (this.hasUnfinishedLaps()) {
			// nå - lapstart
			return ticks - this.lapsAt(-1);
		}

		// lapslutt - lapstart
		return this.lapsAt(-2) - this.lapsAt(-1);
	}

	public int getLastLapTime() {
		if (this.laps.size() < 2) {
			return -1;
		}

		int unfinishedLapAdjustment = this.hasUnfinishedLaps() ? 1 : 0;

		int lastLapEnd = this.lapsAt(-1 - unfinishedLapAdjustment);
		int lastLapStart = this.lapsAt(-2 - unfinishedLapAdjustment);

		return lastLapEnd - lastLapStart;
	}

	public void tick(int ticks) {
		this.ticks += ticks;
	}

	public void start() {
		this.laps.add(this.getTicks());
	}

	public void lap() {
		if (!this.hasLaps()) {
			this.laps.add(0);
		}

		this.laps.add(this.getTicks()); // avslutter forrige
		this.laps.add(this.getTicks()); // starter neste
	}

	public void stop() {
		this.stopped = true;

		if (this.hasLaps() && this.hasUnfinishedLaps()) {
			this.laps.add(this.getTicks());
		}
	}

	private int lapsAt(int index) {
		int adjustedIndex = index < 0 ? this.laps.size() + index : index;

		return this.laps.get(adjustedIndex);
	}

	private boolean hasLaps() {
		return this.laps.size() != 0;
	}

	private boolean hasUnfinishedLaps() {
		return this.laps.size() % 2 != 0;
	}

	public String toString() {
		return String.format("StopWatch {stopped: %s, ticks: %s, laps: %s}", this.stopped, this.ticks, this.laps);
	}

	public static void main(String[] args) {
		StopWatch stopWatch = new StopWatch();

		System.out.println(stopWatch);
		stopWatch.tick(3);
		System.out.println(stopWatch);
		stopWatch.start();
		System.out.println(stopWatch);
		stopWatch.tick(6);
		System.out.println(stopWatch);
		stopWatch.lap();
		System.out.println(stopWatch);
		stopWatch.tick(6);
		System.out.println(stopWatch);
		stopWatch.stop();
		System.out.println(stopWatch);
	}
}
