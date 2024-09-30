import java.util.Scanner;

public class DiscussionBoard {
        
        private static final String[] posts = new String[100];
        private static int postCount = 0;

        public static void main(String[] args) {
                
                // System.out.println("Enter Responce:");
                // Scanner inputScanner = new Scanner(System.in);
                // int myInput = inputScanner.nextInt();
                int choice;
                Scanner scanner = new Scanner(System.in);
                
                do {
                        System.out.println("Menu:");
                        System.out.println("(1) Post new message");
                        System.out.println("(2) Print all posts");
                        System.out.println("(3) Print all posts in reverse order");
                        System.out.println("(4) Print number of posts entered so far");
                        System.out.println("(5) Print all posts from a user");
                        System.out.println("(6) Print the number of vowels across all posts");
                        System.out.println("(7) Perform a search of posts containing a given word (case sensitive)");
                        System.out.println("(8) Perform a search of posts containing a given word (case insensitive)");
                        System.out.println("(9) End Program");
                        System.out.print("Choose an option: ");
                        choice = scanner.nextInt();
                        scanner.nextLine();

                switch (choice) {
                        case 1:
                                System.out.println("Post a new massage");
                                postNewMassage(scanner);
                                printAllPostsInReverseOrder();
                                break;

                        case 2:
                                System.out.println("Print all posts");
                                printAllPosts();
                                break;

                        case 3:
                                System.out.println("Print all posts in reverse order");
                                printAllPostsInReverseOrder();
                                break;

                        case 4:
                                System.out.println("Print number of posts entered so far");
                                printNumberOfPosts();
                                break;

                        case 5:
                                System.out.println("Print all posts from a user");
                                printPostsFromUser(scanner);
                                break;

                        case 6:
                                System.out.println("Print the number of vowels across all posts");
                                printVowelCount();
                                break;

                        case 7:
                                System.out.println("Perform a search of posts containing a given word (case sensitive)");
                                searchPostsContainingWord(scanner, true);
                                break;

                        case 8:
                                System.out.println("Perform a search of posts containing a given word (case insensitive)");
                                searchPosts(scanner, false);
                                break;

                        case 9:
                                System.out.println("End Program");
                                break;
                
                        default:
                                System.out.println("You have entered wrong number!");
                                break;
                }

                } while (choice != 9);
                scanner.close();
        }
        
        private static void postNewMassage(Scanner scanner){
                //scanner.nextLine(); 
                System.out.println("Enter the user name:");
                String userName = scanner.nextLine();
                System.out.println("Enter the message:");
                String message = scanner.nextLine();
                posts[postCount++] = userName + " Says: " + message;
        }

        private static void printAllPosts(){
                for (int i = 0; i < postCount; i++) {
                        System.out.println(posts[i]);
                }
        }

        private static void printAllPostsInReverseOrder(){
                for (int i = postCount - 1; i >= 0; i--) {
                        System.out.println(posts[i]);
                }
        }

        private static void printNumberOfPosts(){
                System.out.println("Number of posts entered so far: " + postCount);
        }

        private static void printPostsFromUser(Scanner scanner) {
                System.out.print("Enter the user's name: ");
                String user = scanner.nextLine().toLowerCase(); 
                boolean found = false;
                        for (int i = 0; i < postCount; i++) {
                                if (posts[i].toLowerCase().startsWith(user + " says: ")) {
                                System.out.println(posts[i]);
                                found = true;
                        }
        }
        if (!found) {
                System.out.println("No posts from this user.");
        }
        }

        private static void printVowelCount() {
                int vowelCount = 0;
                for (int i = 0; i < postCount; i++) {
                        String post = posts[i].toLowerCase();
        
                        String actualMessage = post.substring(post.indexOf(" says: ") + 7); 

                        for (char c : actualMessage.toCharArray()) {
                        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                                vowelCount++;
                        }
                        }
                }

                System.out.println("Total number of vowels across all posts: " + vowelCount);
        }

        private static void searchPostsContainingWord(Scanner scanner, boolean caseSensitive) {
                System.out.print("Enter the word to search for: ");
                String word = scanner.nextLine();
                boolean found = false;
                for (int i = 0; i < postCount; i++) {
                        if (caseSensitive) {
                                if (posts[i].contains(word)) {
                                        System.out.println(posts[i]);
                                        found = true;
                                }
                        } else {
                                if (posts[i].toLowerCase().contains(word.toLowerCase())) {
                                        System.out.println(posts[i]);
                                        found = true;
                                }
                        }
                }
                if (!found) {
                        System.out.println("No posts containing this word.");
                }
        }

        private static void searchPosts(Scanner scanner, boolean caseSensitive) {
                System.out.print("Enter the word to search for: ");
                String word = scanner.nextLine();
                boolean found = false;

                for (int i = 0; i < postCount; i++) {
                        String post = caseSensitive ? posts[i] : posts[i].toLowerCase(); 
                        String searchTerm = caseSensitive ? word : word.toLowerCase();  
                        if (post.contains(searchTerm)) {
                        System.out.println(posts[i]);  
                        found = true;
                        }
                }

                if (!found) {
                        System.out.println("No posts contain the word \"" + word + "\".");
                }
}



}