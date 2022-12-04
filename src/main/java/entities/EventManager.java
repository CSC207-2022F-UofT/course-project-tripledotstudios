package entities;

import java.util.*;
import java.io.IOException;
import java.nio.file.*;
import org.json.*;

/**
 * The class that reads from Events.json (which stores our events in the story),
 * and generates a data structure (Map), like a factory, 
 * which maps the events' UUIDs to the Event objects.
 */
public class EventManager{	
	
	private final Map<Integer, Event> eventMap;

	public EventManager(){
		eventMap = getAllEventsFromFile();
	}

	/**
	 * Main function the user of this class must call upon to return a map of
	 * all the Events.
	 * @return a Map<Integer, Event> mapping the UUIDs to the Event objects
	 */
	public Map<Integer, Event> getAllEvents(){
		return eventMap;
	}

	/**
	 * Private function that is run once in constructor to read from file.
	 */
	@SuppressWarnings("all")
	private Map<Integer, Event> getAllEventsFromFile() {
		Map<Integer, Event> eventMap = new HashMap<>();

		String jsonFile = stringFromJsonFile("data/Events.json");
		
		JSONArray arr = new JSONArray(jsonFile);  // JSONArray of all Events
		
		for(int index = 0; index < arr.length(); index++){  // Iterating through the events

			JSONObject obj = arr.getJSONObject(index);  // Object at current index

			// Getting all the instance attributes:
			
			Integer id = obj.getInt("UUID");

			String narr = obj.getString("narration");

			JSONArray choicesNarrArr = obj.getJSONArray("choicesNarrations");
			List<String> choicesNarr = new ArrayList<>();
			for(int i = 0; i < choicesNarrArr.length(); i++){
				choicesNarr.add(choicesNarrArr.getString(i));
			}

			JSONArray choicesUUIDsArr = obj.getJSONArray("choicesNextUUIDs");
			List<Integer> choicesUUIDs = new ArrayList<>();
			for(int i = 0; i < choicesUUIDsArr.length(); i++){
				choicesUUIDs.add(choicesUUIDsArr.getInt(i));
			}

			String sound = obj.getString("soundFile");

			boolean autoSaves = obj.getBoolean("doesAutoSave");
			
			if(id % 2 == 0){  // normal Event
				
				eventMap.put(id, new Event(id, narr, choicesNarr, choicesUUIDs, sound, autoSaves));  // Puts new Event object in Map

			}else{  // Combat Event! 
				
				JSONObject enemyObj = obj.getJSONObject("enemy");  // Enemy Object
				EnemyData enemy = generateEnemyDataObject(enemyObj);
				
				JSONArray questionsArr = obj.getJSONArray("questions");  // Array of Questions
				List<QuestionData> questions = new ArrayList<>();
				generateListOfQuestions(questionsArr, questions);
				
				eventMap.put(id, new CombatEvent(id, narr, choicesNarr, choicesUUIDs, sound, autoSaves, enemy, questions));
			}
		}

		return eventMap;
	}

	// Below are private helper functions:

	@SuppressWarnings("all")
	private String stringFromJsonFile(String filePath){
		String fileAsString = "[]";
		try {
			fileAsString = new String(Files.readAllBytes(Paths.get(filePath)));
		} catch (IOException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		return fileAsString;
	}

	@SuppressWarnings("all")
	private EnemyData generateEnemyDataObject(JSONObject enemyObj){

		String enemyName = enemyObj.getString("name");
		Integer enemyHealth = enemyObj.getInt("health");
		Integer enemyAttackMean = enemyObj.getInt("enemyAttackValueMean");
		Integer enemyAttackDeviation = enemyObj.getInt("enemyAttackValueDeviation");

		return new EnemyData(enemyName, enemyHealth, enemyAttackMean, enemyAttackDeviation);
	}

	@SuppressWarnings("all")
	private void generateListOfQuestions(JSONArray questionsArr, List<QuestionData> questions){

		for(int i = 0; i < questionsArr.length(); i++){
			JSONObject qObj = questionsArr.getJSONObject(i);
			
			// Parsing each attribute of each QuestionData instance.
			String q = qObj.getString("question");
			
			JSONArray answersArr = qObj.getJSONArray("answers");
			List<String> answers = new ArrayList<String>();
			for(int j = 0; j < answersArr.length(); j++){
				answers.add(answersArr.getString(j));
			}

			JSONArray responsesArr = qObj.getJSONArray("responses");
			List<String> responses = new ArrayList<String>();
			for(int j = 0; j < responsesArr.length(); j++){
				responses.add(responsesArr.getString(j));
			}

			JSONArray attackValuesArr = qObj.getJSONArray("attackValues");
			List<Integer> attackValues = new ArrayList<Integer>();
			for(int j = 0; j < attackValuesArr.length(); j++){
				attackValues.add(attackValuesArr.getInt(j));
			}

			questions.add(new QuestionData(q, answers, responses, attackValues));
		}
	}

}
