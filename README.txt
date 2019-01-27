Welcome to foodApp!!! by Ryan Toepfer (ryantpfr@gmail.com)

----------------------------Introduction:--------------------------------
    This application was written in order to help my friends decide
where to eat dinner. It randomly selects 5 restaurants from a .txt file
and allows users to vote on options. The votes and users are stored in a
relational DB.

-------------------------Users and Security:-----------------------------
    Currently the application uses standard spring security and a database
user store. The application allows users to be administrators. Only
administrators can tell the application to re-select food options. Users
can change their votes but cannot remove votes. The application keeps
records of user's votes in previous session, but because I don't consider
it a requirement, the app only records each users latest vote within each
session.
    One of the applications weaknesses is that it contains no mechanism for
password changes or even hiding passwords from me. Because the passwords
are exposed to me, I assigned my friends passwords (I mean there are only 10
users currently and the app is only hosted on the local network). If I were 
to create a production version of this application I would use an OAuth2.0
to secure my application instead of implementing my own security mechanisms.

-------------------------------Database:----------------------------------
    The application uses Spring Data JPA and Hibernate to interact with a 
relational database. The createDB.sql file is located in src/main/resources 
and is formatted for a mySql db. The application does not use any native 
queries or mySql specific technology. The application also uses only standard 
JPA features. and The database is normalized.
                                ________________
   ________________             |vote_t        |             ________________
   |session_t     |             |--------------|             |user_t        |
   |--------------|             |VOTE_ID(PK)   |             |--------------|
-> |SESSION_ID(PK)|             |SESSION_ID    |       ----> |USERNAME(PK)  |
|  |ACTIVE        |       ------|RESTAURANT_ID |       |     |PASSWORD      |
|  ----------------       |     |USER          |--------     |IS_ADMIN      |
|                         |     ----------------             ----------------
|  ________________       |
|  |restaurant_t  |       |
|  |--------------|       |
|  |RESTAURANT_ID(PK) <----
---|SESSION_ID    |
   |NAME          |
   ----------------

------------------------------------Web:-------------------------------------
    Despite being outdated, using MVC and Thymeleaf instead of a separate
frontend was chosen simply because if it's simplicity. The application is not
a demonstration of current front end development standards (although it does
work on mobile browsers).
