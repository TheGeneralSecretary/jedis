package com.github.thegeneralsecretary.jedis.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.github.thegeneralsecretary.jedis.command.CommandManager;

public class JedisServer {
	private static final Logger LOGGER = LoggerFactory.getLogger(JedisServer.class);
	private static final CommandManager commandManager = new CommandManager();
	private ServerSocket serverSocket;
	private String host;
	private int port;

	public JedisServer(String host, int port) {
		this.host = host;
		this.port = port;

		try {
			serverSocket = new ServerSocket(port, 255, InetAddress.getByName((host)));
			serverSocket.setReuseAddress(true);
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
	}

	public void run() {
		LOGGER.info(String.format("Jedis running on '%s':'%d'", host, port));

		while (true) {
			try {
				Socket clientSocket = serverSocket.accept();
				JedisClientHandler clientHandler = new JedisClientHandler(clientSocket);
				new Thread(clientHandler).start();
			} catch (IOException e) {
				LOGGER.error(e.getMessage());
			}
		}
	}

	private static class JedisClientHandler implements Runnable {
		private final Socket clientSocket;

		public JedisClientHandler(Socket clientSocket) {
			this.clientSocket = clientSocket;
		}

		@Override
		public void run() {
			try {
				String data = Util.socketReadAll(clientSocket);
				Request request = RequestParser.parse(data);
				Reply reply = new Reply(new Encoder(), clientSocket);
				commandManager.handle(request, reply);
				clientSocket.close();
			} catch (IOException e) {
				LOGGER.error(e.getMessage());
			} finally {
				try {
					if (clientSocket != null)
						clientSocket.close();
				} catch (IOException e) {
					LOGGER.error(e.getMessage());
				}
			}
		}
	}
}
