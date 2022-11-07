package com.github.thegeneralsecretary.jedis.command;

import java.util.ArrayList;
import java.util.List;

import com.github.thegeneralsecretary.jedis.commands.EchoCommand;
import com.github.thegeneralsecretary.jedis.commands.GetCommand;
import com.github.thegeneralsecretary.jedis.commands.PingCommand;
import com.github.thegeneralsecretary.jedis.commands.SetCommand;
import com.github.thegeneralsecretary.jedis.server.Encode;
import com.github.thegeneralsecretary.jedis.server.Message;
import com.github.thegeneralsecretary.jedis.server.Reply;
import com.github.thegeneralsecretary.jedis.server.Request;

public class CommandManager {
	private static List<ICommand> commands = new ArrayList<>();

	public CommandManager() {
		addCommand(new PingCommand());
		addCommand(new EchoCommand());
		addCommand(new SetCommand());
		addCommand(new GetCommand());
	}

	public void addCommand(ICommand command) {
		if (!findCommand(command))
			commands.add(command);
	}

	private boolean findCommand(ICommand command) {
		return getCommand(command.getCommand()) != null;
	}

	private ICommand getCommand(String command) {
		for (ICommand cmd : commands)
			if (cmd.getCommand().equals(command))
				return cmd;
		return null;
	}

	public void handle(Request request, Reply reply) {
		if (request.getCmd() != null) {
			ICommand command = getCommand(request.getCmd().toLowerCase());
			if (command != null) {
				command.execute(new CommandContext(request.getCmd(), request.getArgs(), reply));
			} else {
				reply.reply(Message.unknownCommand(request.getCmd(), String.join(" ", request.getArgs())),
						Encode.ERROR);
			}
		}
	}
}
