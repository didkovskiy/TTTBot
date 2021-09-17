# Discord bot TTTBot
This is a simple implementation of the game
[tic-tac-toe](https://en.wikipedia.org/wiki/Tic-tac-toe)
through emoticons in discord. You can try to beat the 
bot to get points for the victory and compete with other
players on the server. 

## Start playing
You can start with `!help` command right in the chat:

<img alt="help" height="200" src="https://github.com/didkovskiy/TTTBot/blob/master/doc/helpCommand.PNG" width=auto/>

Then everything is simple:

<img alt="help" height="180" src="https://github.com/didkovskiy/TTTBot/blob/master/doc/moves.PNG" width=auto/>

Depending on the result, you will receive the following message:

<img alt="help" height="80" src="https://github.com/didkovskiy/TTTBot/blob/master/doc/result.PNG" width=auto/>

Type `!rating` to see list of results of all players on the server.

<img alt="help" height="120" src="https://github.com/didkovskiy/TTTBot/blob/master/doc/rating.PNG" width=auto/>

## Technologies used

- [JDA](https://github.com/DV8FromTheWorld/JDA) 4.3.0.
- [slf4j log4j-12](https://mvnrepository.com/artifact/org.slf4j/slf4j-log4j12) 1.7.31
- [MySQL Connector/J](https://mvnrepository.com/artifact/mysql/mysql-connector-java) 5.1.13

See [build.gradle](https://github.com/didkovskiy/TTTBot/blob/master/build.gradle)

## DataSource
Rating data is stored in the database. [DAO class](https://github.com/didkovskiy/TTTBot/blob/master/src/main/java/didkovskiy/tttbot/dao/PlayerDAO.java)
uses simple jdbc connection. You need to configure __db.properties__ file.

To build same database use this [sql script](https://github.com/didkovskiy/TTTBot/blob/master/database/tttbot_db.sql)

## How to launch bot
You can launch the bot at your discord server
by creating new application in [Discord Developer Portal](https://discord.com/developers/applications).
All you need is bot __token__. You need to configure it in:
```
JDABuilder builder = JDABuilder.createDefault(System.getenv("TOKEN"));
```
