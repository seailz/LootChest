package dev.ioyo.fivooanarchy;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class whitelist implements EventListener {

    private final FivooAnarchy plugin;

    public whitelist (FivooAnarchy plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onEvent(@NotNull GenericEvent event) {
        if (event instanceof MessageReceivedEvent) {
            if (((MessageReceivedEvent) event).getChannel().getIdLong() == 932699931179888650L) {
                if (((MessageReceivedEvent) event).getMessage().getContentRaw().contains(" " )) return;
                ((MessageReceivedEvent) event).getMessage().reply("You have been added to the whitelist!").queue();
                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                String command = "whitelist add " + ((MessageReceivedEvent) event).getMessage().getContentRaw();
                Bukkit.dispatchCommand(console, command);
                ((MessageReceivedEvent) event).getMember().getRoles().add(plugin.jda.getRoleById(932699931179888650L));

                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.sendTitle(ChatColor.BOLD + ((MessageReceivedEvent) event).getMessage().getContentRaw() + " HAS BEEN ADDED TO THE WHITELIST", "", 5, 20, 5);
                }
            }
        }
    }
}
