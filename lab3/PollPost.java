package lab3;
import java.util.LinkedHashMap;
import java.util.Map;

public class PollPost extends Post {
    private Map<String, Integer> options = new LinkedHashMap<>();

    public PollPost(int id, String title, String optionsString, User user) {
        //super(id, title, options, user);
        super(id, title, optionsString, user);
        String[] optionsArray = optionsString.split(",");
        for (String option : optionsArray) {
            options.put(option.trim(), 0); // Initialize vote count to 0
        }
    }

    @Override
    public String getContent() {
        return "This is a poll with voting options.";
    }

    public void vote(int optionIndex) {
        String[] optionKeys = options.keySet().toArray(new String[0]);
        if (optionIndex >= 1 && optionIndex <= optionKeys.length) {
            String option = optionKeys[optionIndex - 1];
            options.put(option, options.get(option) + 1);
        } else {
            System.out.println("Invalid option. Please try again.");
        }
    }

    @Override
    public void display() {
        System.out.println(this.toString());
        int optionNumber = 1;
        for (Map.Entry<String, Integer> entry : options.entrySet()) {
            System.out.println(optionNumber + "." + entry.getKey() + " :" + entry.getValue());
            optionNumber++;
        }
    }

    @Override
    public String toString() {
        return "Post #" + postId + "\nCreated by: " + user.getFullName() + "  (@" + user.getUserName() + ")\nTitle: " + title + "\n" + getContent();
    }
}
