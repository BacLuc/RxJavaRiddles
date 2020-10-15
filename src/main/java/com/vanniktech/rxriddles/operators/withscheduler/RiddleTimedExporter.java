package com.vanniktech.rxriddles.operators.withscheduler;

import java.time.Duration;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Timed;

public class RiddleTimedExporter {

	/**
	 * Collect the data emitted by dataObservable, emit a DataFile with timestamp after each interval, do this work on the given scheduler
	 * <p>
	 * Use case: we want to export the data received every interval to e.g. a file.
	 */
	public static Observable<Timed<DataFile>> solve(Observable<Data> dataObservable, Duration interval, Scheduler scheduler) {
		return null;
	}

	public static class Data {
		private final String data;

		public Data(String data) {
			this.data = data;
		}

		public String getData() {
			return data;
		}
	}

	public static class DataFile {
		private final List<Data> dataToExport;

		public DataFile(List<Data> dataToExport) {
			this.dataToExport = dataToExport;
		}

		public List<Data> getDataToExport() {
			return dataToExport;
		}
	}
}
