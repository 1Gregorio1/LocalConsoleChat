
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static Map<Integer, User> users = new HashMap<>();
    static ArrayList<Message> messages = new ArrayList<Message>();
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static int count = 2;
    static User currentUser;

    public static void main(String[] args) throws IOException {
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("hh:mm:ss");
        /**
         * 2 пользователя и их сообщения, для имитации входа в активный чат.
         */
        users.put(1, new User("Коля", 1));
        users.put(2, new User("Денис", 2));
        messages.add(new Message("Всем привет!", 1, formatForDateNow.format(dateNow)));
        messages.add(new Message("Как дела?", 1, formatForDateNow.format(dateNow)));
        messages.add(new Message("привет, отлично!", 2, formatForDateNow.format(dateNow)));
        messages.add(new Message("Хорошо", 1, formatForDateNow.format(dateNow)));

        System.out.println("Введите слово exit при отправке сообщения, для смены пользователя");
        System.out.println("Для прекращения работы чата, введите 'ChatOFF!', во время отправки сообщения");
        currentUser = getCurrentUser();
        while (true) {
            sendNewMessage();
        }
    }

    /**
     * Получаем текущего пользователя, кто будет писать сообщения.
     * При вводе никнейма, которого нет в чате, создается новый пользователь, иначе пишет имеющийся участник.
     * @return Возвращаем объект пользователя
     * @throws IOException
     */
    private static User getCurrentUser() throws IOException {
        System.out.println("Введите логин пользователя");
        String userName = reader.readLine();
        for (Integer key : users.keySet()) {
            if (userName.equals(users.get(key).getNickname())) {
                return users.get(key);
            }
        }
        System.out.println("Зашел новый пользователь " + userName);
        count++;
        users.put(count, new User(userName, count));
        showAllMessages();
        return new User(userName, count);
    }

    /**
     * Отправка нового сообщения.
     * При вводе ключевого слова "exit", переопределяем пользователя
     * @throws IOException
     */
    private static void sendNewMessage() throws IOException {
        System.out.println("Введите сообщение!");
        String newMessage = reader.readLine();
        if (newMessage.equals("ChatOFF!")) {
            System.exit(0);
        }
        if (newMessage.equalsIgnoreCase("exit")) {
            logOut();
        } else {
            Date dateNow = new Date();
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("hh:mm:ss");
            messages.add(new Message(newMessage, currentUser.getId(), formatForDateNow.format(dateNow)));
        }
    }

    /**
     * Переопределение объекта пользователя
     * @throws IOException
     */
    private static void logOut() throws IOException {
        currentUser = getCurrentUser();
        sendNewMessage();
    }

    /**
     * Вывод истории всех сообщений из списка, при подключении нового пользователя
     */
    private static void showAllMessages() {
        for (var item : messages) {
            int id = item.getUserID();
            User user = users.get(id);
            System.out.println(user.getNickname() + " " + item.getTime() + ": " + item.getText());
        }
    }
}
