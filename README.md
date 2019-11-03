## FilmQuery Project

## Overview
The purpose of this project was to create a java app that could access a database based on search queries. It can search and return results based on filmId or keyword. The first menu is a simple numbered menu and it asks you to make a selection between 1-3 depending on which type of search you want to use. To search by filmId, just enter a number between 1-1000 since there are 1000 films in the film table of the linked sdvid database. This will output your selected film along with its pertinent fields like title, description, actors etc. The keyword search is similar in use; instead you enter a string of words to search the title and description by. 
## Technologies
MAMP - hosted local server
Eclipse - IDE
Terminal
Apache Maven - a build automation tool, used for dependencies
## Lessons Learned
Don't get column data from a "bridge table"/"non real table"/database view. They're just there for joining two actual tables.
