import didkovskiy.tttbot.TTTBot;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import org.apache.log4j.BasicConfigurator;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        JDABuilder builder = JDABuilder.createDefault("ODY4MTIxNDQxNDc0ODQ2NzYx.YPrDGA._ouf1vlczbbM49AnKCXMOjfoMac");
        configure(builder);
    }

    @NotNull
    public static String charsetConvert(@NotNull String s) {
        String result = "";
        try {
            result =  new String(s.getBytes("windows-1251"), StandardCharsets.UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void configure(JDABuilder builder){
        builder.addEventListeners(new TTTBot());
        BasicConfigurator.configure();
        String activity = charsetConvert("в крестики-нолики");
        builder.setActivity(Activity.playing(activity));

        try {
            builder.build();
        } catch (LoginException e) {
            System.out.println(e.getMessage());
        }
    }
}
