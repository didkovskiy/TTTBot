package didkovskiy.tttbot.listeners;

import didkovskiy.tttbot.services.MessagingService;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class ShutdownListener extends ListenerAdapter {

    private final MessagingService messagingService;

    public ShutdownListener(MessagingService messagingService) {
        this.messagingService = messagingService;
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getMessage().getContentRaw().equalsIgnoreCase("!shutdown")) {
            event.getChannel().sendMessageEmbeds(messagingService.getMessage()).queue();
            event.getJDA().shutdown();
        }
    }
}
