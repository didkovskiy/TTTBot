import didkovskiy.tttbot.TTTBot;
import didkovskiy.tttbot.listeners.HelpListener;
import didkovskiy.tttbot.listeners.RatingListener;
import didkovskiy.tttbot.listeners.ShutdownListener;
import didkovskiy.tttbot.services.impl.HelpMsgService;
import didkovskiy.tttbot.services.impl.RatingMsgService;
import didkovskiy.tttbot.services.impl.ShutdownMsgService;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import org.apache.log4j.BasicConfigurator;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class BotConfiguration {

    public static void main(String[] args) {
        //Your discord bot token to login and launch the bot
        JDABuilder builder = JDABuilder.createDefault(System.getenv("TOKEN"));
        configure(builder);
    }

    public static void configure(JDABuilder builder) {
        builder.addEventListeners(new TTTBot());
        builder.addEventListeners(new HelpListener(new HelpMsgService()));
        builder.addEventListeners(new ShutdownListener(new ShutdownMsgService()));
        builder.addEventListeners(new RatingListener(new RatingMsgService()));
        BasicConfigurator.configure();
        String activity = charsetConvert("в крестики-нолики");
        builder.setActivity(Activity.playing(activity));

        try {
            builder.build();
        } catch (LoginException e) {
            System.out.println(e.getMessage());
        }
    }

    @NotNull
    public static String charsetConvert(@NotNull String s) {
        String result = "";
        try {
            result = new String(s.getBytes("windows-1251"), StandardCharsets.UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
