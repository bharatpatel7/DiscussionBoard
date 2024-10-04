```markdown
# Discussion Board Application

This is a simple Java-based discussion board program that allows users to register and post messages. Users can create posts, view all posts, search for posts by username, or search for posts by keywords.

## Table of Contents

- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Classes](#classes)
- [License](#license)

## Features

- Create a new user with a full name and a username.
- Post messages with a title and content.
- Display all posts.
- Search and view all posts by a specific user.
- Search posts by keywords in the title or content.

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/discussion-board.git
   ```

2. Compile the Java files:
   ```bash
   javac DiscussionBoard.java
   ```

3. Run the application:
   ```bash
   java DiscussionBoard
   ```

## Usage

1. **Create a New User:**
   - Enter your full name and username (or leave the username blank to use the first name by default).
   
2. **Create a New Post:**
   - Enter your username, post title, and content to create a new post.

3. **View All Posts:**
   - Displays a list of all the posts created by all users.

4. **Search Posts by Username:**
   - Enter a username to view all posts by that specific user.

5. **Search Posts by Keyword:**
   - Enter a keyword to search for posts that contain the word in the title or content.

## Classes

### `DiscussionBoard.java`
- The main class that contains the application logic.
- It manages users and posts, providing options to create users, create posts, and search for posts.

### `User.java`
- This class represents a user in the discussion board.
- Each user has a full name and a username.

### `Post.java`
- This class represents a post created by a user.
- Each post contains a title, content, and the user who created it.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
```

Make sure to customize the repository link in the `Installation` section to your actual GitHub repo link!