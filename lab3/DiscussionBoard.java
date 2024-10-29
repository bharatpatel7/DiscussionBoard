/*
Author: Bharat Garsondiya
Date: 2024-09-30
Last Modified: 2021-10-3
Student ID: 1303213
compile: javac DiscussionBoard.java
*/


package lab3;

import java.io.*;
import java.util.*;


public class DiscussionBoard implements Serializable {
        private static final long serialVersionUID = 1L;
        
        private static ArrayList<User> users = new ArrayList<>();
        private static ArrayList<Post> posts = new ArrayList<>();
        private static int postIdCounter = 0;

        public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);
                int choice;

               String fileName = "default";
                if (args.length > 0) {
                        fileName = args[0];
                        loadDiscussionBoard(fileName);
                } else {
                        System.out.println("No file specified. Starting a blank discussion board.");
                }

                do { 
                        System.out.println("Menu:");
                        System.out.println("1. Create a new user");
                        System.out.println("2. Create a new post");
                        System.out.println("3. Display all posts");
                        System.out.println("4. Vote in a poll");
                        System.out.println("5. View all posts with a given username");
                        System.out.println("6. View all posts with a given keyword");
                        System.out.println("7. Save Discussion Board");
                        System.out.println("8. Exit");
                        System.out.print("Enter your choice: ");
                        choice = scanner.nextInt();
                        scanner.nextLine();

                        switch (choice) {
                                case 1:
                                        createNewUser(scanner);
                                        break;

                                case 2:
                                        createNewPost(scanner);
                                        break;
                                
                                case 3:
                                        displayAllPosts();
                                        break;

                                case 4:
                                        voteInPoll(scanner);
                                        break;

                                case 5:
                                        viewPostsByUser(scanner);
                                        break;

                                case 6:
                                        viewPostsByKeyword(scanner);
                                        break;

                                case 7:
                                        System.out.print("Enter filename to save: ");
                                        String saveFileName = scanner.nextLine();
                                        saveDiscussionBoard(saveFileName);
                                        break;
                                
                                case 8:
                                        System.out.println("Exiting...");
                                        break; 

                                default:
                                        System.out.println("Invalid choice. Please try again.");
                        }
                        
                } while (choice != 8);

                scanner.close();
        }

        private static void createNewUser(Scanner scanner){
                System.out.print("Enter full name: ");
                String fullName = scanner.nextLine();
                System.out.print("Enter username: (or leave blank to use default) ");
                String userName = scanner.nextLine().trim();
                
                for (User user : users) {
                        if (user.getUserName().equals(userName)) {
                                System.out.println("Username already exists. Please try again.");
                                return;
                        }
                }

                User user = new User(fullName, userName);
                users.add(user);
                System.out.println("User created: " + fullName + " (@" + user.getUserName() + ")");
        }

        private static void createNewPost(Scanner scanner) {
                System.out.println("Enter your Username: ");
                String userName = scanner.nextLine().toLowerCase();

                User user = null;
                for (User u : users) {
                        if (u.getUserName().equalsIgnoreCase(userName)) {
                        user = u;
                        break;
                        }
                }

                if (user == null) {
                        System.out.println("User not found. Please create a new user first.");
                        return;
                }

                System.out.print("Enter post type ('text' or 'poll'): ");
                String postType = scanner.nextLine().toLowerCase();

                System.out.print("Enter post title: ");
                String title = scanner.nextLine();

                Post newPost = null;


                if (postType.equals("text")){
                        System.out.println("Enter Post Content: ");
                        String content = scanner.nextLine();
                        newPost = new TextPost(title, content, user);
                        //posts.add(newPost);
                }
                else if (postType.equals("poll")){
                        newPost = new PollPost(title, user);
                        System.out.println("Enter number of options(Sepreted by ';'): ");
                        String[] options = scanner.nextLine().split(";");

                        for (String option : options) {
                                ((PollPost) newPost).addOption(option);
                        }
                        //posts.add(newPost);
                }
                else {
                        System.out.println("Invalid post type. Please try again.");
                        return;
                }

                newPost.setPostId(postIdCounter++);
                posts.add(newPost);

                System.out.println("Post created successfully.");
        }


        private static void displayAllPosts(){

                if (posts.isEmpty()) {
                        System.out.println("No posts to display.");
                        return;
                }
                else
                {
                        for (Post post : posts) {
                                System.out.println(post);
                                System.out.println("=====================================");
                        }
                }
        }

        private static void viewPostsByUser(Scanner scanner){
                System.out.print("Enter username: ");
                String userName = scanner.nextLine().toLowerCase();

                boolean found = false;
                for (Post post : posts) {
                        if (post.getUser().getUserName().equalsIgnoreCase(userName)) {
                                System.out.println(post);
                                System.out.println("=====================================");
                                found = true;
                        }
                }

                if (!found) {
                        System.out.println("No posts found for this user: " + userName);
                }
        }

        private static void viewPostsByKeyword(Scanner scanner){
                System.out.print("Enter keyword: ");
                String keyword = scanner.nextLine().toLowerCase();

                boolean found = false;
                for (Post post : posts) {
                        if (post.getTitle().toLowerCase().contains(keyword) || (post instanceof TextPost && ((TextPost) post).getContent().toLowerCase().contains(keyword))) {
                                System.out.println(post);
                                System.out.println("=====================================");
                                found = true;
                        }
                }

                if (!found) {
                        System.out.println("No posts found for this keyword: " + keyword);
                }
        }

        private static void voteInPoll(Scanner scanner) {
                System.out.print("Enter post ID to vote: ");
                int postId = scanner.nextInt();
                scanner.nextLine();  // consume newline

                Post post = findPostById(postId);
                if (post instanceof PollPost){
                        PollPost pollPost = (PollPost) post;
                        System.out.println(pollPost);

                        int optionNumber = 1;
                        for (String option : pollPost.getOptions().keySet()) {
                                System.out.println(optionNumber + ". " + option);
                                optionNumber++;
                        }

                        System.out.print("Enter option number to vote: ");
                        int selectedOption = scanner.nextInt();
                        scanner.nextLine();  // consume newline

                        if (selectedOption > 0 && selectedOption <= pollPost.getOptions().size()) {
                                String selectedOptionText = (String) pollPost.getOptions().keySet().toArray()[selectedOption - 1];
                                pollPost.vote(selectedOptionText);
                                System.out.println("Vote successful.");
                        } else {
                                System.out.println("Invalid option number. Please try again.");
                        }
                }else{
                        System.out.println("Post is not a poll. Please try again.");
                }
        }

        private static Post findPostById(int postId) {
                for (Post post : posts) {
                        if (post.getPostId() == postId) {
                                return post;
                        }
                }
                return null;
        }

        private static void saveDiscussionBoard(String fileName) {
                File file = new File("./boards/" + fileName + ".dboard");
                System.out.println("Saving discussion board to " + file.getAbsolutePath());
                
                try (FileOutputStream fileOut = new FileOutputStream(file);
                        ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                                System.out.println("Users: " + users);
                                System.out.println("Posts: " + posts);
                        out.writeObject(users);
                        out.writeObject(posts);
                        System.out.println("Discussion board saved successfully to " + file.getAbsolutePath());
                } catch (IOException e) {
                        System.out.println("Error saving discussion board: " + e.getMessage());
                        //e.printStackTrace();
                }
        }

        @SuppressWarnings("unchecked")
private static void loadDiscussionBoard(String fileName) {
    File file = new File("./boards/" + fileName + ".dboard");
    if (!file.exists()) {
        System.out.println("File not found at " + file.getAbsolutePath() + ". Starting a blank discussion board.");
        return;
    }

    try (FileInputStream fileIn = new FileInputStream(file);
         ObjectInputStream in = new ObjectInputStream(fileIn)) {
        users = (ArrayList<User>) in.readObject();
        posts = (ArrayList<Post>) in.readObject();
        System.out.println("Discussion board loaded successfully from " + file.getAbsolutePath());
    } catch (IOException | ClassNotFoundException e) {
        System.out.println("Error loading discussion board: " + e.getMessage());
    }
}





}

