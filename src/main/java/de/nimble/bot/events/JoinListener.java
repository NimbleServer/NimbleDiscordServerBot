package de.nimble.bot.events;

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class JoinListener extends ListenerAdapter {

	@Override
	public void onGuildMemberJoin(GuildMemberJoinEvent event) {
		Role role = event.getGuild().getRolesByName("user", true).get(0);
		event.getGuild().addRoleToMember(event.getMember(), role).queue();
	}

}
