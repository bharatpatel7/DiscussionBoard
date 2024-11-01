public abstract  class Post{
        private static int idCounter = 0;
        protected int postId;
        protected  String title;
        protected  String Content;
        protected  User user;
        

        public Post(String title, String content, User user) {
                this.postId = ++idCounter;
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
                return "Post #" + postId + "\nCreated by: " + user.getFullName() + "  (@" + user.getUserName() + ")\nTitle: " + title;
        }

        public abstract void display();
        
}

