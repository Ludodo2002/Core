package fr.redcraft.wishescore.commands;

import fr.redcraft.wishescore.Core;
import fr.redcraft.wishescore.api.AbstractCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class TopCommand extends AbstractCommand {

    public TopCommand(){
        super(true,"top");
    }

    @Override
    protected ReturnType runCommand(CommandSender sender, String... args) {
        Player player = (Player) sender;
        player.teleport(player.getWorld().getHighestBlockAt(player.getLocation()).getLocation().add(0,2,0));
        player.sendMessage(Core.PREFIX + "Teleporting to top.");
        return ReturnType.SUCCESS;
    }

    @Override
    protected List<String> onTab(CommandSender sender, String... args) {
        return null;
    }

    @Override
    public String getPermissionNode() {
        return "wish.top";
    }

    @Override
    public String getSyntax() {
        return "top";
    }

    @Override
    public String getDescription() {
        return "Teleport to the highest block at your current position.";
    }
}
