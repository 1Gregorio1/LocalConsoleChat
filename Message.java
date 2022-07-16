public class Message {
    private String text;
    private int userID;
    private String time;

    public Message(String text, int userID, String time) {
        this.text = text;
        this.userID = userID;
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public int getUserID() {
        return userID;
    }

    public String getTime() {
        return time;
    }
}
