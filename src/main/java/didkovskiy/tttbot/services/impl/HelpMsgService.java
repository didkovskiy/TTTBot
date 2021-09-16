package didkovskiy.tttbot.services.impl;

import didkovskiy.tttbot.services.MessagingService;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;


public class HelpMsgService implements MessagingService {
    @Override
    public MessageEmbed getMessage() {
        return new EmbedBuilder()
                .setTitle("Let's start!")
                .setColor(new Color(62, 62, 227))
                .setDescription("""         
                        Type !ttt to start. :crossed_swords:
                        Type !rating to see rating of all players in the server. :trophy:
                                                
                        You always start first by typing !pos
                        where pos is a number(1-9) defining the position in the playing field as in pic below.
                                                
                        Before you play tic-tac-toe with me you should know the rules:
                        https://en.wikipedia.org/wiki/Tic-tac-toe :book:
                        """)
                .setImage("https://cdn1.savepice.ru/uploads/2021/9/7/0220a3cf108a0408dab8e892ab22f9ed-full.png")
                .build();
    }
}
