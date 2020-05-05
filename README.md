# REA_toy_robot
----------------
Project overview
----------------
This project is simulating the toy robot which can move on a table (initialized with the space 5x5, The origin (0,0) can be considered to be the SOUTH WEST most corner) based on the command line from a user. The robot will not start the moving process until it receives a valid starting point(the location is on the table). It will step forward only if this step doesn't let it fall from the table.

-- Command line description --
PLACE X,Y,F
MOVE
LEFT
RIGHT
REPORT

- PLACE will put or relocate the toy robot on the table in position X,Y and facing NORTH, SOUTH, EAST or WEST.
- MOVE will move the toy robot one unit forward in the direction it is currently facing.
- LEFT and RIGHT will rotate the robot 90 degrees in the specified direction without changing the position of the robot.
- REPORT will announce the X,Y and F of the robot.

-- User Story --
- As a user, I want to send command "PLACE" with a location on the table to the robot, so that I can re-locate it.
- As a user, I want to know the status of my "PLACE" command send to robot, so that I can place the robot correctly on the table.
- As a user, I want to send command "MOVE" to the robot, so that it can step one unit forward. 
- As a user, I want to know the status of my "MOVE" command send to robot, so that I can know the next step is safe or not.
- As a user, I want to send command "LEFT" to the robot, so that it can change it's face to the left. 
- As a user, I want to send command "RIGHT" to the robot, so that it can change it's face to the right.
- As a user, I want to send command "REPORT" to the robot, so that I can know the current location and also facing of the robot.

-------------------
Develop environment
-------------------
- Language: Java 8
- IDE: Apache Netbeans 11.3
- Maven: 3.3.9
--------
Testing
--------
Lib: JUnit 5
-- Unit Test --
Approach with a white box by using DD-Path testing.
Approach with a white box by using PTV testing.

-- Integration Test --
Approach with MM-Path

-- System Test --
Running multi testing to check the output of the application


----------
Versioning
----------
Version 1.0 

-------
License
-------
REA Group
