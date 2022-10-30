package com.github.nichxlas98.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.TimeFormat;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class ModalListeners extends ListenerAdapter {
    @Override
    public void onModalInteraction(@NotNull ModalInteractionEvent event) {
        if (event.getModalId().equalsIgnoreCase("script-modal")) {

            String version = event.getValue("script-version").getAsString();
            String changes = event.getValue("script-changes").getAsString();


            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle("Server Changelog!");
            eb.setColor(new Color(51, 215, 215));
            eb.setAuthor(event.getUser().getName(), null, event.getUser().getAvatarUrl());
            eb.setDescription("\n\n**Version**: " + version + "\n**Changelog**: " + changes + "\n\n **Time**: " + TimeFormat.DATE_SHORT.now()
            );

            event.replyEmbeds(eb.build()).queue();
            // event.getChannel().sendMessageEmbeds(eb.build()).queue();
        }
    }
}
