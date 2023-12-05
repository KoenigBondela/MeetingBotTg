package service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Map;

public class Handlers extends TelegramBot {
    Map<String, Runnable> commands = new HashMap<>();

    public void telegramHandlers(String messageText, long chatId) {
        commands.put("/start", () -> startCommandResponse(chatId));
        commands.put("/about", () -> aboutCommandResponse(chatId));
        commands.put("/authors", () -> authorsCommandResponse(chatId));

        if (messageText.contains("/help")) {
            if (messageText.length() > 6) {
                messageText = messageText.substring(6);
            }
            helpCommandResponse(chatId, messageText);
        } else {
            if (commands.containsKey(messageText)) {
                commands.get(messageText).run();
            } else {
                sendMessage(chatId, "Комманда не распознана");
            }
        }

    }

    private void startCommandResponse(long chatId) {
        String answer = "Добро пожаловать в бота для поиска знакомств. Для подробной информации можете отправить \"/help\"";
        sendMessage(chatId, answer);
    }

    private void aboutCommandResponse(long chatId) {
        String answer = "Это бот для поиска новых знакомств!";
        sendMessage(chatId, answer);
    }

    private void authorsCommandResponse(long chatId) {
        String answer = "Этого бота написали:\n\nАфонина Анастасия Вячеславовна\nБочкарев Игорь Константинович\nТёткин Михаил Евгеньевич";
        sendMessage(chatId, answer);
    }

    private void helpCommandResponse(long chatId, String command) {
        String lowercaseCommand = command.toLowerCase();

        String textToSend;
        switch (lowercaseCommand) {
            case "about":
                textToSend = "Эта команда описывает возможности бота.";
                sendMessage(chatId, textToSend);
                break;
            case "start":
                textToSend = "Эта команда перезапускает бота.";
                sendMessage(chatId, textToSend);
                break;
            case "authors":
                textToSend = "Эта команда показывает авторов бота.";
                sendMessage(chatId, textToSend);
                break;
            case "help":
                textToSend = "Вводя эту команду вы можете просмотреть подробности про конкретную команду.";
                sendMessage(chatId, textToSend);
                break;
            default:
                textToSend = "Бот имеет следующие команды:\n/start\n/about\n/help\n/authors\n\nДля подробной информации введите \"/help команда\"";
                sendMessage(chatId, textToSend);
                break;
        }
    }


    public void sendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            System.out.println("Uncorrect: " + e);

        }
    }
}