package fr.redcraft.wishescore.commands;

import fr.redcraft.wishescore.Core;
import fr.redcraft.wishescore.api.AbstractCommand;
import fr.redcraft.wishescore.manager.PlayerManager;
import fr.redcraft.wishescore.user.UserData;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SocialSpyCommand extends AbstractCommand {

    public SocialSpyCommand(){
        super(true,"socialspy");
    }

    @Override
    protected ReturnType runCommand(CommandSender sender, String... args) {
        Player player = (Player) sender;
        UserData ePlayer = PlayerManager.getPlayerData(player);
        if(ePlayer.isSocialspy()){
            ePlayer.socialspy = false;
            player.sendMessage(Core.PREFIX + "§aYou have just activated socialspy messages");
        }else {
            ePlayer.socialspy = true;
            player.sendMessage(Core.PREFIX + "§cYou have just disable socialspy messages");
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
        return "wish.socialspy";
    }

    @Override
    public String getSyntax() {
        return "/socialspy";
    }

    @Override
    public String getDescription() {
        return "toggle socialspy message";
    }
}

