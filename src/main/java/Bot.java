import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return "ossa_photo_bot";
    }

    @Override
    public String getBotToken() {
        return "5967119981:AAEanoz4YJbpngRJVLw-cH-9nKzdxhenPc8";
    }


    // Bus      AgACAgIAAxkBAAMEY8A4trVvDx--q7NamhHAOICjAhcAAu3DMRv79gFKj1nJUivCya0BAAMCAANzAAMtBA
    // Islandia AgACAgIAAxkBAAMGY8A5GQqpym_RS217vgygHxqZ0lgAAvLDMRv79gFKDqMccndYr9YBAAMCAANzAAMtBA
    // Mir      AgACAgIAAxkBAAMKY8A5OImrhhY2Df8sa0sCtFnZyNgAAvPDMRv79gFKDpvuYidviWcBAAMCAANzAAMtBA
    // Paris    AgACAgIAAxkBAAMMY8A5VVsF7GEAAcvp0O_6bKJd2OVjAAL0wzEb-_YBSv1v_NVBhlnmAQADAgADcwADLQQ
    // Tourism  AgACAgIAAxkBAAMOY8A5nH2H6u32kcIeByhIqKscZuIAAvXDMRv79gFK_sy7ARqn2uUBAAMCAANzAAMtBA
    // Verona   AgACAgIAAxkBAAMQY8A5skFNt78Cbu1ciXYyI-ADU60AAvbDMRv79gFKiTW6hiRvg_gBAAMCAAN5AAMtBA
    @Override
    public void onUpdateReceived(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        KeyboardRow keyboardRow1 = new KeyboardRow();
        KeyboardRow keyboardRow2 =  new KeyboardRow();
        keyboardRow1.add(new KeyboardButton("Bus"));
        keyboardRow1.add(new KeyboardButton("Islandia"));
        keyboardRow1.add(new KeyboardButton("Mir"));

        keyboardRow2.add(new KeyboardButton("Paris"));
        keyboardRow2.add(new KeyboardButton("Tourist"));
        keyboardRow2.add(new KeyboardButton("Verona"));

        List<KeyboardRow> list = new ArrayList<>();
        list.add(keyboardRow1);
        list.add(keyboardRow2);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(list);
        sendMessage.setText(update.getMessage().getText());
        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(update.getMessage().getChatId().toString());
        InputFile inputFile = new InputFile();
        switch (update.getMessage().getText()){
            case "Bus":
                inputFile.setMedia("AgACAgIAAxkBAAMEY8A4trVvDx--q7NamhHAOICjAhcAAu3DMRv79gFKj1nJUivCya0BAAMCAANzAAMtBA");
                break;
            case "Islandia":
                inputFile.setMedia("AgACAgIAAxkBAAMGY8A5GQqpym_RS217vgygHxqZ0lgAAvLDMRv79gFKDqMccndYr9YBAAMCAANzAAMtBA");
                break;
            case "Mir":
                inputFile.setMedia("AgACAgIAAxkBAAMKY8A5OImrhhY2Df8sa0sCtFnZyNgAAvPDMRv79gFKDpvuYidviWcBAAMCAANzAAMtBA");
                break;
            case "Paris":
                inputFile.setMedia("AgACAgIAAxkBAAMMY8A5VVsF7GEAAcvp0O_6bKJd2OVjAAL0wzEb-_YBSv1v_NVBhlnmAQADAgADcwADLQQ");
                break;
            case "Tourist":
                inputFile.setMedia("AgACAgIAAxkBAAMOY8A5nH2H6u32kcIeByhIqKscZuIAAvXDMRv79gFK_sy7ARqn2uUBAAMCAANzAAMtBA");
                break;
            case "Verona":
                inputFile.setMedia("AgACAgIAAxkBAAMQY8A5skFNt78Cbu1ciXYyI-ADU60AAvbDMRv79gFKiTW6hiRvg_gBAAMCAAN5AAMtBA");
                break;
        }

        sendPhoto.setPhoto(inputFile);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

        try {
            execute(sendPhoto);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }
}
