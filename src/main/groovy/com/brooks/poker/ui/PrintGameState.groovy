package com.brooks.poker.ui

import com.brooks.poker.cards.HandValue
import com.brooks.poker.game.data.GameState
import com.brooks.poker.game.handler.GameStateHandlerAdaptor
import com.brooks.poker.player.Player

class PrintGameState extends GameStateHandlerAdaptor {

	Map<String,Integer> chipStatus = [:]

	@Override
	void handleFirstBetState(GameState gameState) {
		handleBetState(gameState)
	}

	@Override
	void handleFlopBetState(GameState gameState) {
		handleBetState(gameState)
	}

	@Override
	void handleTurnBetState(GameState gameState) {
		handleBetState(gameState)
	}

	@Override
	void handleRiverBetState(GameState gameState) {
		handleBetState(gameState)
	}

	private void handleBetState(GameState gameState) {
		chipStatus = [:]
		gameState.table.getSortedActivePlayers().each {
			chipStatus.put(it.name, it.getChipCount())
		}
	}

	@Override
	void handleEndHandState(GameState gameState) {
		println "*************** Hand Results *********************"
		gameState.table.allPlayers.each {
			if(gameState.table.isInactive(it))
				return
			if(it.hand.handValue.type != HandValue.HandValueType.NULL_VALUE)
				println "$it :: $it.hand.handValue"

		}
		gameState.table.getSortedActivePlayers().each {
			if(chipStatus.get(it.name) < it.getChipCount()){
				println "Winner : ${it.name} ($it.chipCount)"
			}
		}
		println "**************************************************"

	}

	@Override
	void handleEndGameState(GameState gameState) {
		println gameState.getTable().getAllPlayers()[0]
	}
	
	private void printPlayer(Player player){
		print "${player} :: "

	}
	
}
