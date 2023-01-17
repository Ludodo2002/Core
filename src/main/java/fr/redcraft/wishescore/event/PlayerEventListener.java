package fr.redcraft.wishescore.event;

import fr.redcraft.wishescore.Core;
import fr.redcraft.wishescore.manager.PlayerManager;
import fr.redcraft.wishescore.user.UserData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerEventListener implements Listener {

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String sender = player.getName();
        String message = event.getMessage();
        UserData ePlayer = PlayerManager.getPlayerData(player);

        for(Player pls : Bukkit.getOnlinePlayers()) {
            if(ePlayer.isSocialspy()) {
                pls.sendMessage("§4" + sender + "§8§l>> §6" + message);
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        Core.getAfkManager().playerJoined(event.getPlayer());
        if(!PlayerManager.getPlayerMap().containsKey(event.getPlayer().getUniqueId())){
            PlayerManager.getPlayerMap().put(player.getUniqueId(),new UserData(player.getUniqueId(),true,false));
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Core.getAfkManager().playerLeft(event.getPlayer());
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Core.getAfkManager().playerMoved(event.getPlayer());
    }
}
