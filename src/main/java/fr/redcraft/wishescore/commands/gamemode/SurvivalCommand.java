package fr.redcraft.wishescore.commands.gamemode;

import fr.redcraft.wishescore.Core;
import fr.redcraft.wishescore.api.AbstractCommand;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SurvivalCommand extends AbstractCommand {

    public SurvivalCommand(){
        super(false,"gms");
    }

    @Override
    protected ReturnType runCommand(CommandSender sender, String... args) {
        if(args.length > 0){
            Player target = Bukkit.getPlayer(args[0]);
            if (target != null) {
                target.setGameMode(GameMode.SURVIVAL);
                sender.sendMessage(Core.PREFIX + "Set game mode Survival for " + target.getName());
                return ReturnType.SUCCESS;

            }else{
                return ReturnType.ERROR_PLAYER;
            }

        }else{
            Player player = (Player) sender;
            player.setGameMode(GameMode.SURVIVAL);
            sender.sendMessage(Core.PREFIX + "Set game mode Survival");
            return ReturnType.SUCCESS;
        }
    }

    @Override
    protected List<String> onTab(CommandSender sender, String... args) {
        return null;
    }

    @Override
    public String getPermissionNode() {
        return "wish.gamemode.survival";
    }

    @Override
    public String getSyntax() {
        return "/gms";
    }

    @Override
    public String getDescription() {
        return "set to gamemode survival";
    }
}
