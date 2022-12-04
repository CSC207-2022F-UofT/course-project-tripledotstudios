package entities;

import java.util.List;

/**
 * The data class that stores an Event in the storyline.
 */
public class Event {
    protected final Integer UUID;  // The UUID of the Event
	
	// Important Feature Notice: If the UUID of the event is EVEN, then it's a normal event.
	// If the UUID of the event is ODD, then it's a combat event.
	
    /**
     * Returns the UUID of the Event.
     * @return UUID of the current Event (as an Integer).
     */
    public int getUUID() {
        return UUID;
    }

    protected final String narration;  // The main narration of the Event.
    /**
     * Returns the main narration of the Event as a String.
     * @return the main narration of the Event (as a String).
     */
    public String getNarration() {
        return narration;
    }

    protected final List<String> choicesNarrations;  // A list of the narration of the choices the player can make.
    protected final List<Integer> choicesNextUUIDs;  // A list of the UUIDs of Events the choices lead to.
    // Note that the indexes of the above two lists ALIGN WITH EACH OTHER.
    /**
     * Returns the list of the narration of the choices the player can make.
     * @return the list of the narration of the choices the player can make (as a list of Strings).
     */
    public List<String> getChoicesNarrations() {
        return choicesNarrations;
    }
    /**
     * Returns the list of the UUIDs of Events the choices the player can make lead to.
     * @return the list of the UUIDs of Events the choices lead to (as a list of Integers).
     */
    public List<Integer> getChoicesNextUUIDs() {
        return choicesNextUUIDs;
    }

    protected final String soundFile;  // Link to the sound file of the current event.
    /**
     * Returns the link of the sound file of the current Event.
     * @return the link of the sound file of the current Event (as a String).
     */
    public String getSoundFile() {
        return soundFile;
    }

    protected final boolean doesAutoSave;  // Whether the game auto-saves upon this Event or not
    /**
     * Returns whether the game auto-saves upon this Event or not.
     * @return whether the game auto-saves (as a boolean)
     */
    public boolean getDoesAutoSave() {
        return doesAutoSave;
    }

    public Event(int UUID, String narration, List<String> choicesNarrations,
                 List<Integer> choicesNextUUIDs, String soundFile, boolean doesAutoSave) {
        this.UUID = UUID;
        this.narration = narration;
        this.choicesNarrations = choicesNarrations;
        this.choicesNextUUIDs = choicesNextUUIDs;
        this.soundFile = soundFile;
        this.doesAutoSave = doesAutoSave;
    }

}
