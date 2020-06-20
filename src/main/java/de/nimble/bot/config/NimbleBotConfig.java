package de.nimble.bot.config;

public class NimbleBotConfig extends NimbleConfig{

	public NimbleBotConfig() {
    super("D:\\Development\\Workspaces\\Java\\NimbleServer\\NimbleDiscordServerBot\\config\\nimblebot.nimble");
	}

	public String getToken() {
		return read().get("token");
	}

}
