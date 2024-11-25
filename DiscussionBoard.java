/*
Author: Bharat Garsondiya
Date: 2024-09-30
Last Modified: 2024-11-19
Student ID: 1303213
compile: javac DiscussionBoard/*.java
run: java -jar DiscussionBoard/DiscussionBoard.jar
*/
package DiscussionBoard;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class DiscussionBoard {
        private JFrame frame;
        private JPanel panel;
        private RegisterUserPanel registerUserPanel;
        private CreatePostPanel createPostPanel;
        private SearchPostsPanel searchPostsPanel;
        private JPanel mainPanel;
        

        public static ArrayList<User> users = new ArrayList<>();
        public static ArrayList<Post> posts = new ArrayList<>();
        public static HashMap<String, ArrayList<Integer>> userPostMap = new HashMap<>();
        


        public DiscussionBoard() {
                frame = new JFrame("Discussion Board");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 400);

                registerUserPanel = new RegisterUserPanel();
                createPostPanel = new CreatePostPanel();
                searchPostsPanel = new SearchPostsPanel();

                mainPanel = new JPanel(new CardLayout());
                mainPanel.add(registerUserPanel, "registerUserPanel");
                mainPanel.add(new CreatePostPanel(), "CreatePost");
                mainPanel.add(searchPostsPanel, "searchPostsPanel");


                JMenuBar menuBar = new JMenuBar();
                JMenu menu = new JMenu("Options");
                JMenuItem registerMenuItem = new JMenuItem("Register User");
                JMenuItem createPostMenuItem = new JMenuItem("Create Post");
                JMenuItem searchPostsMenuItem = new JMenuItem("Search Posts");
                createPostMenuItem.addActionListener(e -> {
                        CardLayout cl = (CardLayout) mainPanel.getLayout();
                        cl.show(mainPanel, "CreatePost");
                });


                registerMenuItem.addActionListener(e -> switchPanel("registerUserPanel"));
                createPostMenuItem.addActionListener(e -> switchPanel("createPostPanel"));
                searchPostsMenuItem.addActionListener(e -> switchPanel("searchPostsPanel"));

                menu.add(registerMenuItem);
                menu.add(createPostMenuItem);
                menu.add(searchPostsMenuItem);
                menuBar.add(menu);
                frame.setJMenuBar(menuBar);

                frame.add(mainPanel);
                frame.setVisible(true);
                
        }

        private void switchPanel(String panelName) {
                CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
                cardLayout.show(mainPanel, panelName);
        }

        public static void main(String[] args) {
                SwingUtilities.invokeLater(DiscussionBoard::new);
        }

}



