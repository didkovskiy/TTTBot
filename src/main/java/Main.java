import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.apache.log4j.BasicConfigurator;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main extends ListenerAdapter {
    static List<String> fieldPositions = new ArrayList<>(9);
    static List<Integer> emptyPositions = new ArrayList<>(9);

    public static void main(String[] args) {
        BasicConfigurator.configure();
        String activity = charsetConvert("в крестики-нолики");
        JDABuilder builder = JDABuilder.createDefault("ODY4MTIxNDQxNDc0ODQ2NzYx.YPrDGA._ouf1vlczbbM49AnKCXMOjfoMac");
        builder.addEventListeners(new Main());
        assert activity != null;
        builder.setActivity(Activity.playing(activity));

        try {
            builder.build();
        } catch (LoginException e) {
            System.out.println(e.getMessage());
        }

        fieldPositions.addAll(Arrays.asList
                        (":red_square:",":red_square:",":red_square:\n"
                        ,":red_square:",":red_square:",":red_square:\n"
                        ,":red_square:",":red_square:",":red_square:\n"));

        for (int i = 0; i < fieldPositions.size(); i++) {
            if(fieldPositions.get(i).contains(":red_square:")
                    || fieldPositions.get(i).contains(":red_square:\n"))
                emptyPositions.add(i);
        }
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        try {

            if (event.getMessage().getContentRaw().equalsIgnoreCase("!shutdown")) {
                event.getJDA().shutdown();
            }
            if (event.getMessage().getContentRaw().equalsIgnoreCase("!ttt")) {
                event.getChannel().sendMessageEmbeds(playX(0)).queue();
            }
            if (event.getMessage().getContentRaw().equalsIgnoreCase("!1")) {
                event.getChannel().sendMessageEmbeds(playX(1)).queue();
                Thread.sleep(700);
                event.getChannel().sendMessageEmbeds(playO()).queue();
            }
            if (event.getMessage().getContentRaw().equalsIgnoreCase("!2")) {
                event.getChannel().sendMessageEmbeds(playX(2)).queue();
                Thread.sleep(700);
                event.getChannel().sendMessageEmbeds(playO()).queue();
            }
            if (event.getMessage().getContentRaw().equalsIgnoreCase("!3")) {
                event.getChannel().sendMessageEmbeds(playX(3)).queue();
                Thread.sleep(700);
                event.getChannel().sendMessageEmbeds(playO()).queue();
            }
            if (event.getMessage().getContentRaw().equalsIgnoreCase("!4")) {
                event.getChannel().sendMessageEmbeds(playX(4)).queue();
                Thread.sleep(700);
                event.getChannel().sendMessageEmbeds(playO()).queue();
            }
            if (event.getMessage().getContentRaw().equalsIgnoreCase("!5")) {
                event.getChannel().sendMessageEmbeds(playX(5)).queue();
                Thread.sleep(700);
                event.getChannel().sendMessageEmbeds(playO()).queue();
            }
            if (event.getMessage().getContentRaw().equalsIgnoreCase("!6")) {
                event.getChannel().sendMessageEmbeds(playX(6)).queue();
                Thread.sleep(700);
                event.getChannel().sendMessageEmbeds(playO()).queue();
            }
            if (event.getMessage().getContentRaw().equalsIgnoreCase("!7")) {
                event.getChannel().sendMessageEmbeds(playX(7)).queue();
                Thread.sleep(700);
                event.getChannel().sendMessageEmbeds(playO()).queue();
            }
            if (event.getMessage().getContentRaw().equalsIgnoreCase("!8")) {
                event.getChannel().sendMessageEmbeds(playX(8)).queue();
                Thread.sleep(700);
                event.getChannel().sendMessageEmbeds(playO()).queue();
            }
            if (event.getMessage().getContentRaw().equalsIgnoreCase("!9")) {
                event.getChannel().sendMessageEmbeds(playX(9)).queue();
                Thread.sleep(700);
                event.getChannel().sendMessageEmbeds(playO()).queue();
            }
        }catch (IllegalArgumentException | InterruptedException e){
            System.out.println(e.getMessage());
        }
    }

    public static String charsetConvert(String s) {
        try {
            return new String(s.getBytes("windows-1251"), StandardCharsets.UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static MessageEmbed playX(int move){
        EmbedBuilder embedBuilder = new EmbedBuilder();
        switch (move) {
            case 0 -> System.out.println("Game Field was created");
            case 1 -> {
                if(fieldPositions.get(0).contains(":red_square:")){
                    fieldPositions.set(0, ":x:");
                }else throw new IllegalArgumentException();
                emptyPositions.remove(emptyPositions.get(0));
            }
            case 2 -> {
                if(fieldPositions.get(1).contains(":red_square:")){
                    fieldPositions.set(1, ":x:");
                }else throw new IllegalArgumentException();
                emptyPositions.remove(emptyPositions.get(1));
            }
            case 3 -> {
                if(fieldPositions.get(2).contains(":red_square:\n")){
                    fieldPositions.set(2, ":x:\n");
                }else throw new IllegalArgumentException();
                emptyPositions.remove(emptyPositions.get(2));
            }
            case 4 -> {
                if(fieldPositions.get(3).contains(":red_square:")){
                    fieldPositions.set(3, ":x:");
                }else throw new IllegalArgumentException();
                emptyPositions.remove(emptyPositions.get(3));
            }
            case 5 -> {
                if(fieldPositions.get(4).contains(":red_square:")){
                    fieldPositions.set(4, ":x:");
                }else throw new IllegalArgumentException();
                emptyPositions.remove(emptyPositions.get(4));
            }
            case 6 -> {
                if(fieldPositions.get(5).contains(":red_square:\n")){
                    fieldPositions.set(5, ":x:\n");
                }else throw new IllegalArgumentException();
                emptyPositions.remove(emptyPositions.get(5));
            }
            case 7 -> {
                if(fieldPositions.get(6).contains(":red_square:")){
                    fieldPositions.set(6, ":x:");
                }else throw new IllegalArgumentException();
                emptyPositions.remove(emptyPositions.get(6));
            }
            case 8 -> {
                if(fieldPositions.get(7).contains(":red_square:")){
                    fieldPositions.set(7, ":x:");
                }else throw new IllegalArgumentException();
                emptyPositions.remove(emptyPositions.get(7));
            }
            case 9 -> {
                if(fieldPositions.get(8).contains(":red_square:\n")){
                    fieldPositions.set(8, ":x:\n");
                }else throw new IllegalArgumentException();
                emptyPositions.remove(emptyPositions.get(8));
            }
            default -> throw new IllegalArgumentException();
        }
        MessageBuilder messageBuilder = new MessageBuilder();
        for(String s: fieldPositions){
            messageBuilder.append(s);
        }
        embedBuilder.setTitle("Player moves:");
        embedBuilder.setDescription(messageBuilder.build().getContentRaw());
        return embedBuilder.build();
    }

    public static MessageEmbed playO(){
        EmbedBuilder embedBuilder = new EmbedBuilder();
        Random random = new Random();

        emptyPositions.forEach(integer -> System.out.print(integer+ " "));
        System.out.println();

        int i = random.nextInt(emptyPositions.size());
        fieldPositions.set(emptyPositions.get(i),
                fieldPositions.get(emptyPositions.get(i)).equals(":red_square:") ? ":o:" : ":o:\n");

        emptyPositions.remove(emptyPositions.get(i));

        emptyPositions.forEach(integer -> System.out.print(integer+ " "));
        System.out.println();

        MessageBuilder messageBuilder = new MessageBuilder();
        for(String s: fieldPositions){
            messageBuilder.append(s);
        }
        embedBuilder.setTitle("Bot moves:");
        embedBuilder.setDescription(messageBuilder.build().getContentRaw());
        return embedBuilder.build();
    }
}
