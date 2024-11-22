package  DiscussionBoard;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class CreatePostPanel extends JPanel {

        public CreatePostPanel(){

                setLayout(new BorderLayout());

                JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
                JLabel userNameLabel = new JLabel("User Name:");
                JTextField userNameField = new JTextField(10);
                JLabel titLabel = new JLabel("Enter Post Title:");
                JTextField titleField = new JTextField();
                JLabel contentLabel = new JLabel("Enter Post Content:");
                JTextArea contentArea = new JTextArea(10, 30);
                contentArea.setLineWrap(true);
                contentArea.setWrapStyleWord(true);
                JScrollPane scrollPane = new JScrollPane(contentArea);

                inputPanel.add(userNameLabel);
                inputPanel.add(userNameField);
                inputPanel.add(titLabel);
                inputPanel.add(titleField);
                inputPanel.add(contentLabel);
                inputPanel.add(scrollPane);

                JPanel buttonPanel = new JPanel(new FlowLayout());
                JButton createPostButton = new JButton("Create Post");
                JTextArea messageArea = new JTextArea(5, 30);
                messageArea.setEditable(false);
                messageArea.setLineWrap(true);
                messageArea.setWrapStyleWord(true);
                JScrollPane messageScrollPane = new JScrollPane(messageArea);

                buttonPanel.add(createPostButton);

                add(inputPanel, BorderLayout.NORTH);
                add(buttonPanel, BorderLayout.CENTER);
                add(messageScrollPane, BorderLayout.SOUTH);

                createPostButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                String userName = userNameField.getText().trim().toLowerCase();
                                String title = titleField.getText().trim();
                                String content = contentArea.getText().trim();

                                if (userName.isEmpty()) {
                                        messageArea.setText("User Name cannot be empty.");
                                        return;
                                }
                                if (title.isEmpty()) {
                                        messageArea.setText("Title cannot be empty.");
                                        return;
                                }
                                if (content.isEmpty()) {
                                        messageArea.setText("Content cannot be empty.");
                                        return;
                                }

                                User user = null;
                                for (User u : DiscussionBoard.users) {
                                        if (u.getUserName().equalsIgnoreCase(userName)) {
                                                user = u;
                                                break;
                                        }
                                }

                                if (user == null) {
                                        messageArea.setText("Error: User '" + userName + "'not found. Please create a new user first.");
                                        return;
                                }

                                try{
                                        Post post = new Post(title, content, user);
                                        DiscussionBoard.posts.add(post);

                                        DiscussionBoard.userPostMap.computeIfAbsent(user.getUserName(), k -> new ArrayList<>()).add(DiscussionBoard.posts.size() - 1);
                                        messageArea.setText("Post created successfully by @" + user.getUserName());
                                } catch (Exception ex) {
                                        messageArea.setText(ex.getMessage());
                                        return;
                                }
                        }
                });
        }
}