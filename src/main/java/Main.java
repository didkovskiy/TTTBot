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
    static List<String> fieldPositions; //contains game positions {:red_square:, :o:(:o:\n), :x:(:x:\n)}
    static List<Integer> emptyPositions; //contains 1 in free pos and 0 in not free

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
        fieldPositions = generateEmptyGameField();
        emptyPositions = generateFreePositions();
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
            playIteration(event,"!1");
            playIteration(event,"!2");
            playIteration(event,"!3");
            playIteration(event,"!4");
            playIteration(event,"!5");
            playIteration(event,"!6");
            playIteration(event,"!7");
            playIteration(event,"!8");
            playIteration(event,"!9");

        }catch (IllegalArgumentException | InterruptedException e){
            System.out.println("Position is not empty");
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

    //method for user to play X
    public static MessageEmbed playX(int move){
        EmbedBuilder embedBuilder = new EmbedBuilder();
        switch (move) {
            case 0 -> {
                fieldPositions = generateEmptyGameField();
                emptyPositions = generateFreePositions();
            }
            case 1 -> {
                if(fieldPositions.get(0).contains(":red_square:")){
                    fieldPositions.set(0, ":x:");
                }else throw new IllegalArgumentException();
                emptyPositions.set(0,0);
            }
            case 2 -> {
                if(fieldPositions.get(1).contains(":red_square:")){
                    fieldPositions.set(1, ":x:");
                }else throw new IllegalArgumentException();
                emptyPositions.set(1,0);
            }
            case 3 -> {
                if(fieldPositions.get(2).contains(":red_square:\n")){
                    fieldPositions.set(2, ":x:\n");
                }else throw new IllegalArgumentException();
                emptyPositions.set(2,0);
            }
            case 4 -> {
                if(fieldPositions.get(3).contains(":red_square:")){
                    fieldPositions.set(3, ":x:");
                }else throw new IllegalArgumentException();
                emptyPositions.set(3,0);
            }
            case 5 -> {
                if(fieldPositions.get(4).contains(":red_square:")){
                    fieldPositions.set(4, ":x:");
                }else throw new IllegalArgumentException();
                emptyPositions.set(4,0);
            }
            case 6 -> {
                if(fieldPositions.get(5).contains(":red_square:\n")){
                    fieldPositions.set(5, ":x:\n");
                }else throw new IllegalArgumentException();
                emptyPositions.set(5,0);
            }
            case 7 -> {
                if(fieldPositions.get(6).contains(":red_square:")){
                    fieldPositions.set(6, ":x:");
                }else throw new IllegalArgumentException();
                emptyPositions.set(6,0);
            }
            case 8 -> {
                if(fieldPositions.get(7).contains(":red_square:")){
                    fieldPositions.set(7, ":x:");
                }else throw new IllegalArgumentException();
                emptyPositions.set(7,0);
            }
            case 9 -> {
                if(fieldPositions.get(8).contains(":red_square:\n")){
                    fieldPositions.set(8, ":x:\n");
                }else throw new IllegalArgumentException();
                emptyPositions.set(8,0);
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

    //method for bot to play O
    public static MessageEmbed playO(){
        EmbedBuilder embedBuilder = new EmbedBuilder();
        Random random = new Random();
        List<Integer> freeIndexes = new ArrayList<>(); //contains indexes with free positions

        for (int i = 0; i < emptyPositions.size(); i++) {
            if(emptyPositions.get(i) == 1) freeIndexes.add(i);
        }

        int randomIndex = freeIndexes.get(random.nextInt(freeIndexes.size())); // get  random elem with a free position
        if(randomIndex == 2 || randomIndex == 5 || randomIndex == 8)
        fieldPositions.set(randomIndex,":o:\n");
        else fieldPositions.set(randomIndex,":o:");
        emptyPositions.set(randomIndex,0);

        MessageBuilder messageBuilder = new MessageBuilder();
        for(String s: fieldPositions){
            messageBuilder.append(s);
        }
        embedBuilder.setTitle("Bot moves:");
        embedBuilder.setDescription(messageBuilder.build().getContentRaw());
        return embedBuilder.build();
    }

    public static List<String> generateEmptyGameField(){
        List<String> fieldPositions = new ArrayList<>(9);
        fieldPositions.addAll(Arrays.asList
                        (":red_square:",":red_square:",":red_square:\n"
                        ,":red_square:",":red_square:",":red_square:\n"
                        ,":red_square:",":red_square:",":red_square:\n"));
        System.out.println("Game field was created");
        return fieldPositions;
    }

    public static List<Integer> generateFreePositions(){
        List<Integer> emptyPositions = new ArrayList<>(9);
        emptyPositions.addAll(Arrays.asList(1,1,1,1,1,1,1,1,1));
        return emptyPositions;
    }

    public static boolean checkWin(){
        return (!fieldPositions.get(0).equals(":x:") || !fieldPositions.get(1).equals(":x:") || !fieldPositions.get(2).equals(":x:\n"))
                && (!fieldPositions.get(3).equals(":x:") || !fieldPositions.get(4).equals(":x:") || !fieldPositions.get(5).equals(":x:\n"))
                && (!fieldPositions.get(6).equals(":x:") || !fieldPositions.get(7).equals(":x:") || !fieldPositions.get(8).equals(":x:\n"))
                && (!fieldPositions.get(0).equals(":x:") || !fieldPositions.get(3).equals(":x:") || !fieldPositions.get(6).equals(":x:"))
                && (!fieldPositions.get(1).equals(":x:") || !fieldPositions.get(4).equals(":x:") || !fieldPositions.get(7).equals(":x:"))
                && (!fieldPositions.get(2).equals(":x:\n") || !fieldPositions.get(5).equals(":x:\n") || !fieldPositions.get(8).equals(":x:\n"))
                && (!fieldPositions.get(0).equals(":x:") || !fieldPositions.get(4).equals(":x:") || !fieldPositions.get(8).equals(":x:\n"))
                && (!fieldPositions.get(2).equals(":x:\n") || !fieldPositions.get(4).equals(":x:") || !fieldPositions.get(6).equals(":x:"));
    }

    public static boolean checkLose(){
        return (fieldPositions.get(0).equals(":o:") && fieldPositions.get(1).equals(":o:") && fieldPositions.get(2).equals(":o:\n"))
                || (fieldPositions.get(3).equals(":o:") && fieldPositions.get(4).equals(":o:") && fieldPositions.get(5).equals(":o:\n"))
                || (fieldPositions.get(6).equals(":o:") && fieldPositions.get(7).equals(":o:") && fieldPositions.get(8).equals(":o:\n"))
                || (fieldPositions.get(0).equals(":o:") && fieldPositions.get(3).equals(":o:") && fieldPositions.get(6).equals(":o:"))
                || (fieldPositions.get(1).equals(":o:") && fieldPositions.get(4).equals(":o:") && fieldPositions.get(7).equals(":o:"))
                || (fieldPositions.get(2).equals(":o:\n") && fieldPositions.get(5).equals(":o:\n") && fieldPositions.get(8).equals(":o:\n"))
                || (fieldPositions.get(0).equals(":o:") && fieldPositions.get(4).equals(":o:") && fieldPositions.get(8).equals(":o:\n"))
                || (fieldPositions.get(2).equals(":o:\n") && fieldPositions.get(4).equals(":o:") && fieldPositions.get(6).equals(":o:"));
    }

    public static boolean checkDraw(){
        List<Integer> nullList = Arrays.asList(0,0,0,0,0,0,0,0,0);
        return emptyPositions.equals(nullList);
    }

    public static MessageEmbed getWinMessage(){
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("You Won!!!");
        embedBuilder.setDescription(":clap: :clap: :clap:");
        embedBuilder.setFooter("Congratulations!");
        return embedBuilder.build();
    }

    public static MessageEmbed getLoseMessage(){
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("You Lose!!!");
        embedBuilder.setDescription(":-1: :poop: :-1:");
        embedBuilder.setFooter("Noob!");
        return embedBuilder.build();
    }

    public static MessageEmbed getDrawMessage(){
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("Draw");
        embedBuilder.setDescription(":clown:");
        embedBuilder.setFooter("try again...");
        return embedBuilder.build();
    }

    public static void playIteration(@NotNull MessageReceivedEvent event, String pos) throws InterruptedException {
        int move = Integer.parseInt(pos.substring(1));
        if (event.getMessage().getContentRaw().equalsIgnoreCase(pos)) {
            event.getChannel().sendMessageEmbeds(playX(move)).queue();
            if(checkDraw()) event.getChannel().sendMessageEmbeds(getDrawMessage()).queue();
            if(checkWin()) {
                Thread.sleep(700);
                event.getChannel().sendMessageEmbeds(playO()).queue();
                if(checkLose())event.getChannel().sendMessageEmbeds(getLoseMessage()).queue();
            }else event.getChannel().sendMessageEmbeds(getWinMessage()).queue();
        }
    }
}
