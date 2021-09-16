package didkovskiy.tttbot.game;

import didkovskiy.tttbot.dao.PlayerDAO;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class TTTBot extends ListenerAdapter {

    private final TTTGame game;
    private final PlayerDAO playerDAO;

    public TTTBot(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
        game = TTTGame.initGame();
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String eventAuthor = charsetConvert(event.getAuthor().getName());
        try {
            if (event.getMessage().getContentRaw().equalsIgnoreCase("!ttt")) {
                if(!playerDAO.isPlayerExists(eventAuthor))
                    playerDAO.saveNewPlayer(eventAuthor);
                event.getChannel().sendMessageEmbeds(game.playX(0)).queue();
            }

            game.playIteration(event, "!1");
            game.playIteration(event, "!2");
            game.playIteration(event, "!3");
            game.playIteration(event, "!4");
            game.playIteration(event, "!5");
            game.playIteration(event, "!6");
            game.playIteration(event, "!7");
            game.playIteration(event, "!8");
            game.playIteration(event, "!9");

            //bad practice, need to fix
        } catch (IllegalArgumentException | InterruptedException e) {
            System.out.println("Position is not empty");
            System.out.println(e.getMessage());
        }
    }

    private String charsetConvert(@NotNull String s) {
        String result = "";
        try {
            result = new String(s.getBytes(StandardCharsets.UTF_8), "windows-1251");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

}
