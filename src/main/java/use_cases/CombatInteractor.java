package use_cases;

import java.util.*;
import dataclasses.*;
import entities.PlayerInteractor;

public class CombatInteractor {

    EventManager events;
    CombatInterface view;
	PlayerInteractor player;

	/**
	 * Constructor of CombatInteractor.
	 * @param view reference to the CombatPresenter
	 * @param events reference to the EventManager class
	 */
    public CombatInteractor(CombatInterface view, EventManager events, PlayerInteractor player){
		this.view = view;
		this.events = events;
		this.player = player;
    }

    /**
     * Gets called upon by use_cases.StorylineInteractor whenever a combat event is encountered.
     * @param UUID the unique id of the CombatEvent. (MUST be a CombatEvent)
     * @return whether the player wins or not: true if won, false if lost
     */
	public boolean combat(int UUID){
        CombatEvent event = (CombatEvent) events.getAllEvents().get(UUID);

		int enemyMaxHealth = event.getEnemyMaxHealth();
		int enemyCurrentHealth = enemyMaxHealth;
		int playerMaxHealth = 100;  // Player health can be handled from PlayerInteractor later
		int playerCurrentHealth = playerMaxHealth;
        
        // Initialize the arena
		view.displayHealthBar(player.getPlayerUsername(), playerMaxHealth, playerCurrentHealth,
				event.getEnemyName(), enemyMaxHealth, enemyCurrentHealth);

		view.displayNarration(event.getNarration());

        while(enemyCurrentHealth > 0 && playerCurrentHealth > 0){  // Checking the ending condition of the combat
			if(view.playerUsesItem()){
				// TODO: Item Usage Support
			}else{
				QuestionData q = event.getRandomQuestion();
				int index = view.askQuestion(q.getQuestion(), q.getAnswers(), q.getResponses());
				int damage = q.getAttackValues().get(index);
				if(damage == -1){
					playerCurrentHealth -= event.generateEnemyAttackValue();
				}else{
					enemyCurrentHealth -= damage;
				}
			}
			view.updateHealthBar(player.getPlayerUsername(), playerMaxHealth, playerCurrentHealth,
					event.getEnemyName(), enemyMaxHealth, enemyCurrentHealth);
        }

		return enemyCurrentHealth <= 0;
    }

	public void respondItemUse(boolean playerWantsItemUse) {

	}

	public void playerChoosesItem(int itemChoice) {

	}

	public void returnAnswer(int answerIndex) {

	}
}
