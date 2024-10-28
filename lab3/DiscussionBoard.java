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

               String fileName = "./boards/";
                if (args.length > 0) {
                        fileName += args[0] + ".dboard";
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

                if (postType.equals("text")) {
                        System.out.print("Enter post content: ");
                        String content = scanner.nextLine();
                        newPost = new TextPost(postIdCounter++, title, content, user);
                        
                } else if (postType.equals("poll")) {
                        System.out.print("Enter poll options separated by semicolons (e.g., 'Option1;Option2;Option3'): ");
                        String options = scanner.nextLine();
                        newPost = new PollPost(postIdCounter++, title, options, user);
                } else {
                        System.out.println("Invalid post type. Please try again.");
                }

                posts.add(newPost);
                System.out.println("Post created successfully!");
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
                        if (post.getTitle().toLowerCase().contains(keyword) || post.getContent().toLowerCase().contains(keyword)) {
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

                Post post = null;
                for (Post p : posts) {
                        if (p.getPostId() == postId) {
                        post = p;
                        break;
                        }
                }

        if (post == null) {
                System.out.println("Post not found.");
                return;
        }

        if (post instanceof PollPost) {
                PollPost pollPost = (PollPost) post;
                pollPost.display();
                System.out.print("Enter option number to vote: ");
                int optionIndex = scanner.nextInt() - 1;
                pollPost.vote(optionIndex);
        } else {
                System.out.println("The selected post is not a poll.");
        }
        }

        private static void saveDiscussionBoard(String fileName) {
                File file = new File("./boards/" + fileName + ".dboard");
                try (FileOutputStream fileOut = new FileOutputStream(file);
                        ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                        out.writeObject(users);
                        out.writeObject(posts);
                        System.out.println("Discussion board saved successfully to " + fileName + ".dboard");
                } catch (IOException e) {
                        System.out.println("Error saving discussion board: " + e.getMessage());
                }
        }

        @SuppressWarnings("unchecked")
        private static void loadDiscussionBoard(String fileName) {
                File file = new File(fileName);
                if (!file.exists()) {
                        System.out.println("File not found. Starting a blank discussion board.");
                        return;
                }

                try (FileInputStream fileIn = new FileInputStream(file);
                        ObjectInputStream in = new ObjectInputStream(fileIn)) {
                        users = (ArrayList<User>) in.readObject();
                        posts = (ArrayList<Post>) in.readObject();
                        System.out.println("Discussion board loaded successfully from " + fileName);
                } catch (IOException | ClassNotFoundException e) {
                        System.out.println("Error loading discussion board: " + e.getMessage());
                }
        }





}

