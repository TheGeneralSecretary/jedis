package com.github.thegeneralsecretary.jedis.server;

import java.util.ArrayList;

public class RequestParser {
	public static Request parse(String data) {
		String cmd = null;
		ArrayList<String> args = new ArrayList<>();

		int i = 0;
		while (i < data.length()) {
			StringBuilder sb = new StringBuilder();

			if (data.charAt(i) == '$') {
				while (data.charAt(i++) != '\n')
					;
				while (data.charAt(i) != '\r')
					sb.append(data.charAt(i++));

				if (cmd != null)
					args.add(sb.toString());
				else
					cmd = sb.toString();
			}
			i++;
		}

		return new Request(cmd, args);
	}
}
