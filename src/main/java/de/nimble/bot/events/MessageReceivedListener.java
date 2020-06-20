package de.nimble.bot.events;

import de.nimble.bot.commands.system.CommandHandler;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageReceivedListener extends ListenerAdapter {

  @Override
  public void onMessageReceived(MessageReceivedEvent event) {
    if (event.getAuthor().isBot()) {
      return;
    }

    // COMMAND HANDLER
    String label = getLabel(event.getMessage());
    if (CommandHandler.getInstance().isCommand(label)) {
      CommandHandler.getInstance().setMessage(event.getMessage());

      String[] args = CommandHandler.getInstance().getArgs(label);

      CommandHandler.getInstance().getCommand(label).onCommand(event.getMessage(), args);
    }
  }

  public String getLabel(Message message) {
    String[] splitAtPrefix =
        message.getContentRaw().split(CommandHandler.getInstance().getPrefix());
    String[] labelSplit = splitAtPrefix[1].split(" ");
    return labelSplit[0];
  }
}
