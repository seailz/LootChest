package dev.ioyo.fivooanarchy;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Embed {

    public Embed(String title, Color color, String text, String author, String authorimage, TextChannel channel) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(color);
        embed.setTitle(title);
        embed.setAuthor(author, null, authorimage);
        embed.setDescription(text);
        MessageEmbed message1 = embed.build();
        channel.sendMessageEmbeds(message1).queue( message -> message.delete().queueAfter(2, TimeUnit.HOURS) );
    }

}
