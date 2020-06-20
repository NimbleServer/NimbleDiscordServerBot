package de.nimble.bot;

import de.nimble.bot.bugreports.commands.BugReportCommand;
import de.nimble.bot.config.NimbleBotConfig;
import de.nimble.bot.events.EmojiReactEvent;
import de.nimble.bot.help.commands.HelpCommand;
import de.nimble.bot.commands.system.CommandHandler;
import de.nimble.bot.events.JoinListener;
import de.nimble.bot.events.MessageReceivedListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class NimbleBot {

  public static void main(String[] args) {
    loadCommands();
    NimbleBotConfig nbc = new NimbleBotConfig();
    JDA jda = null;
    JDABuilder builder = new JDABuilder();
    builder.setToken(nbc.getToken());
    builder.setActivity(Activity.listening("to you"));
    builder.setStatus(OnlineStatus.ONLINE);
    try {
      jda = builder.build();
    } catch (LoginException e) {
      e.printStackTrace();
    }
    if (jda != null) {
      jda.addEventListener(new MessageReceivedListener());
      jda.addEventListener(new JoinListener());
      jda.addEventListener(new EmojiReactEvent());
    }
  }

  private static void loadCommands() {
    CommandHandler ch = CommandHandler.getInstance().setPrefix("!");
    ch.registerCommand("help", new HelpCommand());
    ch.registerCommand("bugreport", new BugReportCommand());
  }
}
