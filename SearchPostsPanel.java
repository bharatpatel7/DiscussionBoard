package DiscussionBoard;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class SearchPostsPanel extends JPanel {

        private JTextField userNameField;
        private JTextArea outPutArea;
        private JTextArea massageArea;
        private JButton searchButton;

        public SearchPostsPanel() {

                setLayout(new BorderLayout());

                JPanel mainPanel = new JPanel(new BorderLayout());
                mainPanel.setBorder(BorderFactory.createTitledBorder("Search Posts"));

                JPanel rightJPanel = new JPanel(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new java.awt.Insets(10, 10, 10, 10);
                gbc.fill = GridBagConstraints.HORIZONTAL;
                
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.gridwidth = 2;
                rightJPanel.add(new JLabel("Userame:"), gbc);

                gbc.gridy = 1;
                //gbc.gridx = 2;
                gbc.gridwidth = 2;
                userNameField = new JTextField(20);
                rightJPanel.add(userNameField, gbc);

                gbc.gridy = 4;
                gbc.gridwidth = 2;
                gbc.anchor = GridBagConstraints.CENTER;
                searchButton = new JButton("Search Post");
                //searchButton.setPreferredSize(new Dimension(200, 30));
                rightJPanel.add(searchButton, gbc);

                mainPanel.add(rightJPanel, BorderLayout.WEST);

                JPanel leftPanel = new JPanel(new BorderLayout());
                leftPanel.add(new JLabel("Massage: "), BorderLayout.NORTH);
                massageArea = new JTextArea();
                massageArea.setEditable(false);
                leftPanel.add(new JScrollPane(massageArea), BorderLayout.CENTER);

                mainPanel.add(leftPanel, BorderLayout.CENTER);

                add(mainPanel, BorderLayout.CENTER);

                //JButton searchButton = new JButton("Search Posts");
                searchButton.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                String userName = userNameField.getText().trim().toLowerCase();
                                ArrayList<Integer> postIndex = DiscussionBoard.userPostMap.get(userName);
                                if (userName.isEmpty()) {
                                        massageArea.setText("User Name cannot be empty.");
                                        return;
                                }
                                if (postIndex == null) {
                                        massageArea.setText("No posts found for the user" + userName);
                                } else {
                                        StringBuilder results = new StringBuilder("Posts by @" + userName + ":\n");
                                        for (int index : postIndex) {
                                                results.append(DiscussionBoard.posts.get(index).toString());
                                                results.append("\n=====================================\n");
                                        }
                                        massageArea.setText(results.toString());
                                }
                        }
                });

                // add(inputPanel, BorderLayout.NORTH);
                // add(scrollPane, BorderLayout.CENTER);
                // add(searchButton, BorderLayout.SOUTH);

        }
}