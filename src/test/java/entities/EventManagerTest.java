package entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.List;
import java.io.File;

/**
 * EventManagerTest tests the functionality of EventManager, specifically:
 * loading all events from data/Events.json into a Map data structure mapping
 * the UUIDs of events to their respective Event objects.
 * .
 * (Note: This class also covers testing for Event, CombatEvent, EnemyData, QuestionData)
 */
public class EventManagerTest {

    /**
     * EventManagerObjectTypeCorrectness tests if we read from Events.java,
     * then the types of objects within the Map would be correct in respect
     * to their UUIDs. (If UUID is even, then it's an Event, if odd, then CombatEvent.)
     */
    @Test
    public void EventManagerObjectTypeCorrectness(){
        EventManager eventManager = new EventManager();
        Map<Integer, Event> events = eventManager.getAllEvents();

        boolean correctness = true;
        for(Event e : events.values()){
            if(e.getUUID() % 2 == 0 && e instanceof CombatEvent) {correctness = false;}
            if(e.getUUID() % 2 == 1 && !(e instanceof CombatEvent)) {correctness = false;}
        }

        Assertions.assertTrue(correctness);
    }

    /**
     * EventManagerEventInstanceVarCorrectness tests each Event instance inside
     * the map to see if its instance variables do contain the correct values.
     */
    @Test
    public void EventManagerEventInstanceVarCorrectness(){
        EventManager eventManager = new EventManager();
        Map<Integer, Event> events = eventManager.getAllEvents();

        for(Event e : events.values()){
            if(!(e instanceof CombatEvent)){  // If e is an Event, not a CombatEvent
                // Testing UUID and narration:
                Assertions.assertTrue((e.UUID % 2 == 0) && (e.UUID >= 0));
                Assertions.assertTrue(e.narration.length() > 0);

                // Testing the choices arrays:
                Assertions.assertTrue(e.choicesNarrations.size() > 0);
                Assertions.assertTrue(e.choicesNextUUIDs.size() > 0);
                Assertions.assertEquals(e.choicesNextUUIDs.size(), e.choicesNarrations.size());

                // Testing the soundFile directory:
                File f = new File(e.soundFile);
                Assertions.assertTrue(f.exists() && !(f.isDirectory()));

                // Note: doesAutoSave is not tested here.
            }
        }
    }

    /**
     * EventManagerCombatEventInstanceVarCorrectness tests if each CombatEvent instance inside
     * the map to see if its instance variables do contain the correct values.
     */
    @Test
    public void EventManagerCombatEventInstanceVarCorrectness(){
        EventManager eventManager = new EventManager();
        Map<Integer, Event> events = eventManager.getAllEvents();

        for(Event e : events.values()){
            if(e instanceof CombatEvent){  // If e is a CombatEvent
                // Testing UUID and narration:
                Assertions.assertTrue((e.UUID % 2 != 0) && (e.UUID >= 0));
                Assertions.assertTrue(e.narration.length() > 0);

                // Testing the choices arrays:
                Assertions.assertTrue(e.choicesNarrations.size() > 0);
                Assertions.assertTrue(e.choicesNextUUIDs.size() > 0);
                Assertions.assertEquals(e.choicesNextUUIDs.size(), e.choicesNarrations.size());

                // Testing the soundFile directory:
                File f = new File(e.soundFile);
                Assertions.assertTrue(f.exists() && !(f.isDirectory()));

                // Testing that doesAutoSave is FALSE:
                Assertions.assertFalse(e.doesAutoSave);  // A rule that Event.json must follow!

                // Testing EnemyData within CombatEvent:
                EnemyData enemy = ((CombatEvent) e).enemy;
                Assertions.assertTrue(enemy.getName().length() > 0);
                Assertions.assertTrue(enemy.getHealth() > 0);
                Assertions.assertTrue(enemy.generateAttackValue() > 0);  // attack value generation

                // Testing QuestionData ARRAY within CombatEvent:
                List<QuestionData> questions = ((CombatEvent) e).questions;
                Assertions.assertTrue(questions.size() > 0);  // must have at least 1 question!

                for(QuestionData q : questions){
                    Assertions.assertTrue(q.getQuestion().length() > 0);

                    Assertions.assertTrue(q.getAnswers().size() > 0);
                    Assertions.assertEquals(q.getAnswers().size(), q.getResponses().size());
                    Assertions.assertEquals(q.getAnswers().size(), q.getAttackValues().size());
                }
            }
        }
    }
}
