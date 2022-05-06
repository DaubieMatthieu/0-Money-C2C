# 0-Money C2C

End of course project of ISEP engineer school

Module IP.3510 / AP.3610, semester S6, Master 2

## Summary

This is a team project realised during the last semester of our engineer formation.
Our team is composed of François CHIV, Matthieu DAUBIE and Gonzague DUMOULIN. 

The chosen subject is called 0-Money C2C and was presented by ISEP supervisor Ammar KHEIRBEK.
The details can be found in the pdf document at the root of the project, 
The goal is to design and implement a Consumer-To-Consumer application on the barter principle, with no money involved.
More details can be found in the pdf document at the root of the project.

## Installation
### Database
Download MySQL Server and either the MySQL Workbench or the MySQL Shell

Run the following commands as root user:

CREATE DATABASE omoney_c2c;

CREATE USER `spring`@`localhost` IDENTIFIED BY "spring2mysql";

GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, ALTER, REFERENCES ON omoney_c2c.* TO `spring`@`localhost`;

Once this is done you can simply run the application, it will create the tables itself.

