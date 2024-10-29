package lab3;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PollPost extends Post implements Serializable {
    private Map<String, Integer> options = new HashMap<>();

    public PollPost(String title, User user){
        super(title, user);
        options = new HashMap<>();
    }

    public void addOption(String option) {
        options.put(option.trim(), 0);
    }

    public void vote(String option) {
        options.put(option.trim(), options.getOrDefault(option.trim(), 0) + 1);
    }

    public Map<String, Integer> getOptions() {
        return options;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Post #" + postId + "\nCreated by: " + user.getFullName() + "  (@" + user.getUserName() + ")\nTitle: " + title + "\n");
        int index = 1;
        for (Map.Entry<String, Integer> entry : options.entrySet()) {
            sb.append(entry.getKey() + ". " + entry.getValue() + "\n");
        }
        return sb.toString();
    }
}

