/*
Author: Bharat Garsondiya
Date: 2024-09-30
Last Modified: 2021-10-3
Student ID: 1303213
compile: javac DiscussionBoard.java
*/

package lab3;

import java.util.*;



public class DiscussionBoard {
        
        private static ArrayList<User> users = new ArrayList<>();
        private static ArrayList<Post> posts = new ArrayList<>();

        public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);
                int choice;

                do { 
                        System.out.println("Menu:");
                        System.out.println("1. Create a new user");
                        System.out.println("2. Create a new post");
                        System.out.println("3. Display all posts");
                        System.out.println("4. View all posts with a given username");
                        System.out.println("5. View all posts with a given keyword");
                        System.out.println("6. Exit");
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
                                        viewPostsByUser(scanner);
                                        break;

                                case 5:
                                        viewPostsByKeyword(scanner);
                                        break;

                                case 6:
                                        System.out.println("Exiting...");
                                        break;

                                default:
                                        System.out.println("Invalid choice. Please try again.");
                        }
                        
                } while (choice != 6);

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
                }6

                User user = new User(fullName, userName);
                users.add(user);
                System.out.println("User created:" + user.getUserName() + "(@" + user.getUserName() + ")");
        }

        private static void createNewPost(Scanner scanner){
                System.out.println("Enter your Username: ");
                String fullName = scanner.nextLine().toLowerCase();

                User user = null;
                for (User u : users) {
                        if (u.getUserName().equalsIgnoreCase(fullName)) {
                                user = u;
                                break;
                        }
                }

                if (user == null) {
                        System.out.println("User not found. Please create a new user first.");
                        return;
                }

                System.out.print("Enter Post title: ");
                String title = scanner.nextLine();
                System.out.print("Enter Post content: ");
                String content = scanner.nextLine();

                Post newPost = new Post(title, content, user);
                posts.add(newPost);
                System.out.println("Post created Sucsessfully!");
                
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

        // public class TextPost extends Post {
        //         private String content;

        //         public TextPost(String title, String content, User user) {
        //                 super(title, user);
        //                 this.content = content;
        //         }

        //         @Override
        //         public String getContent() {
        //                 return content;
        //         }

        //         @Override
        //         public void display() {
        //                 System.out.println(this.toString());
        //                 System.out.println(content);
        //         }
        // }



}

