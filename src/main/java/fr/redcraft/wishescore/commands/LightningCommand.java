package fr.redcraft.wishescore.commands;

import fr.redcraft.wishescore.api.AbstractCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class LightningCommand extends AbstractCommand {

    public LightningCommand(){
        super(true,"lightning","strike","smite");
    }

    @Override
    protected ReturnType runCommand(CommandSender sender, String... args) {
        Player player = (Player) sender;
        player.getWorld().strikeLightning(player.getTargetBlock(null,600).getLocation());
        return ReturnType.SUCCESS;
    }

    @Override
    protected List<String> onTab(CommandSender sender, String... args) {
        return null;
    }

    @Override
    public String getPermissionNode() {
        return "wish.lightning";
    }

    @Override
    public String getSyntax() {
        return "lightning";
    }

    @Override
    public String getDescription() {
        return "strike";
    }
}
