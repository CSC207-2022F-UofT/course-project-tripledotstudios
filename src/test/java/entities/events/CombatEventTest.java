package entities.events;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.List;

/**
 * CombatEventTest tests the functionality inside CombatEvent, namely
 * the getRandomQuestion function.
 */
public class CombatEventTest {
    /**
     * CombatEventGetRandomQuestion tests the correctness of
     * getRandomQuestion() function inside a CombatEvent object.
     */
    @Test
    public void CombatEventGetRandomQuestion(){
        // First, read from Events.json file to get all events.
        EventManager eventManager = new EventManager();
        Map<Integer, Event> events = eventManager.getAllEvents();

        for(Event e : events.values()){  // Loop through all events
            if(e instanceof CombatEvent){  // If it is a combat event:

                List<QuestionData> questions = ((CombatEvent) e).questions;

                QuestionData q = ((CombatEvent) e).getRandomQuestion();

                Assertions.assertTrue(questions.contains(q));  // Test if q is inside questions.

            }
        }
    }
}
