This project takes a Java version of the game 2048 that I originally built in CIS 1200 and sets it up with a full DevOps workflow. The goal of the project is to make the game easy to build, test, 
and run on any machine. I used Maven to manage the code, GitHub Actions for CI/CD, and Docker to create a portable build of the project.

With this setup, any time I push to GitHub, the project compiles and runs its tests. When I create a tagged release, the workflow builds a runnable JAR and attaches it to a GitHub Release. 
This helps make the project reproducible, consistent, and simple to share.

Explanation of key code:

Game.java-
This file contains the main(String[] args) method that launches the game. This starts the GUI on the Swing event thread.

Game2048.java-
This class manages the game logic: moves, tile merging, scoring, checking if the game is over

GameBoard2048.java-
This class handles: drawing the board, updating the UI, key presses from the player

Tile.java-
Represents an individual tile on the grid. Stores the tile value (2, 4, 8, etc.).

Utils.java-
Handles reading and writing saved score data.

Tests-
The test file checks basic behavior like: tile movement, merging, adding new tiles, game-over logic. These tests run automatically in CI.

How to Build and View the Project
1. Build the project
From the project root:
mvn clean package
If successful, the runnable JAR appears in:
target/java-2048-1.0.0.jar
2. Run the game
java -jar target/java-2048-1.0.0.jar
This opens the 2048 GUI.
4. Run CI/CD on GitHub
Push any commit → CI compiles, tests, and builds the project
Create a tag like v1.0.0 → GitHub automatically builds the JAR and attaches it to a Release
You can see your releases under the Releases tab of the GitHub repo.
