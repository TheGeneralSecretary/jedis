package com.github.thegeneralsecretary.jedis.commands;

import com.github.thegeneralsecretary.jedis.command.CommandContext;
import com.github.thegeneralsecretary.jedis.command.ICommand;
import com.github.thegeneralsecretary.jedis.server.DataStore;
import com.github.thegeneralsecretary.jedis.server.Encode;
import com.github.thegeneralsecretary.jedis.server.Message;

public class SetCommand implements ICommand {
	@Override
	public void execute(CommandContext ctx) {
		if (ctx.getArgs().size() <= 1) {
			ctx.getReply().reply(Message.wrongArgumentCount(ctx.getCmd()), Encode.ERROR);
			return;
		}

		String key = ctx.getArgs().get(0);
		String value = ctx.getArgs().get(1);

		if (ctx.getArgs().size() > 2) {
			String behavior = ctx.getArgs().get(2);
			if (behavior.toLowerCase().equals("px")) {
				try {
					String timems = ctx.getArgs().get(3);
					DataStore.set(key, value, Integer.parseInt(timems));
				} catch (IndexOutOfBoundsException e) {
					ctx.getReply().reply(Message.syntaxError(), Encode.ERROR);
					return;
				}
			}
		} else {
			DataStore.set(key, value);
		}

		ctx.getReply().reply("OK", Encode.SIMPLE_STRING);
	}

	@Override
	public String getCommand() {
		return "set";
	}

	@Override
	public String getHelp() {
		return "Set key to hold the string value";
	}
}
