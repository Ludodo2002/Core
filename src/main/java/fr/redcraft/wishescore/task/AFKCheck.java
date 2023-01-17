package fr.redcraft.wishescore.task;

import fr.redcraft.wishescore.manager.AFKManager;

public class AFKCheck implements Runnable{
    private AFKManager afkManager;

    public AFKCheck(AFKManager afkManager) {
        this.afkManager = afkManager;
    }

    @Override
    public void run() {
        afkManager.checkAllPlayersAFKStatus();
    }
}
