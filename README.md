# CSC207 Project: Lost-the Game

As of December 7, we have finished all of the features of the game. Since the Milestone 4 deadline, we have finished 
our UI and have a working version of the game. After receiving the Milestone 4 feedback, we refactored our code 
for better and cleaner organization. In addition, we fixed some issues that did not adhere to Clean Architecture 
and SOLID principles, and we added tests to our test folder, showing an increase in test coverage. 


## Progress Report
- [X] Sound feature during the game
- [X] Log in feature
- [X] Saving feature
- [X] Event demos
- [X] Item Pool and Data features
- [X] HP and Attack System in the game
- [X] Dialogue options and storyline interactions
- [X] Player data classes and interactors
- [X] UI and game screen
- [X] Working game
- 
- 
## How to Run the Game
Run the Main program.
 
## Testing and Functionality

Our tests are located in the test package, and within each package, our tests are separated by their layer. We tested
most of our entities and usecases. 


## Members of Group
- Josh Chan
- Alaina Hu
- Chris Jiang
- Gwendolyn Kwong
- Taha Saeed Piracha
- Natasha Sharan
- Ludan Ye

## Design Patterns

In our CRC model, we already separated our classes into the different layers of Clean Architecture, so during
implementation, we did not have issues with adhering to Clean Architecture and the SOLID design rules. Putting our data 
classes into the entities layer and the interactors into the Use Case layer helped us not run into instances of breaking
Clean Architecture.  

- For the entities layer, we have a factory design pattern which we utilized to fix a SOLID violation in our main use
  case. Previously,we were accessing the entity PlayerData directly. Now, we use the Player Manager that we built.

- To follow Clean Architecture, we implemented our entities layer with the next layer being their interactors to access
  those entities. For example, to access our saved sounds for each event in the story, we implemented the SoundInteractor
  which handles the playing and the pausing of our sound. We follow this for other use cases as well.

- To hande our View for the user, we utilized dependency inversion by implementing interfaces for the 
  StoryLineInteractor and the View. 

- We also used the Facade Pattern to initialise our screens and interactors. This allowed us to manage the screens and 
  controllers efficiently while also keeping our main class relatively simple.

- For our data persistence layer we implemented the SaveInteractor and LoadInteractor classes which implement the 
  Saver and Loader Interfaces. This saves a serializable file in the savesfiles folder, and the LoadInteractor loads 
  the file back up from that folder depending on the player username.

