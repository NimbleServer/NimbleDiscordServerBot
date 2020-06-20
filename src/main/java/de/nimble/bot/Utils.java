package de.nimble.bot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public class Utils {

  public static final boolean DEBUG = true;

  public static MessageEmbed errorEmbed(String description) {
    return new EmbedBuilder()
        .setAuthor("Error")
        .setDescription(description)
        .setColor(Color.RED)
        .build();
  }
}
