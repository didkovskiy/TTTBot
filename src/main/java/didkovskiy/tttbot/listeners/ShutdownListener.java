package didkovskiy.tttbot.listeners;

import didkovskiy.tttbot.dao.PlayerDAO;
import didkovskiy.tttbot.services.MessagingService;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class ShutdownListener extends ListenerAdapter {

    private final MessagingService messagingService;
    private final PlayerDAO playerDAO;

    public ShutdownListener(MessagingService messagingService, PlayerDAO playerDAO) {
        this.messagingService = messagingService;
        this.playerDAO = playerDAO;
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getMessage().getContentRaw().equalsIgnoreCase("!shutdown")) {
            event.getChannel().sendMessageEmbeds(messagingService.getMessage()).queue();
            event.getJDA().shutdown();
            playerDAO.closeConnection();
        }
    }
}
