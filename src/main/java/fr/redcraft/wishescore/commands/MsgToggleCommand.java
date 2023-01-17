package fr.redcraft.wishescore.commands;

import fr.redcraft.wishescore.Core;
import fr.redcraft.wishescore.api.AbstractCommand;
import fr.redcraft.wishescore.manager.PlayerManager;
import fr.redcraft.wishescore.user.UserData;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class MsgToggleCommand extends AbstractCommand {

    public MsgToggleCommand(){
        super(true,"msgtoggle");
    }

    @Override
    protected ReturnType runCommand(CommandSender sender, String... args) {
        Player player = (Player) sender;
        UserData ePlayer = PlayerManager.getPlayerData(player);
        if(ePlayer.isMsgtoggle()){
            ePlayer.msgtoggle = false;
            player.sendMessage(Core.PREFIX + "§aYou have just activated private messages");
        }else {
            ePlayer.msgtoggle = true;
            player.sendMessage(Core.PREFIX + "§cYou have just disable private messages");
        }
        PlayerManager.getPlayerMap().replace(player.getUniqueId(),ePlayer);
        return ReturnType.SUCCESS;
    }

    @Override
    protected List<String> onTab(CommandSender sender, String... args) {
        return null;
    }

    @Override
    public String getPermissionNode() {
        return "wish.msgtoggle";
    }

    @Override
    public String getSyntax() {
        return "/msgtoggle";
    }

    @Override
    public String getDescription() {
        return "toggle private message";
    }
}
