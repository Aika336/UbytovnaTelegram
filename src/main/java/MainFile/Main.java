package MainFile;

import core.RegularCheck;
import TelegramSide.Bot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.concurrent.TimeUnit;

// "7997682354:AAGk6apnbDP1CAQrHKHgFUaiDWUWCeZMvp4"
// "MladostChecker"

public class Main {
    public static void main(String[] args) {
        RegularCheck info = new RegularCheck();
        Runnable task = info.getTask();

        RegularCheck.SCEDULER.scheduleAtFixedRate(task, 0, 30, TimeUnit.SECONDS);


        /*try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new Bot("7997682354:AAGk6apnbDP1CAQrHKHgFUaiDWUWCeZMvp4",
                    "MladostChecker"));

            System.out.println("Bot is working!");
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }*/
    }
}
