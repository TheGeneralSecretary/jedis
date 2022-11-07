package com.github.thegeneralsecretary.jedis.server;

import java.io.IOException;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Reply {
	private static final Logger LOGGER = LoggerFactory.getLogger(Reply.class);
	private Encoder encoder;
	private Socket clientSocket;

	public Reply(Encoder encoder, Socket clientSocket) {
		this.encoder = encoder;
		this.clientSocket = clientSocket;
	}

	public void reply(String data, Encode type) {
		String encoded = this.encoder.encode(data, type);
		try {
			this.clientSocket.getOutputStream().write(encoded.getBytes());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
	}
}
