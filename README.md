# NoDraw

An integrated Notetaking application that does not draw!

This software serves as the project for the course CSC207 at the University of Toronto.

Distribution without permission is not permitted!

## Final Presentation slides
Provided is a link to our slides presentation on the application.
https://utoronto-my.sharepoint.com/:p:/g/personal/reeshav_kumar_mail_utoronto_ca/EYDBguP2EeZDv4HfEs7tOBQBFjRy9my1ZvwcJP0jtbVI0w?e=yXQHgy

## Package Organization
Our code is divided into several packages adhering to clean architecture:

Package entity contains all entity level classes
Package database contains all database and API related classes
Package use_cases contains all use case level classes
Package controllers contains all controller classes
Package presenter contains all presenter classes
Package UI contains all UI level classes
Package Exception contains all exceptions
Package base contains Main class

## Compiling
This project requires a JDK ≥ version 17. OpenJDK 20 is recommended.

*IMPORTANT!!!* 

An API token used in this application will be expired, as such please expect the application to not run as intended.
For example,a feature of the app such as logging out after creating some files and logging back in will not show the files previously worked on.


Please email gpayumo99@gmail.com to use a fully working version of the application. 

## Usage
### Login View
Upon opening the program, a login page is presented with a username and password field, a "Login" button, and a "Signup" button. The "Signup" button opens a new signup page, where the user can register a unique username and password. Signing a new unique user returns the client to the login page to register. "Login" requires the user to have an existing username and password in order to access the application. Upon a successful login, a new page containing a "Text Note" button and a "Draw Note" button are presented, and the user may decide between these options or exit the program.
### API Token


In this project, the primary objective is to develop a user-friendly and all-encompassing note­taking application. The goal is to integrate it with a powerful drawing tool, creating a ve­rsatile platform that caters to individuals from various fields such as students, professionals, artists, and anyone who desire­s an efficient and flexible­ tool for capturing and visualizing information. This comprehensive app will allow users to effortle­ssly organize their thoughts, ideas, and cre­ative expressions. It promise­s intuitive functionality while ensuring smooth inte­gration between different features.

Key Features and Focus Areas:

1. Synchronized Notetaking and Drawing Interface:
The application provides a seamless and uninterrupte­d workflow by offering users a harmonious environment to effortlessly transition between taking textual notes and creating drawings.

2. Intuitive User Interface;

The focus of the application will be, on providing a design that's easy to navigate and understand making it simple for users to create, edit and organize their notes and drawings.

3. Diverse Range of Drawing Tools;

Within the notetaking interface users will have access to a selection of brushes, colors, shapes, and other features. This will enable them to express their creativity through diagrams, flowcharts, mind maps, illustrations, and more.

4. Efficient Search and Organization;

To enhance productivity the application will offer search capabilities along with tools for organization. Users will be able to locate and categorize their notes and drawings for retrieval.

5. Secure Storage and Synchronization;

For added convenience and peace of mind users can choose to store their notes and drawings in a database that enables synchronization across devices. This ensures accessibility while minimizing the risk of data loss.

6. Flexible Exporting and Sharing Options;

To facilitate sharing with others or archiving purposes the application will provide export formats such, as PDF or image files. Users can then easily share their creations via email or messaging apps on platforms.

The ultimate goal of this integrated notetaking and drawing application is to provide users with a comprehensive and flexible tool that enhances their productivity, creativity, and organization, empowering them to effectively capture and visualize their ideas and thoughts.

-Fruityvice API documentation link (tentative, API may be subject to change):
[https://dev.evernote.com/doc/#reference](https://www.fruityvice.com/doc/index.html)

-Screenshot in hoppscotch.io
![image](https://github.com/FionaYYX/CSC207_Group199/assets/69075231/dcf7352c-827c-40b8-8c8d-4307ce0643c8)

-Output in java
![image](https://github.com/FionaYYX/CSC207_Group199/assets/69075231/b6bff36f-ed2d-4057-a94d-8562c42171c2)

