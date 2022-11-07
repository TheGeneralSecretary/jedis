package com.github.thegeneralsecretary.jedis.command;

import java.util.List;

import com.github.thegeneralsecretary.jedis.server.Reply;

public class CommandContext {
	String cmd;
	private final List<String> args;
	private final Reply reply;

	public CommandContext(String cmd, List<String> args, Reply reply) {
		this.cmd = cmd;
		this.args = args;
		this.reply = reply;
	}

	public String getCmd() {
		return this.cmd;
	}

	public List<String> getArgs() {
		return this.args;
	}

	public Reply getReply() {
		return this.reply;
	}
}
