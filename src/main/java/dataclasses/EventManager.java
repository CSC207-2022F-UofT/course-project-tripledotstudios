package dataclasses;

import java.util.*;
import java.nio.file.*;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * The class that reads from Events.json (which stores our events in the story),
 * and generates a data structure (Map), like a factory, 
 * which maps the events' UUIDs to the Event objects.
 */
public class EventManager{	
	
	public Map<Integer, Event> getAllEvents(){
		Map<Integer, Event> eventMap = new HashMap<Integer, Event>();

		String jsonFile = stringFromJsonFile("data/Events.json");
		
		JSONArray arr = new JSONArray(jsonFile);  // JSONArray of all Events
		
		for(int index = 0; index < arr.length(); index++){  // Iterating through the events

			JSONObject obj = arr.getJSONObject(index);  // Object at current index

			// Getting all the instance attributes:
			
			Integer id = obj.getInt("UUID");

			String narr = obj.getString("narration");

			JSONArray choicesNarrArr = obj.getJSONArray("choicesNarrations");
			List<String> choicesNarr = new ArrayList<String>();
			for(int i = 0; i < choicesNarrArr.length(); i++){
				choicesNarr.add(choicesNarrArr.getString(i));
			}

			JSONArray choicesUUIDsArr = obj.getJSONArray("choicesNextUUIDs");
			List<Integer> choicesUUIDs = new ArrayList<Integer>();
			for(int i = 0; i < choicesUUIDsArr.length(); i++){
				choicesUUIDs.add(choicesUUIDsArr.getInt(i));
			}

			String sound = obj.getString("soundFile");

			boolean autoSaves = obj.getBoolean("doesAutoSave");
			
			if(id % 2 == 0){  // normal Event
				
				// Creating the Event object, and putting it into the Map:
				eventMap.put(id, new Event(id, narr, choicesNarr, choicesUUIDs, sound, autoSaves));

			}else{  // Combat Event! More attributes to read!
				
				JSONObject enemyObj = obj.getJSONObject("enemy");  // Enemy Object

				String enemyName = enemyObj.getString("name");
				Integer enemyHealth = enemyObj.getInt("health");
				Integer enemyAttackMean = enemyObj.getInt("enemyAttackValueMean");
				Integer enemyAttackDeviation = enemyObj.getInt("enemyAttackValueDeviation");
				
				JSONArray questionsArr = obj.getJSONArray("questions");  // Array of Questions
				List<QuestionData> questions = new ArrayList<QuestionData>();
				for(int i = 0; i < questionsArr.length(); i++){
					//
					JSONObject qObj = questionsArr.getJSONObject(i);
					
					// TODO: parse each attribute of each QuestionData instance.

					questions.add(new QuestionData());
				}

				
			}
		}
	}

	private String stringFromJsonFile(String filePath){
		return new String(Files.readAllBytes(Paths.get(filePath)));
	}

}
