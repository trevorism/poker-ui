package com.brooks.poker.ui

import com.brooks.poker.game.PokerGame
import com.brooks.poker.game.data.BlindsAnte
import com.brooks.poker.game.data.GameState
import com.brooks.poker.player.Player
import com.trevorism.poker.bots.ChaosPokerBot
import com.trevorism.poker.bots.CheatingPokerBot
import com.trevorism.poker.bots.DumbPokerBot
import com.trevorism.poker.bots.SimplePokerBot

/**
 * @author Trevor
 */
class PlayPoker {

    static void main(String[] args) {

        Player trevor = new Player("Trevor", 500, new UserPromptedAction())
        //Player trevor = new Player("Trevor", 500, new AlwaysCallPlayerAction())
        Player vaughn = new Player("Vaughn", 500, new DumbPokerBot())
        Player brooks = new Player("Brooks", 500, new ChaosPokerBot())
        Player sean = new Player("Sean", 500, new SimplePokerBot())
        Player elliott = new Player("Elliott", 500, new CheatingPokerBot())
        List<Player> players = [trevor, vaughn, brooks, sean, elliott]

        GameState gameState = GameState.configureTournamentGameState(BlindsAnte.STANDARD_TOURNAMENT, players)
        def adaptor = new PrintGameState()
        gameState.addGameStateHandler(adaptor)

        PokerGame.playGame(gameState)

    }


}
