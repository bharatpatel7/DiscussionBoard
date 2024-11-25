
```markdown
# Discussion Board

## Overview

This project is a simple discussion board application built using Java Swing. Users can register, create posts, and search for posts. The application features a graphical user interface with different panels for each functionality. Basically it was part of my school and all the functionality is based on prof's requirement where I got 100/100 so this project is proper accordingly outline.

## Project Structure

```
DiscussionBoard/
    

CreatePostPanel.java


    CreatePostPanel.class
    

DiscussionBoard.java


    DiscussionBoard.class
    DiscussionBoard.jar
    

Post.java


    Post.class
    RegisterUserPanel.java
    RegisterUserPanel.class
    SearchPostsPanel.java
    SearchPostsPanel.class
    

User.java


    User.class
        DiscussionBoard/
            

CreatePostPanel.java


            CreatePostPanel.class
            

DiscussionBoard.java


            DiscussionBoard.class
            DiscussionBoard.jar
            

Post.java


            Post.class
            RegisterUserPanel.java
            RegisterUserPanel.class
            SearchPostsPanel.java
            SearchPostsPanel.class
            

User.java


            User.class
```

## Classes

### `DiscussionBoard`

The main class that initializes the application and sets up the main frame and panels.

### `User`

Represents a user with a full name and username. Validates that these fields are not empty.

### `Post`

Represents a post created by a user. Validates that the content is not empty.

### `CreatePostPanel`

A panel that allows users to create a new post. Contains fields for the username and post content.

### `RegisterUserPanel`

A panel that allows users to register by providing their full name and username.

### `SearchPostsPanel`

A panel that allows users to search for posts.

## How to Compile and Run

To compile the project, navigate to the `DiscussionBoard` directory and run:

```sh
javac DiscussionBoard/*.java
```

To run the project, use:

```sh
java -jar 

DiscussionBoard/DiscussionBoard.jar


```

## Author

Bharat Garsondiya

## License

This project is licensed under the MIT License.
```