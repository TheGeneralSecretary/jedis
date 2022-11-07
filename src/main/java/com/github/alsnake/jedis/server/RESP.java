package com.github.thegeneralsecretary.jedis.server;

public class RESP {
	public static String NullBulkString() {
		return "$-1\r\n";
	}
}
