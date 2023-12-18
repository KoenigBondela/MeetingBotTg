package service;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import resources.BotInitializator;
import resources.DatabaseConnector;

public class StartBot extends TelegramBot {

    Handlers handlers = new Handlers();
    DatabaseConnector dataBase = new DatabaseConnector();

    public void checking(long telegramId) {
        DatabaseConnector dataBase = new DatabaseConnector();
        if (dataBase.findUser(telegramId) == true) {

        } else {
            addUser(telegramId);
        }
    }

    public void addUser(long telegramId) {
        handlers.sendMessage(telegramId, "Введите ваше имя");
        String name = "";
        handlers.sendMessage(telegramId, "Сколько вам лет?");
        int age = 1;
        handlers.sendMessage(telegramId, "Ваш пол (М/Ж)");
        String gender = "";
        handlers.sendMessage(telegramId, "Ваш город");
        String city = "";
        handlers.sendMessage(telegramId, "Ваше фото");
        String photo = "";
        handlers.sendMessage(telegramId, "Описание анкеты");
        String description = "";

        dataBase.addUser(telegramId, name, description, gender, photo, age, city);

    }
}
