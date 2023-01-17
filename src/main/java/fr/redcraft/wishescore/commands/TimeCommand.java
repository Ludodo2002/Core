package fr.redcraft.wishescore.commands;

import fr.redcraft.wishescore.Core;
import fr.redcraft.wishescore.api.AbstractCommand;
import fr.redcraft.wishescore.helper.Helper;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TimeCommand extends AbstractCommand {

    public TimeCommand(){
        super(false,"time");
    }

    @Override
    protected ReturnType runCommand(CommandSender sender, String... args) {
        Player player = (Player) sender;
        if (args.length > 0) {
            if(Helper.isInt(args[0])){
                Integer time = Integer.parseInt(args[0]);
                player.getWorld().setTime(time);
                player.sendMessage(Core.PREFIX + "You just changed the time");
            }else {
                player.sendMessage(Core.PREFIX + "§cYou must use an integer");
                return ReturnType.FAILURE;
            }
        }else {
            return ReturnType.SYNTAX_ERROR;
        }
        return ReturnType.SUCCESS;
    }

    @Override
    protected List<String> onTab(CommandSender sender, String... args) {
        if (args.length == 1) {
            ArrayList<String> types = new ArrayList<>();
            types.add("[time]:Integer");
            Collections.sort(types);
            return Helper.getStartingWith(types, args[0]);
        }
        return null;
    }

    @Override
    public String getPermissionNode() {
        return "wish.time";
    }

    @Override
    public String getSyntax() {
        return "time <time>";
    }

    @Override
    public String getDescription() {
        return "change time on world";
    }
}
