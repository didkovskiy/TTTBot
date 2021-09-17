package didkovskiy.tttbot.services.impl;

import didkovskiy.tttbot.dao.PlayerDAO;
import didkovskiy.tttbot.services.MessagingService;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;


public class RatingMsgService implements MessagingService {

    private final PlayerDAO playerDAO;

    public RatingMsgService(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }

    @Override
    public MessageEmbed getMessage() {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        Map<String, Integer> ratingMap = playerDAO.getRatingMap();

        LinkedHashMap<String, Integer> sortedRating = ratingMap.entrySet().stream().
                sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        embedBuilder.setTitle("Rating :trophy:");

        int count = 0;
        for (Map.Entry<String, Integer> entry : sortedRating.entrySet()) {
            if (count < 3) {
                if (count == 0) embedBuilder.appendDescription(":first_place_medal:  ");
                if (count == 1) embedBuilder.appendDescription(":second_place_medal:  ");
                if (count == 2) embedBuilder.appendDescription(":third_place_medal:  ");
            } else embedBuilder.appendDescription(":poop:  ");
            embedBuilder.appendDescription(entry.getKey() + " ");
            embedBuilder.appendDescription(entry.getValue() + "\n");
            count++;
        }

        embedBuilder.setColor(new Color(255, 236, 0));
        return embedBuilder.build();
    }
}
