package com.github.thegeneralsecretary.jedis.server;

public class Encoder {
	public String encode(String data, Encode encode) {
		switch (encode) {
			case NONE:
				return data;
			case SIMPLE_STRING:
				return encodeSimpleString(data);
			case BULK_STRING:
				return encodeBulkString(data);
			case ERROR:
				return encodeError(data);
		}
		return "UNSUPPORTED ENCODER TYPE";
	}

	private String encodeSimpleString(String data) {
		return "+" + data + "\r\n";
	}

	private String encodeBulkString(String data) {
		return "$" + data.length() + "\r\n" + data + "\r\n";
	}

	private String encodeError(String data) {
		return "-" + data + "\r\n";
	}
}
