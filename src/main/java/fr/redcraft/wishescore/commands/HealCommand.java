package fr.redcraft.wishescore.commands;

import fr.redcraft.wishescore.api.AbstractCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class HealCommand extends AbstractCommand {

    public HealCommand(){
        super(false,"heal");
    }

    @Override
    protected ReturnType runCommand(CommandSender sender, String... args) {
        if (args.length > 0) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target != null) {
                target.setHealth(20D);
            } else {
                return ReturnType.ERROR_PLAYER;
            }
        } else {
            Player player = (Player) sender;
            player.setHealth(20D);
        }
        return ReturnType.SUCCESS;
    }

    @Override
    protected List<String> onTab(CommandSender sender, String... args) {
        return null;
    }

    @Override
    public String getPermissionNode() {
        return "wish.heal";
    }

    @Override
    public String getSyntax() {
        return "heal";
    }

    @Override
    public String getDescription() {
        return "heal";
    }
}

