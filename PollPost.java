import java.util.LinkedHashMap;
import java.util.Map;

public class PollPost extends Post {
    private Map<String, Integer> options = new LinkedHashMap<>();

    public PollPost(String title, String optionsString, User user) {
        super(title, user);
        String[] optionsArray = optionsString.split(";");
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
        if (optionIndex >= 0 && optionIndex < optionKeys.length) {
            String option = optionKeys[optionIndex];
            options.put(option, options.get(option) + 1);
        } else {
            System.out.println("Invalid option. Please try again.");
        }
    }

    @Override
    public void display() {
        System.out.println(this.toString());
        for (Map.Entry<String, Integer> entry : options.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
