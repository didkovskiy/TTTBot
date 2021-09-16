package didkovskiy.tttbot;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class TTTBot extends ListenerAdapter {

    private final TTTGame game;

    public TTTBot() {
        game = TTTGame.initGame();
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        try {
            if (event.getMessage().getContentRaw().equalsIgnoreCase("!ttt"))
                event.getChannel().sendMessageEmbeds(game.playX(0)).queue();

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

}
