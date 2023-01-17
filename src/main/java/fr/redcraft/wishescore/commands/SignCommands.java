package fr.redcraft.wishescore.commands;

import fr.redcraft.wishescore.api.AbstractCommand;
import org.bukkit.command.CommandSender;

import java.util.List;

public class SignCommands extends AbstractCommand {

    public SignCommands(){
        super(true,"editsign");
    }

    @Override
    protected ReturnType runCommand(CommandSender sender, String... args) {
        return ReturnType.SUCCESS;
    }

    @Override
    protected List<String> onTab(CommandSender sender, String... args) {
        return null;
    }

    @Override
    public String getPermissionNode() {
        return "wish.editsign";
    }

    @Override
    public String getSyntax() {
        return "editsign <set/clear/copy/paste> [line number] [text]";
    }

    @Override
    public String getDescription() {
        return "modify sign";
    }
}
