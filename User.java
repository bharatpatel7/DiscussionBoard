package DiscussionBoard;


public class User {
        private String fullName;
        private String userName;

        public User(String fullName, String userName) throws IllegalArgumentException {

                if (fullName == null || fullName.trim().isEmpty()) {
                        throw new IllegalArgumentException("Name cannot be empty.");
                }
                this.fullName = fullName;
                
                if (userName == null || userName.trim().isEmpty()){
                        this.userName = fullName.split(" ")[0].toLowerCase();
                } else {
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