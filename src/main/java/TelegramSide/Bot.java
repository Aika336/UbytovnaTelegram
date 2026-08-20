package TelegramSide;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import core.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Bot extends TelegramLongPollingBot {
    private final String token;
    private final String botName;
    private final String chatId;

    private UbytovanieStatement state;

    public Bot(String token, String botName, String chatId) {
        this.token = token;
        this.botName = botName;
        this.chatId = (chatId != null && !chatId.isBlank()) ? chatId : null;

        if(this.chatId != null) {
            state = new UbytovanieStatement();
            RegularCheck.SCEDULER.scheduleAtFixedRate(this::checkStatus, 0, 10, TimeUnit.SECONDS);
            System.out.println("Monitoring is working. Chat Id: " + this.chatId);
        }else {
            System.out.println("Chat id is not setting, pls use /chatid to get it");
        }
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {

    }

    private void checkStatus() {
        try {
            if(state.isStatementPodana()) {
                sendMessage("Внимание!!! Общежития светит!!!1!1111!!!1");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String text) {
        try {
            execute(SendMessage.builder()
                    .chatId(chatId)
                    .text(text)
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
