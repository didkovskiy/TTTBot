package didkovskiy.tttbot.listeners;

import didkovskiy.tttbot.services.MessagingService;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class HelpListener extends ListenerAdapter {

    private final MessagingService messagingService;

    public HelpListener(MessagingService messagingService) {
        this.messagingService = messagingService;
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getMessage().getContentRaw().equalsIgnoreCase("!help")) {
            event.getChannel().sendMessageEmbeds(messagingService.getMessage()).queue();
        }
    }
}
