package de.nimble.bot.bugreports;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class BugReport {

  private String messageId;
  private String name;
  private StringBuilder description;

  public BugReport(String[] args) {
    this.name = args[0];
    this.description = new StringBuilder();
    for (int i = 1; i < args.length; i++) {
      description.append(args[i]).append(" ");
    }
  }

  public MessageEmbed build() {
    EmbedBuilder bugReportEmbed =
        new EmbedBuilder().setAuthor(getName()).setDescription(getDescription());
    return bugReportEmbed.build();
  }

  public void setMessageId(String messageId) {
    this.messageId = messageId;
  }

  public String getMessageId() {
    return this.messageId;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setDescription(String description) {
    this.description = new StringBuilder().append(description);
  }

  public String getDescription() {
    return this.description.toString();
  }
}
