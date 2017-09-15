package com.javarush.task.task30.task3008.client;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by user on 29.04.2017.
 */
public class BotClient extends Client {

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        return "date_bot_" + String.format("%d", (int) (Math.random() * 100));
    }

    public static void main(String[] args) {
        new BotClient().run();
    }

    public class BotSocketThread extends SocketThread {

        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            SimpleDateFormat df = null;

            super.processIncomingMessage(message);
            String[] strings = message.split(": ");

            if (strings.length == 2) {
                String answerMessage = "Информация для " + strings[0] + ": ";
                if ("дата".equals(strings[1])) {
                    df = new SimpleDateFormat("d.MM.YYYY");
                } else if ("день".equals(strings[1])) {
                    df = new SimpleDateFormat("d");
                } else if ("месяц".equals(strings[1])) {
                    df = new SimpleDateFormat("MMMM");
                } else if ("год".equals(strings[1])) {
                    df = new SimpleDateFormat("YYYY");
                } else if ("время".equals(strings[1])) {
                    df = new SimpleDateFormat("H:mm:ss");
                } else if ("час".equals(strings[1])) {
                    df = new SimpleDateFormat("H");
                } else if ("минуты".equals(strings[1])) {
                    df = new SimpleDateFormat("m");
                } else if ("секунды".equals(strings[1])) {
                    df = new SimpleDateFormat("s");
                }

                if (df != null) {
                    answerMessage += df.format(Calendar.getInstance().getTime());
                    sendTextMessage(answerMessage);
                }
            }
        }
    }
}
