package com.github.nichxlas98.uhcorebot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.utils.TimeFormat;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class SuggestionCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("suggest")) {
            event.deferReply().queue(m -> m.deleteOriginal().queueAfter(1, TimeUnit.SECONDS));

            OptionMapping option1 = event.getOption("title");
            OptionMapping option2 = event.getOption("suggestion");
            OptionMapping option3 = event.getOption("type");

            if (option1 == null || option2 == null || option3 == null) {
                event.reply("An error occurred, contact the bot developer.").queue();
                return;
            }

            String suggestionTitle = option1.getAsString();
            String suggestionDesc = option2.getAsString();
            String suggestionType = option3.getAsString();

            EmbedBuilder eb = new EmbedBuilder();

            eb.setTitle(suggestionTitle);
            eb.setColor(new Color(136, 8, 8));
            eb.setAuthor(event.getUser().getName(), null, event.getUser().getAvatarUrl());
            eb.setDescription("\n\n**Suggestion**: " + suggestionDesc + "\n**Suggestion Type**: " + suggestionType + "\n\n **Time**: " + TimeFormat.RELATIVE.now()
            );


            event.getChannel().sendMessageEmbeds(eb.build()).queue();
        }

    }
}
