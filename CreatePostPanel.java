package  DiscussionBoard;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class CreatePostPanel extends JPanel {

        private JTextField userNameField;
        private JTextArea contentArea;
        private JTextArea massageArea;

        public CreatePostPanel(){

                setLayout(new BorderLayout());

                JPanel mainPanel = new JPanel(new BorderLayout());
                mainPanel.setBorder(BorderFactory.createTitledBorder("Create Post"));

                JPanel rightJPanel = new JPanel(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(10, 10, 10, 10);
                gbc.fill = GridBagConstraints.HORIZONTAL;

                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.gridwidth = 2;
                rightJPanel.add(new JLabel("Userame:"), gbc);

                gbc.gridy = 1;
                //gbc.gridx = 2;
                gbc.gridwidth = 2;
                userNameField = new JTextField();
                rightJPanel.add(userNameField, gbc);

                gbc.gridy = 2;
                //gbc.gridx = 2;
                gbc.gridwidth = 2;
                rightJPanel.add(new JLabel("Post Content:"), gbc);

                gbc.gridy = 3;
                //gbc.gridx = 1;
                gbc.gridwidth = 2;
                contentArea = new JTextArea();
                contentArea.setLineWrap(true);
                contentArea.setWrapStyleWord(true);
                JScrollPane contentScrollPane = new JScrollPane(contentArea);
                contentScrollPane.setPreferredSize(new Dimension(300, 150));
                rightJPanel.add(new JScrollPane(contentArea), gbc);
                
                gbc.gridy = 4;
                gbc.gridwidth = 2;
                gbc.anchor = GridBagConstraints.CENTER;
                JButton createPostButton = new JButton("Create Post");
                createPostButton.setPreferredSize(new Dimension(200, 30));
                rightJPanel.add(createPostButton, gbc);

                mainPanel.add(rightJPanel, BorderLayout.WEST);

                JPanel leftPanel = new JPanel(new BorderLayout());
                leftPanel.add(new JLabel("Massage: "), BorderLayout.NORTH);
                massageArea = new JTextArea();
                massageArea.setEditable(false);
                leftPanel.add(new JScrollPane(massageArea), BorderLayout.CENTER);

                mainPanel.add(leftPanel, BorderLayout.CENTER);

                add(mainPanel, BorderLayout.CENTER);


                createPostButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                String userName = userNameField.getText().trim().toLowerCase();
                                //String title = titleField.getText().trim();
                                String content = contentArea.getText().trim();

                                if (userName.isEmpty()) {
                                        massageArea.setText("User Name cannot be empty.");
                                        return;
                                }
                                // if (title.isEmpty()) {
                                //         messageArea.setText("Title cannot be empty.");
                                //         return;
                                // }
                                if (content.isEmpty()) {
                                        massageArea.setText("Content cannot be empty.");
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
                                        massageArea.setText("Error: User '" + userName + "'not found. Please create a new user first.");
                                        return;
                                }

                                try{
                                        //Post post = new Post(title, content, user);
                                        //DiscussionBoard.posts.add(post);
                                        String title = "Default Title"; // or retrieve the title from a field if available
                                        Post post = new Post(content, user);
                                        DiscussionBoard.posts.add(post);
                                        DiscussionBoard.userPostMap.computeIfAbsent(user.getUserName(), k -> new ArrayList<>()).add(DiscussionBoard.posts.size() - 1);
                                        massageArea.setText("Post created successfully by @" + user.getUserName() + "." + "\nPost Content: " + content);
                                } catch (Exception ex) {
                                        massageArea.setText(ex.getMessage());
                                }
                        }
                });
        }
}