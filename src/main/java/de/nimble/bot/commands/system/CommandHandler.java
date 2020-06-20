package de.nimble.bot.commands.system;

import de.nimble.bot.logger.Logger;
import net.dv8tion.jda.api.entities.Message;

import java.util.HashMap;

public class CommandHandler {

  private String prefix;
  private Message message;

  private HashMap<String, NimbleCommand> commands;

  private static CommandHandler builder;

  public static CommandHandler getInstance() {
    if (builder == null) {
      builder = new CommandHandler();
    }

    return builder;
  }

  private CommandHandler() {
    this.commands = new HashMap<>();
  }

  public boolean isCommand(String label) {
    return commands.keySet().contains(label);
  }

  public void registerCommand(String label, NimbleCommand cmd) {
    this.commands.put(label, cmd);
  }

  public NimbleCommand getCommand(String label) {
    return commands.get(label);
  }

  public String[] getArgs(String label) {
    try {
      String[] args = new String[splitAtEmpty().length - 1];
      for (int i = 0; i < splitAtEmpty().length - 1; i++) {
        if (i == splitAtEmpty().length - 1) {
          args[i] = splitAtEmpty()[i];
        } else {
          args[i] = splitAtEmpty()[i + 1];
        }
      }
      return args;
    } catch (NullPointerException e) {
      Logger.getInstance().log("No args in getArgs");
    }

    return new String[] {};
  }

  private String[] splitAtEmpty() {
    try {
      String[] splitAtPrefix = message.getContentRaw().split(prefix);
      String msgWithoutPrefix = splitAtPrefix[1];
      return msgWithoutPrefix.split(" ");
    } catch (NullPointerException e) {
      System.out.println("No args in splitAtEmpty");
    }
    return new String[] {};
  }

  public HashMap<String, NimbleCommand> getCommands() {
    return this.commands;
  }

  public CommandHandler setPrefix(String prefix) {
    this.prefix = prefix;
    return this;
  }

  public String getPrefix() {
    return this.prefix;
  }

  public void setMessage(Message message) {
    this.message = message;
  }

  public Message getMessage() {
    return this.message;
  }
}
