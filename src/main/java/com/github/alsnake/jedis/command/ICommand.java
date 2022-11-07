package com.github.thegeneralsecretary.jedis.command;

public interface ICommand {
	void execute(CommandContext ctx);

	String getCommand();

	String getHelp();
}
