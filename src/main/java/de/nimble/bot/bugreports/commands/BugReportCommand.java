package de.nimble.bot.bugreports.commands;

import de.nimble.bot.Utils;
import de.nimble.bot.bugreports.BugReport;
import de.nimble.bot.bugreports.BugReportSql;
import de.nimble.bot.commands.system.NimbleCommand;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class BugReportCommand implements NimbleCommand {

  @Override
  public void onCommand(Message message, String[] args) {
    if (args.length < 2) {
      message
          .getChannel()
          .sendMessage(
              Utils.errorEmbed(
                  "Too few arguments for Bugreport command! You need at least two arguments for the name and a description!"))
          .queue();
      return;
    }

    BugReport report = new BugReport(args);
    TextChannel channel = message.getJDA().getTextChannelsByName("bug-reports", true).get(0);

    message
        .getChannel()
        .sendMessage("Bug report created!")
        .queue(
            msg -> {
              channel
                  .sendMessage(report.build())
                  .queue(
                      embed -> {
                        report.setMessageId(embed.getId());
                        BugReportSql.getInstance()
                            .createBugReport(
                                embed.getId(), report.getName(), report.getDescription());
                        embed.addReaction("✅").queue();
                      });
            });
  }
}
