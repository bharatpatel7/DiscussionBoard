package lab3;

import java.io.Serializable;

public class User implements Serializable {
        private String fullName;
        private String userName;

        public User(String fullName, String userName) {
                this.fullName = fullName;

                if (userName == null || userName.trim().isEmpty()) {
                        this.userName = fullName.split(" ")[0].toLowerCase();
                }
                else {
                        this.userName = userName.toLowerCase();
                }
        }

        public String getFullName() {
                return fullName;
        }

        public String getUserName() {
                return userName;
        }
}