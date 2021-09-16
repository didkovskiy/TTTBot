package didkovskiy.tttbot.services.impl;

import didkovskiy.tttbot.services.MessagingService;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public class ShutdownMsgService implements MessagingService {
    @Override
    public MessageEmbed getMessage() {
        return new EmbedBuilder()
                .setTitle("Goodbye... :broken_heart:")
                .setImage("https://cdn131.picsart.com/319099854243201.jpg")
                .setColor(new Color(229, 28, 145))
                .setFooter("I'll miss you")
                .build();
    }
}
