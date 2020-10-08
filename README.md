# simple-client-server

A simple client server project. 
The client simply reads the user input from the terminal and sends it to the server. 
The server emulates a small database of students with names and teams they are in.

# Commands

**ADD**
- `add name1 name2 ... nameN` (adds N new students with names specified to the default 0 team)
- `add -t id name1 name2 ... nameN` (adds N new students with names specified to the **id** team)

**GET**
- `get -a` (returns all students added to the database)
- `get -s name` (returns team id of a student with the name specified)
- `get -t id` (returns all students names from a team with the id specified)

**SET**
- `set id name1 name2 ... nameN` (moves the students with names specified to the id'th team) 
- `set -a id` (moves all students in the database to the team with id specified)
- `set -t id id1 id2 ... idN` (combines all teams with ids specified into the id'th team)

**RMV**
- `rmv -s name` (removes a student with the name specified from the database)
- `rmv -t id` (removes an id'th team and its students from the database)
- `rmv -a` (clears the database) 
