package com.github.thegeneralsecretary.jedis.server;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Jedis {
	private static final Logger LOGGER = LoggerFactory.getLogger(Jedis.class);

	public static void main(String[] args) throws ParseException {
		String host = "localhost";
		int port = 6378;

		Options options = new Options();
		options.addOption("help", "help", false, "help");
		options.addOption("host", "host", true, "host");
		options.addOption("port", "port", true, "port");

		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = parser.parse(options, args);

		if (cmd.hasOption("help")) {
			System.out.println(options);
			return;
		}

		if (cmd.hasOption("host"))
			host = cmd.getOptionValue("host");

		if (cmd.hasOption("port"))
			port = Integer.parseInt(cmd.getOptionValue("port"));

		JedisServer jedisServer = new JedisServer(host, port);
		jedisServer.run();
	}
}
