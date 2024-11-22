package DiscussionBoard;
import java.awt.*;
import javax.swing.*;

public class RegisterUserPanel extends JPanel {
        private JTextField fullNameField, userNameField;
        private JTextArea massageArea;
        private JButton registerButton;

        public RegisterUserPanel(){
                setLayout(new BorderLayout());

                JPanel inputPanel = new JPanel(new GridLayout(3, 2));
                inputPanel.add(new JLabel("Full Name:"));
                fullNameField = new JTextField();
                inputPanel.add(fullNameField);

                inputPanel.add(new JLabel("User Name:"));
                userNameField = new JTextField();
                inputPanel.add(userNameField);

                registerButton = new JButton("Register");
                inputPanel.add(registerButton);

                add(inputPanel, BorderLayout.CENTER);

                massageArea = new JTextArea();
                massageArea.setEditable(false);
                add(new JScrollPane(massageArea), BorderLayout.SOUTH);

                registerButton.addActionListener(e -> registerUser());
        }

        private void registerUser() {

                String fullName = fullNameField.getText().trim();
                String userName = userNameField.getText().trim();

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
}