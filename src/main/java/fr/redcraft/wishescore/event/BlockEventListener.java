package fr.redcraft.wishescore.event;

import fr.redcraft.wishescore.Core;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

public class BlockEventListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(Objects.requireNonNull(event.getClickedBlock()).getState() instanceof Sign){
                Sign sign = (Sign) event.getClickedBlock().getState();
                if(Core.signs.containsKey(event.getPlayer())){
                    Core.signs.remove(event.getPlayer());
                    Core.signs.put(event.getPlayer(), sign);
                }else {
                    Core.signs.put(event.getPlayer(), sign);
                }
            }
        }
    }
}
