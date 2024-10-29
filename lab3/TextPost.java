package lab3;

import java.io.Serializable;

public class TextPost extends Post implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String content;


    public TextPost(String title, String content, User user) {
        super(title, user);
        this.content = content;
    }

    public String getContent() {
        return "content";
    }

    @Override
    public String toString() {
        return "Post #" + postId + "\nCreated by: " + user.getFullName() + "  (@" + user.getUserName() + ")\nTitle: " + title + "\n" + content;
    }
}