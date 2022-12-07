package usecases.combat;

import java.util.*;
import entities.events.*;
import entities.items.*;
import entities.player.*;

public class CombatInteractor implements CombatInputBoundary {
	
	EventManager events;
	CombatInterface view;
	PlayerInteractor player;

	/**
	 * Constructor of CombatInteractor.
	 * @param view reference to the CombatPresenter
	 * @param events reference to the EventManager class
	 * @param player the player in question
	 */
	public CombatInteractor(CombatInterface view, EventManager events, PlayerInteractor player){
		this.view = view;
		this.events = events;
		this.player = player;
	}

	/**
	* Gets called upon by usecases.story.StorylineInteractor whenever a combat event is encountered.
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
			List<ItemData> playerItems = player.getInventoryItems();
			playerWantsItemUse = false;

			if(playerItems.size() > 0) {
				view.askIfPlayerUsesItem();  // Asks if player wants to use an item.
				// Answer is returned by CombatController calling respondItemUse() function.
			}

			if(playerWantsItemUse){
				// Item Usage Support
				view.letPlayerChooseItem(playerItems);  // Let player choose the item to use, index is returned

				ItemData item = playerItems.get(itemChoice);
				player.removeFromInventory(item);
				view.informPlayerOfItemUse(item);  // Tell the player that the item is used and its effects

				if (item.getAttribute().equals("health")){
					playerCurrentHealth += item.getValue();
				}else{  // Assuming the attribute is "attack"
					enemyCurrentHealth -= item.getValue();
				}

			}else{
				QuestionData q = event.getRandomQuestion();
				view.askQuestion(q.getQuestion(), q.getAnswers(), q.getResponses(), true);
				int damage = q.getAttackValues().get(answerIndex);
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

	// Functions for CombatController to call (CombatInputBoundary)

	private boolean playerWantsItemUse;
	@Override
	public void respondItemUse(boolean playerWantsItemUse){
		this.playerWantsItemUse = playerWantsItemUse;
	}

	private int itemChoice;
	@Override
	public void playerChoosesItem(int itemChoice){
		this.itemChoice = itemChoice;
	}

	private int answerIndex;
	@Override
	public void returnAnswer(int answerIndex){
		this.answerIndex = answerIndex;
	}

}
