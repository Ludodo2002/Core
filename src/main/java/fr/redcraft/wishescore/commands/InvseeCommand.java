package fr.redcraft.wishescore.commands;

import fr.redcraft.wishescore.api.AbstractCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class InvseeCommand extends AbstractCommand {

    public InvseeCommand(){
        super(true,"invsee");
    }

    @Override
    protected ReturnType runCommand(CommandSender sender, String... args) {
        if (args.length > 0) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target != null) {
                Player player = (Player) sender;
                player.openInventory(target.getInventory());
            } else {
                return ReturnType.ERROR_PLAYER;
            }
        } else {
            return ReturnType.SYNTAX_ERROR;
        }
        return ReturnType.SUCCESS;
    }

    @Override
    protected List<String> onTab(CommandSender sender, String... args) {
        return null;
    }

    @Override
    public String getPermissionNode() {
        return "wish.invsee";
    }

    @Override
    public String getSyntax() {
        return "invsee <player>";
    }

    @Override
    public String getDescription() {
        return "get player inventory";
    }
}
