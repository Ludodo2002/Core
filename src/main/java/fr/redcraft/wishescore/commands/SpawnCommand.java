package fr.redcraft.wishescore.commands;

import fr.redcraft.wishescore.Core;
import fr.redcraft.wishescore.api.AbstractCommand;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SpawnCommand extends AbstractCommand {

    public SpawnCommand(){
        super(true,"spawn");
    }

    @Override
    protected ReturnType runCommand(CommandSender sender, String... args) {
        Player player = (Player) sender;
        if(Core.getInstance().getConfig().contains("spawn")){
            Location location = Core.getInstance().getConfig().getLocation("spawn");
            player.teleport(location);
        }else {
            player.sendMessage(Core.PREFIX + "§cSpawn not set !");
            return ReturnType.FAILURE;
        }
        return ReturnType.SUCCESS;
    }

    @Override
    protected List<String> onTab(CommandSender sender, String... args) {
        return null;
    }

    @Override
    public String getPermissionNode() {
        return "wish.spawn";
    }

    @Override
    public String getSyntax() {
        return "spawn";
    }

    @Override
    public String getDescription() {
        return "teleport to spawn point";
    }
}
