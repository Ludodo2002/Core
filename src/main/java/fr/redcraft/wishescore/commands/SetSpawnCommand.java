package fr.redcraft.wishescore.commands;

import fr.redcraft.wishescore.Core;
import fr.redcraft.wishescore.api.AbstractCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SetSpawnCommand extends AbstractCommand {

    public SetSpawnCommand(){
        super(true,"setspawn");
    }

    @Override
    protected ReturnType runCommand(CommandSender sender, String... args) {
        Player player = (Player) sender;
        Core.getInstance().getConfig().set("spawn",player.getLocation());
        Core.getInstance().saveConfig();
        player.sendMessage(Core.PREFIX + "Spawn point set !");
        return ReturnType.SUCCESS;
    }

    @Override
    protected List<String> onTab(CommandSender sender, String... args) {
        return null;
    }

    @Override
    public String getPermissionNode() {
        return "wish.setspawn";
    }

    @Override
    public String getSyntax() {
        return "setspawn";
    }

    @Override
    public String getDescription() {
        return "set spawn point";
    }
}
