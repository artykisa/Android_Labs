package com.example.mulpiplayertest;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class gameClass {
    playerClass player1;
    playerClass player2;
    FirebaseDatabase database;
    DatabaseReference playerRef;

    gameClass(playerClass player1, playerClass player2){
        this.player1 = player1;
        this.player2 = player2;
        database = FirebaseDatabase.getInstance();
    }

    boolean shot(boolean player, int target){
        if(player){
            if(player2.playerShips.contains(target)){
                player2.playerShips.remove(target);
                return true;
            }

        }
        else{
            if(player1.playerShips.contains(target)){
                player1.playerShips.remove(target);
                return true;
            }
        }
        return false;
    }

    void endGame(){

    }
}
