package fr.redcraft.wishescore.commands;

import fr.redcraft.wishescore.Core;
import fr.redcraft.wishescore.api.AbstractCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class AFKCommand extends AbstractCommand {

    public AFKCommand(){
        super(true,"afk");
    }

    @Override
    protected ReturnType runCommand(CommandSender sender, String... args) {
        Player player = (Player) sender;
        if(Core.getAfkManager().toggleAFKStatus(player)){
            Core.getAfkManager().announceToOthers(player,true);
        }else {
            Core.getAfkManager().announceToOthers(player,false);
        }
        return ReturnType.SUCCESS;
    }

    @Override
    protected List<String> onTab(CommandSender sender, String... args) {
        return null;
    }

    @Override
    public String getPermissionNode() {
        return "wish.afk";
    }

    @Override
    public String getSyntax() {
        return "/afk";
    }

    @Override
    public String getDescription() {
        return "afk";
    }
}
