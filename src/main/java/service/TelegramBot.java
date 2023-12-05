package service;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramBot extends TelegramLongPollingBot {
    public String messageText;
    public long chatId;
    @Override
    public String getBotUsername() {
        return "TestBot";
    }

    @Override
    public String getBotToken() {
        return System.getenv("token");
    }

    @Override
    public void onUpdateReceived(Update update) {
        Handlers messageList = new Handlers();
        if (update.hasMessage() && update.getMessage().hasText()) {
            System.out.println(update.getMessage().getText()); // показывает что пишут люди
            messageText = update.getMessage().getText();
            chatId = update.getMessage().getChatId();
            messageList.telegramHandlers(messageText, chatId);
        }
    }
}