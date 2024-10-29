package lab3;

import java.io.Serializable;

public abstract class Post implements Serializable {
        private static final long serialVersionUID = 1L;
        private static int idCounter = 0;

        protected int postId;
        protected  String title;
        //protected  String Content;
        protected  User user;
        

        public Post(String title, User user) {
                this.postId = ++idCounter;
                this.title = title;
                this.user = user;
        }

        public int getPostId() {
                return postId;
        }

        public User getUser() {
                return user;
        }

        public String getTitle() {
                return title;
        }

        // public String getContent() {
        //         return Content;
        // }

        public void setPostId(int postId) {
                this.postId = postId;
        }

        // @Override
        // public String toString() {
        //         return "Post #" + postId + "\nCreated by: " + user.getFullName() + "  (@" + user.getUserName() + ")\nTitle: " + title;
        // }

        public abstract String toString();
        
}

