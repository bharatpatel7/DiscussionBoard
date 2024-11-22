package DiscussionBoard;

public class Post {
        private String title;
        private String Content;
        private User user;

        public Post(String title, String content, User user) throws IllegalArgumentException {
                
                if (title == null || title.trim().isEmpty()) {
                        throw new IllegalArgumentException("Title cannot be empty.");
                }
                if (content == null || content.trim().isEmpty()) {
                        throw new IllegalArgumentException("Content cannot be empty.");
                }
                
                this.title = title;
                Content = content;
                this.user = user;
        }

        public User getUser() {
                return user;
        }

        public String getTitle() {
                return title;
        }

        public String getContent() {
                return Content;
        }

        @Override
        public String toString() {
                return "Created by: " + user.getFullName() + "  (@" + user.getUserName() + ")\nTitle: " + title + "\n" + Content;
        }
        
}