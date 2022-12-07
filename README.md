# CSC207 Project: Lost-the Game

As of Nov. 29, we have finished most of our features for the game. We just need to finish the UI and one small feature 
between the CombatInteractor class and the usecases.story.StorylineInteractor class. After these implementations, our program will 
be able to run. 

## Progress Report
- [X] Sound feature during the game
- [X] Log in feature
- [X] Saving feature
- [X] Event demos
- [X] Item Pool and Data features
- [X] HP and Attack System in the game
- [X] Dialogue options and storyline interactions
- [X] Player data classes and interactors
- 
## How to Run the Game
Run the Main program.
 
## Testing and Functionality

We have individually written tests for our code and tested it before submitting the pull requests and merging. After 
merging all our features and being able to run the game we implemented a series of tests to test the entities and 
use case features as a whole.


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

- For the entities layer we have a factory design pattern which we utilized to fix a SOLID violation in our main use
  case in which we were accessing the entity PlayerData directly instead of using the Player Manager that we built.

- To follow Clean Architecture we implemented our entities layer with the next layer being their interactors to access
  those entities. For example, to access our saved sounds for each event in the story we implemented the SoundInteractor
  which handles the playing and the pausing our sound. We follow this for other use cases as well.

- To hande our View for the user, we utilized dependency inversion by implementing interfaces for the 
  StoryLineInteractor and the View. 

- We also used the Facade Pattern to initialise our screens and interactors. This allowed us to manage the screens and 
  controllers effeciently while also keeping our main class relatively simple.

- For our data persistance layer we implemented the SaveInteractor and LoadInteractor classes which implement the 
  Saver and Loader Interfaces. This saves a serializable file in the savesfiles folder and the LoadInteractor loads 
  the file back up from that folder depending on the player username.

