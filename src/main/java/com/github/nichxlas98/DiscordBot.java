package com.github.nichxlas98;

import com.github.nichxlas98.commands.ScriptCommand;
import com.github.nichxlas98.commands.SuggestionCommand;
import com.github.nichxlas98.listeners.ModalListeners;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import javax.security.auth.login.LoginException;
import java.util.Scanner;

import static com.github.nichxlas98.utils.SendConsoleUtil.*;

public class DiscordBot {

    public static JDA bot;

    public static void main(String[] args) throws LoginException, InterruptedException {


        sendNotice("JDA Bot - Starting...");
        sendNotice("Please Enter Your Server ID:");
        // Using Scanner for Getting Input from User
        Scanner in = new Scanner(System.in);
        String guildID = in.nextLine();


        // UhCore Discord Bot by @nichxlas98
        bot = JDABuilder.createDefault("MTAxMDk2OTE1NjU0NTQyOTYxNA.GuHDA-.zpppu55tV4s-ojXrtyBLRoWqi1OuNtr0w_fiEU")
                .setActivity(Activity.watching("UhCore's Development"))
                .addEventListeners(new SuggestionCommand())
                .addEventListeners(new ScriptCommand(), new ModalListeners())
                .build().awaitReady();
        sendNotice("JDA Bot - Running!");

        Guild guild = bot.getGuildById(guildID);
        if (guild == null) {
            sendError("Invalid Server Guild.");
            return;
        }

        guild.upsertCommand("script", "Script changelogs").queue();
        guild.upsertCommand("suggest", "Submit your game/community suggestions with this command!")
                .addOption(OptionType.STRING, "title", "Your suggestion title.", true)
                .addOption(OptionType.STRING, "suggestion", "Your suggestion description.", true)
                .addOptions(
                        new OptionData(OptionType.STRING, "type", "Your suggestion type.", true)
                                .addChoice("Game Suggestion", "Game Suggestion.")
                                .addChoice("Community Suggestion", "Community Suggestion."))
                .queue();

        checkCommands();
    }

    private static void checkCommands() {
        try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }

        for (int i = 0; i < 25; i++) {
            System.out.println(" ");
        }

        Scanner input = new Scanner(System.in);
        sendNotice("Type 'HELP' and press ENTER/RETURN for a list of runnable commands.");
        sendNotice("Type 'EXIT' and press ENTER/RETURN to close this program.");
        switch (input.nextLine().toUpperCase()) {

            case "EXIT":
                sendNotice("JDA Bot - Shutting down...");
                bot.shutdownNow();
                System.exit(0);
                break;
            case "HELP":
                sendMessage("- EXIT: Closes the Program and the JDA Session.");
                checkCommands();
                break;
            default:
                sendError("Invalid Command!");
                checkCommands();

        }
    }
}
