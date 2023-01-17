package fr.redcraft.wishescore.manager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class AFKManager {

    private long MOVEMENT_THRESHOLD = 60000L;

    private HashMap<Player, Long> lastMovement = new HashMap<>();
    private HashMap<Player, Boolean> previousData = new HashMap<>();

    public void playerJoined(Player player){
        lastMovement.put(player, System.currentTimeMillis());
    }

    public void playerLeft(Player player){
        lastMovement.remove(player);
    }

    public boolean toggleAFKStatus(Player player){
        if (isAFK(player)){
            previousData.put(player, false);
            lastMovement.put(player, System.currentTimeMillis());
            return false;
        }else{
            previousData.put(player, true);
            lastMovement.put(player, -1L);
            return true;
        }
    }

    public void playerMoved(Player player){
        lastMovement.put(player, System.currentTimeMillis());
        checkPlayerAFKStatus(player);

    }

    public boolean isAFK(Player player){
        if(lastMovement.containsKey(player)){
            if(lastMovement.get(player) == -1L){
                return true;
            }else{
                long timeElapsed = System.currentTimeMillis() - lastMovement.get(player);
                if(timeElapsed >= MOVEMENT_THRESHOLD){
                    return true;
                }
            }
        }else{
            lastMovement.put(player, System.currentTimeMillis());
        }

        return false;
    }

    public void checkAllPlayersAFKStatus(){
        for (Map.Entry<Player, Long> entry : lastMovement.entrySet()){
            checkPlayerAFKStatus(entry.getKey());
        }
    }

    public void checkPlayerAFKStatus(Player player){
        if (lastMovement.containsKey(player)){
            boolean nowAFK = isAFK(player);
            if (previousData.containsKey(player)){
                boolean wasAFK = previousData.get(player);
                if(wasAFK && !nowAFK){
                    player.sendMessage("You are no longer AFK");
                    previousData.put(player, false);
                    announceToOthers(player, false);
                }else if(!wasAFK && nowAFK){
                    player.sendMessage("You are now AFK!");
                    previousData.put(player, true);
                    announceToOthers(player, true);
                }
            }else{
                previousData.put(player, nowAFK);
            }
        }
    }

    public void announceToOthers(Player target, boolean isAFK){
        Bukkit.getServer().getOnlinePlayers().stream()
                .forEach(player -> {
                    if (isAFK){
                        player.sendMessage(target.getDisplayName() + " is now AFK.");
                        target.setPlayerListName("{UNICODE} " + target.getName());
                    }else{
                        player.sendMessage(target.getDisplayName() + " is no longer AFK.");
                        target.setPlayerListName(target.getName());

                    }
                });
    }
}
