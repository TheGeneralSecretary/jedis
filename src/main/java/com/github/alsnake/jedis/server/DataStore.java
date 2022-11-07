package com.github.thegeneralsecretary.jedis.server;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class DataStore {
	private static HashMap<String, String> data = new HashMap<>();

	public static void set(String key, String value) {
		data.put(key, value);
	}

	public static void set(String key, String value, int expiryms) {
		data.put(key, value);

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				data.remove(key);
			}
		}, expiryms);
	}

	public static String get(String key) {
		return data.get(key);
	}
}
