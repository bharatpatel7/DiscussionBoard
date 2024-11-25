package DiscussionBoard;
import java.awt.*;
import javax.swing.*;

public class RegisterUserPanel extends JPanel {
        private JTextField fullNameField, userNameField;
        private JTextArea massageArea;
        private JButton registerButton;

        public RegisterUserPanel(){
                setLayout(new BorderLayout());

                JPanel mainPanel = new JPanel(new BorderLayout());
                mainPanel.setBorder(BorderFactory.createTitledBorder("Register User"));

                // JPanel leftJPanel = new JPanel(new GridLayout(4, 2, 10, 10));
                // leftJPanel.add(new JLabel("Register new user"));
                //mainPanel.add(new JLabel());

                JPanel leftJPanel = new JPanel(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(10, 10, 10, 10);
                gbc.fill = GridBagConstraints.HORIZONTAL;

                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.gridwidth = 5;
                leftJPanel.add(new JLabel("Full Name:"), gbc);

                gbc.gridy = 1;
                //gbc.gridx = 1;
                gbc.gridwidth = 5;
                fullNameField = new JTextField();
                leftJPanel.add(fullNameField, gbc);

                gbc.gridy = 2;
                //gbc.gridx = 1;
                gbc.gridwidth = 5;
                leftJPanel.add(new JLabel("Username:"), gbc);

                gbc.gridy = 5;
                //gbc.gridx = 1;
                gbc.gridwidth = 5;
                userNameField = new JTextField();
                leftJPanel.add(userNameField, gbc);

                gbc.gridy = 6;
                //gbc.gridx = 1;
                gbc.gridwidth = 2;
                gbc.anchor = GridBagConstraints.CENTER;
                registerButton = new JButton("Register");
                registerButton.setPreferredSize(new Dimension(100, 30));
                leftJPanel.add(registerButton, gbc);

                mainPanel.add(leftJPanel, BorderLayout.WEST);
                //add(leftJPanel, BorderLayout.WEST);

                JPanel rightPanel = new JPanel(new BorderLayout());
                rightPanel.add(new JLabel("Massage: "), BorderLayout.NORTH);
                massageArea = new JTextArea();
                massageArea.setEditable(false);
                rightPanel.add(new JScrollPane(massageArea), BorderLayout.CENTER);

                mainPanel.add(rightPanel, BorderLayout.CENTER);

                add(mainPanel, BorderLayout.CENTER);

                registerButton.addActionListener(e -> registerUser());
        }



        private void registerUser() {

                String fullName = fullNameField.getText().trim();
                String userName = userNameField.getText().trim();
                massageArea.setText("User Registered: " + fullName + " (" + userName + ")");
                

                if (fullName.isEmpty()) {
                        massageArea.setText("Full Name cannot be empty.");
                        return;
                }

                if (userName.isEmpty()) {
                        userName = fullName.split(" ")[0].toLowerCase();
                }

                for (User u : DiscussionBoard.users) {
                        if (u.getUserName().equalsIgnoreCase(userName)) {
                                massageArea.setText("User Name already exists.");
                                return;
                        }
                }

                User user = new User(fullName, userName);
                DiscussionBoard.users.add(user);
                massageArea.setText("User registered successfully.\nUser Name: " + user.getUserName());
        }

        public JTextField getFullNameField() {
                return fullNameField;
        }

        public JTextField getUserNameField() {
                return userNameField;
        }

        public JTextArea getMassageArea() {
                return massageArea;
        }

        public JButton getRegisterButton() {
                return registerButton;
        }
}