package fr.redcraft.wishescore.commands;

import fr.redcraft.wishescore.Core;
import fr.redcraft.wishescore.api.AbstractCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class FlyCommand extends AbstractCommand {

    public FlyCommand(){
        super(false,"fly");
    }

    @Override
    protected ReturnType runCommand(CommandSender sender, String... args) {
        if (args.length > 0) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null) {
                    if (target.getAllowFlight()) {
                        sender.sendMessage(Core.PREFIX + "Flying off");
                        target.setAllowFlight(false);
                        return ReturnType.SUCCESS;
                    } else {
                        target.setAllowFlight(true);
                        sender.sendMessage(Core.PREFIX + "Flying on");
                        return ReturnType.SUCCESS;
                    }
                } else {
                    return ReturnType.ERROR_PLAYER;

                }
        } else {
            Player player = (Player) sender;
            if (player.getAllowFlight()) {
                player.sendMessage(Core.PREFIX + "Flying off");
                player.setAllowFlight(false);
                return ReturnType.SUCCESS;
            } else {
                player.setAllowFlight(true);
                player.sendMessage(Core.PREFIX + "Flying on");
                return ReturnType.SUCCESS;
            }
        }
    }

    @Override
    protected List<String> onTab(CommandSender sender, String... args) {
        return null;
    }

    @Override
    public String getPermissionNode() {
        return "wish.fly";
    }

    @Override
    public String getSyntax() {
        return "fly <player>";
    }

    @Override
    public String getDescription() {
        return "fly";
    }
}