package fr.redcraft.wishescore.commands;

import fr.redcraft.wishescore.Core;
import fr.redcraft.wishescore.api.AbstractCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.List;

public class HelpCommand extends AbstractCommand {
    private static final int COMMAND_PER_PAGE = 5;

    public HelpCommand(){
        super(false,"help");
    }

    @Override
    protected ReturnType runCommand(CommandSender sender, String... args) {
        Player player = (Player) sender;
        ArrayList<AbstractCommand> commands = new ArrayList<AbstractCommand>(Core.getCommandManager().getAllCommands());

        int pageCount = (commands.size() - 1) / COMMAND_PER_PAGE + 1;

        int page = 1;
        if (args.length == 1) {
            try {
                page = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {}

            if (page > pageCount) {
                return ReturnType.FAILURE;
            }
        }
        sender.sendMessage("(Page " + page + "/" + pageCount + "):");
        for (int i = (page-1) * COMMAND_PER_PAGE; i < page * COMMAND_PER_PAGE && i < commands.size(); i++) {
            AbstractCommand abstractCommand = commands.get(i);
            sender.sendMessage(abstractCommand.getSyntax() + " | " + abstractCommand.getPermissionNode());
        }

        if (page < pageCount) {
            sender.sendMessage("/help list " + (page + 1));
        }
        return ReturnType.SUCCESS;
    }

    @Override
    protected List<String> onTab(CommandSender sender, String... args) {
        return null;
    }

    @Override
    public String getPermissionNode() {
        return "wish.help";
    }

    @Override
    public String getSyntax() {
        return "/help <page>";
    }

    @Override
    public String getDescription() {
        return "get all commands";
    }
}
