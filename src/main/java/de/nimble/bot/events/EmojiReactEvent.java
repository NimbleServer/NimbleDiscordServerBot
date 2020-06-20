package de.nimble.bot.events;

import de.nimble.bot.bugreports.BugReportSql;
import de.nimble.bot.logger.Logger;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class EmojiReactEvent extends ListenerAdapter {

  public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent event) {
    if (event.getUser().isBot()) {
      return;
    }

    Logger.getInstance().log("messageId: " + event.getMessageId());
    Logger.getInstance()
        .log("is bugreport: " + (BugReportSql.getInstance().isBugReport(event.getMessageId())));
    if (BugReportSql.getInstance().isBugReport(event.getMessageId())) {
      Logger.getInstance().log("is bugreport");
      Logger.getInstance().log(event.getReactionEmote().getName());
      if (event
          .getReactionEmote()
          .equals(MessageReaction.ReactionEmote.fromUnicode("✅", event.getJDA()))) {
        BugReportSql.getInstance().deleteBugReport(event.getMessageId());
        event.getChannel().deleteMessageById(event.getMessageId()).queue();
      }
    }
  }
}
