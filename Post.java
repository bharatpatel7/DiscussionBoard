public class Post{
        private String title;
        private String Content;
        private User user;

        public Post(String title, String content, User user) {
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
                return "Created by: " + user.getUserName() + "  (@" + user.getUserName() + ")\nTitle: " + title + "\n" + Content;
        }
        
}