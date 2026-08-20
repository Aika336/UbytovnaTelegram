package MainFile;

import TelegramSide.Bot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String botToken;
        String botName;
        String chatId; // может быть пустым — тогда бот в режиме /chatid

        if (configFileExists("config.properties")) {
            Properties props = loadProperties("config.properties");
            botToken = props.getProperty("bot.token");
            botName  = props.getProperty("bot.name");
            chatId   = props.getProperty("chat.id"); // может отсутствовать

        } else {
            Scanner in = new Scanner(System.in);

            System.out.print("Bot token: ");
            botToken = in.nextLine().trim();

            System.out.print("Bot name: ");
            botName = in.nextLine().trim();

            System.out.println("Chat id:");
            String input = in.nextLine().trim();
            chatId = input.isBlank() ? null : input;

            in.close();
        }

        if (botToken == null || botToken.isBlank() || botName == null || botName.isBlank()) {
            System.err.println("Token and name of bot is important");
            System.exit(1);
        }

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new Bot(botToken, botName, chatId));
        } catch (TelegramApiException e) {
            throw new RuntimeException("Не удалось запустить бота: " + e.getMessage(), e);
        }
    }

    public static boolean configFileExists(String path) {
        return new java.io.File(path).exists();
    }

    public static Properties loadProperties(String path) {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(path)) {
            props.load(fis);
        } catch (IOException e) {
            System.err.println("Не удалось прочитать " + path + ": " + e.getMessage());
            System.exit(1);
        }
        return props;
    }
}