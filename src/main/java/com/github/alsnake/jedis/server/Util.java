package com.github.thegeneralsecretary.jedis.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Util {
	public static String socketReadAll(Socket socket) throws IOException {
		BufferedReader input = new BufferedReader(
				new InputStreamReader(socket.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line;
		while (input.ready() && (line = input.readLine()) != null)
			sb.append(line).append("\r\n");
		return sb.toString();
	}
}
