package de.nimble.bot.commands.system;

import net.dv8tion.jda.api.entities.Message;

public interface NimbleCommand {

  void onCommand(Message message, String[] args);
}
