package com.github.thegeneralsecretary.jedis.commands;

import com.github.thegeneralsecretary.jedis.command.CommandContext;
import com.github.thegeneralsecretary.jedis.command.ICommand;
import com.github.thegeneralsecretary.jedis.server.Encode;
import com.github.thegeneralsecretary.jedis.server.Message;

public class EchoCommand implements ICommand {
	@Override
	public void execute(CommandContext ctx) {
		if (ctx.getArgs().isEmpty()) {
			ctx.getReply().reply(Message.wrongArgumentCount(ctx.getCmd()), Encode.ERROR);
			return;
		}
		ctx.getReply().reply(String.join(" ", ctx.getArgs()), Encode.BULK_STRING);
	}

	@Override
	public String getCommand() {
		return "echo";
	}

	@Override
	public String getHelp() {
		return "Returns Message";
	}
}
