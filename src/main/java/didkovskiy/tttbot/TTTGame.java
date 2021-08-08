package didkovskiy.tttbot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TTTGame {
    private static List<String> fieldPositions; //contains game positions {:red_square:, :o:(:o:\n), :x:(:x:\n)}
    private static List<Integer> emptyPositions; //contains 1 in free pos and 0 in not free

    private enum GameStatus{WIN,LOSE,DRAW}

    private TTTGame(){
        fieldPositions = generateEmptyGameField();
        emptyPositions = generateFreePositions();
    }

    public static TTTGame initGame(){
        return new TTTGame();
    }

    //method for user to play X
    MessageEmbed playX(int move){
        EmbedBuilder embedBuilder = new EmbedBuilder();
        switch (move) {
            case 0 -> {
                fieldPositions = generateEmptyGameField();
                emptyPositions = generateFreePositions();
            }
            case 1 -> playOnPosition(0);
            case 2 -> playOnPosition(1);
            case 3 -> playOnPosition(2);
            case 4 -> playOnPosition(3);
            case 5 -> playOnPosition(4);
            case 6 -> playOnPosition(5);
            case 7 -> playOnPosition(6);
            case 8 -> playOnPosition(7);
            case 9 -> playOnPosition(8);
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

    private void playOnPosition(int pos){
        if(pos == 2 || pos == 5 || pos == 8){
            if(fieldPositions.get(pos).contains(":red_square:\n")){
                fieldPositions.set(pos, ":x:\n");
            }else throw new IllegalArgumentException();
        }else{
            if(fieldPositions.get(pos).contains(":red_square:")){
                fieldPositions.set(pos, ":x:");
            }else throw new IllegalArgumentException();
        }
        emptyPositions.set(pos,0);
    }

    //method for bot to play O
    MessageEmbed playO(){
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

    private boolean checkWin(){
        return (!fieldPositions.get(0).equals(":x:") || !fieldPositions.get(1).equals(":x:") || !fieldPositions.get(2).equals(":x:\n"))
                && (!fieldPositions.get(3).equals(":x:") || !fieldPositions.get(4).equals(":x:") || !fieldPositions.get(5).equals(":x:\n"))
                && (!fieldPositions.get(6).equals(":x:") || !fieldPositions.get(7).equals(":x:") || !fieldPositions.get(8).equals(":x:\n"))
                && (!fieldPositions.get(0).equals(":x:") || !fieldPositions.get(3).equals(":x:") || !fieldPositions.get(6).equals(":x:"))
                && (!fieldPositions.get(1).equals(":x:") || !fieldPositions.get(4).equals(":x:") || !fieldPositions.get(7).equals(":x:"))
                && (!fieldPositions.get(2).equals(":x:\n") || !fieldPositions.get(5).equals(":x:\n") || !fieldPositions.get(8).equals(":x:\n"))
                && (!fieldPositions.get(0).equals(":x:") || !fieldPositions.get(4).equals(":x:") || !fieldPositions.get(8).equals(":x:\n"))
                && (!fieldPositions.get(2).equals(":x:\n") || !fieldPositions.get(4).equals(":x:") || !fieldPositions.get(6).equals(":x:"));
    }

    private boolean checkLose(){
        return (fieldPositions.get(0).equals(":o:") && fieldPositions.get(1).equals(":o:") && fieldPositions.get(2).equals(":o:\n"))
                || (fieldPositions.get(3).equals(":o:") && fieldPositions.get(4).equals(":o:") && fieldPositions.get(5).equals(":o:\n"))
                || (fieldPositions.get(6).equals(":o:") && fieldPositions.get(7).equals(":o:") && fieldPositions.get(8).equals(":o:\n"))
                || (fieldPositions.get(0).equals(":o:") && fieldPositions.get(3).equals(":o:") && fieldPositions.get(6).equals(":o:"))
                || (fieldPositions.get(1).equals(":o:") && fieldPositions.get(4).equals(":o:") && fieldPositions.get(7).equals(":o:"))
                || (fieldPositions.get(2).equals(":o:\n") && fieldPositions.get(5).equals(":o:\n") && fieldPositions.get(8).equals(":o:\n"))
                || (fieldPositions.get(0).equals(":o:") && fieldPositions.get(4).equals(":o:") && fieldPositions.get(8).equals(":o:\n"))
                || (fieldPositions.get(2).equals(":o:\n") && fieldPositions.get(4).equals(":o:") && fieldPositions.get(6).equals(":o:"));
    }

    private boolean checkDraw(){
        List<Integer> nullList = List.of(0,0,0,0,0,0,0,0,0);
        return emptyPositions.equals(nullList);
    }

    private MessageEmbed getMessage(GameStatus status){
        EmbedBuilder embedBuilder = new EmbedBuilder();
        switch (status){
            case WIN -> {
                embedBuilder.setTitle("You Won!!!");
                embedBuilder.setDescription(":clap: :clap: :clap:");
                embedBuilder.setFooter("Congratulations!");
            }
            case LOSE -> {
                embedBuilder.setTitle("You Lose!!!");
                embedBuilder.setDescription(":-1: :poop: :-1:");
                embedBuilder.setFooter("Noob!");
            }
            case DRAW -> {
                embedBuilder.setTitle("Draw");
                embedBuilder.setDescription(":clown:");
                embedBuilder.setFooter("try again...");
            }
        }
        return embedBuilder.build();
    }

    void playIteration(@NotNull MessageReceivedEvent event, String pos) throws InterruptedException {
        int move = Integer.parseInt(pos.substring(1));
        if (event.getMessage().getContentRaw().equalsIgnoreCase(pos)) {
            event.getChannel().sendMessageEmbeds(playX(move)).queue();
            if(checkDraw()) event.getChannel().sendMessageEmbeds(getMessage(GameStatus.DRAW)).queue();
            if(checkWin()) {
                TimeUnit.MILLISECONDS.sleep(700);
                event.getChannel().sendMessageEmbeds(playO()).queue();
                if(checkLose())event.getChannel().sendMessageEmbeds(getMessage(GameStatus.LOSE)).queue();
            }else event.getChannel().sendMessageEmbeds(getMessage(GameStatus.WIN)).queue();
        }
    }

    private List<String> generateEmptyGameField(){
        List<String> fieldPositions = new ArrayList<>(9);
        fieldPositions.addAll(List.of
                (":red_square:",":red_square:",":red_square:\n"
                        ,":red_square:",":red_square:",":red_square:\n"
                        ,":red_square:",":red_square:",":red_square:\n"));
        System.out.println("Game field was created");
        return fieldPositions;
    }

    private List<Integer> generateFreePositions(){
        List<Integer> emptyPositions = new ArrayList<>(9);
        emptyPositions.addAll(List.of(1,1,1,1,1,1,1,1,1));
        return emptyPositions;
    }
}
