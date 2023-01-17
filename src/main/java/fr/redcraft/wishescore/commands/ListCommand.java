package fr.redcraft.wishescore.commands;

import fr.redcraft.wishescore.api.AbstractCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class ListCommand extends AbstractCommand {

    public ListCommand(){
        super(false,"list","online","who");
    }

    @Override
    protected ReturnType runCommand(CommandSender sender, String... args) {
        sender.sendMessage("§fOnline players §7: §5" + Bukkit.getOnlinePlayers().size() + " §7/ §5" + Bukkit.getServer().getMaxPlayers());
        sender.sendMessage("§5------------------");
        for(Player player : Bukkit.getOnlinePlayers()){
            sender.sendMessage("§5- §f" + player.getName());
        }
        return ReturnType.SUCCESS;
    }

    @Override
    protected List<String> onTab(CommandSender sender, String... args) {
        return null;
    }

    @Override
    public String getPermissionNode() {
        return "wish.list";
    }

    @Override
    public String getSyntax() {
        return "list";
    }

    @Override
    public String getDescription() {
        return "get list online players";
    }
}
