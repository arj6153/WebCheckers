package com.webcheckers.model;

import com.webcheckers.appl.GameCenter;

public class AIPlayer extends Player{

    /**
     * Constructor for AIPlayer.
     *
     */
    public AIPlayer(GameCenter gameCenter) {
       super("ai#"+gameCenter.getAiNum());
       gameCenter.addAiNum();
        System.out.println(this.getName());
    }
}
