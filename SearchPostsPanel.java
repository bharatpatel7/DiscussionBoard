package DiscussionBoard;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class SearchPostsPanel extends JPanel {
        public SearchPostsPanel() {
                setLayout(new BorderLayout());

                JPanel inputPanel = new JPanel(new GridLayout());
                JLabel userNameLabel = new JLabel("User Name:");
                JTextField userNameField = new JTextField();
                inputPanel.add(userNameLabel);
                inputPanel.add(userNameField);

                JTextArea outPutArea = new JTextArea(5, 30);
                outPutArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(outPutArea);

                JButton searchButton = new JButton("Search Posts");
                searchButton.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                String userName = userNameField.getText().trim().toLowerCase();
                                ArrayList<Integer> postIndex = DiscussionBoard.userPostMap.get(userName);
                                if (userName.isEmpty()) {
                                        outPutArea.setText("User Name cannot be empty.");
                                        return;
                                }
                                if (postIndex == null) {
                                        outPutArea.setText("No posts found for the user" + userName);
                                } else {
                                        StringBuilder results = new StringBuilder("Posts by @" + userName + ":\n");
                                        for (int index : postIndex) {
                                                results.append(DiscussionBoard.posts.get(index).toString());
                                                results.append("\n=====================================\n");
                                        }
                                        outPutArea.setText(results.toString());
                                }
                        }
                });

                add(inputPanel, BorderLayout.NORTH);
                add(scrollPane, BorderLayout.CENTER);
                add(searchButton, BorderLayout.SOUTH);

        }
}