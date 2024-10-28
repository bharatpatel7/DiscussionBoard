package lab3;
public class TextPost extends Post {
    private int id;
    private String title;
    private String content;
    private User user;


    public TextPost(int id, String title, String content, User user) {
        super(id, title, content, user);
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void display() {
        System.out.println(this.toString());
        System.out.println(content);
    }

    @Override
    public String toString() {
        return "Post #" + postId + "\nCreated by: " + user.getFullName() + "  (@" + user.getUserName() + ")\nTitle: " + title + "\n" + content;
    }
}