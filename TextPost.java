public class TextPost extends Post {
    private String content;

    public TextPost(String title, String content, User user) {
        super(title, user);
        this.content = content;
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
}