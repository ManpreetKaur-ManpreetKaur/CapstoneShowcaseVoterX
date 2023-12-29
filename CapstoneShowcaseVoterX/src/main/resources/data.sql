INSERT INTO capstoneProject (projectName, videoLink, teamName, projectDescription, votes) VALUES
('Project 1', 'https://youtube.com/project1', 'Team A', 'Description for Project 1', 10),
('Project 2', 'https://youtube.com/project2', 'Team B', 'Description for Project 2',12),
('Project 3', 'https://youtube.com/project3', 'Team C', 'Description for Project 3', 14);




INSERT INTO sec_user (email, encryptedPassword, enabled)
VALUES ('simon.hood@sheridancollege.ca', '$2a$10$1ltibqiyyBJMJQ4hqM7f0OusP6np/IHshkYc4TjedwHnwwNChQZCy', 1);

INSERT INTO sec_user (email, encryptedPassword, enabled)
VALUES ('jonathan.penava@sheridancollege.ca', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);

 
INSERT INTO sec_role (roleName)
VALUES ('ROLE_USER');
 
INSERT INTO sec_role (roleName)
VALUES ('ROLE_GUEST');
   

 
INSERT INTO user_role (userId, roleId)
VALUES (1, 1);
 
INSERT INTO user_role (userId, roleId)
VALUES (2, 2);

