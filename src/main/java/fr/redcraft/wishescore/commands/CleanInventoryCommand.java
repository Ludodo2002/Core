package fr.redcraft.wishescore.commands;

import fr.redcraft.wishescore.api.AbstractCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CleanInventoryCommand extends AbstractCommand {


    public CleanInventoryCommand(){
        super(true,"cleaninventory","clean","ci");
    }

    @Override
    protected ReturnType runCommand(CommandSender sender, String... args) {
        Player player = (Player) sender;
        player.getInventory().clear();
        return ReturnType.SUCCESS;
    }

    @Override
    protected List<String> onTab(CommandSender sender, String... args) {
        return null;
    }

    @Override
    public String getPermissionNode() {
        return "wish.cleaninventory";
    }

    @Override
    public String getSyntax() {
        return "cleaninventory";
    }

    @Override
    public String getDescription() {
        return "clean inventory";
    }
}
