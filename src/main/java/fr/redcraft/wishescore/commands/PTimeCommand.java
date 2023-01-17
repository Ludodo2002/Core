package fr.redcraft.wishescore.commands;

import fr.redcraft.wishescore.Core;
import fr.redcraft.wishescore.api.AbstractCommand;
import fr.redcraft.wishescore.helper.Helper;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PTimeCommand extends AbstractCommand {

    public PTimeCommand(){
        super(true,"ptime");
    }

    @Override
    protected ReturnType runCommand(CommandSender sender, String... args) {
        Player player = (Player) sender;
        if (args.length < 1)
        {
            return ReturnType.SYNTAX_ERROR;
        }
        long askedtime = 0;
        boolean undefined = true;
        if(!args[0].equalsIgnoreCase("reset")) {
            for(AssociedTimes time : AssociedTimes.values()) {
                if(!args[0].equalsIgnoreCase(time.toString())) continue;
                askedtime = time.ticks();
                undefined = false;
                break;
            }
        } else {
            player.resetPlayerTime();
            sender.sendMessage(Core.PREFIX + "Time set to " + player.getPlayerTime());
            return ReturnType.SUCCESS;
        }
        if(undefined) {
            try {
                askedtime = Long.parseLong(args[0]);
            } catch (IllegalArgumentException e) {
                sender.sendMessage(Core.PREFIX + "The value is not correct!");
                return ReturnType.SUCCESS;
            }
        }

        player.setPlayerTime(askedtime, false);
        sender.sendMessage(Core.PREFIX + "Time set to " + askedtime);
        return ReturnType.SUCCESS;
    }


    @Override
    protected List<String> onTab(CommandSender sender, String... args) {
        List<String> l = new ArrayList<String>();
        for(AssociedTimes time : AssociedTimes.values()) {
            l.add(time.toString());
        }
        l.add("reset");
        Collections.sort(l);
        return Helper.getStartingWith(l, args[0]);
    }

    @Override
    public String getPermissionNode() {
        return "wish.ptime";
    }

    @Override
    public String getSyntax() {
        return "ptime <time> [player]";
    }

    @Override
    public String getDescription() {
        return "Change player time";
    }
}
enum AssociedTimes {

    afternoon(9000),
    day(4000),
    midnight(18000),
    morning(1000),
    night(12000),
    noon(6000),
    sunrise(11000),
    sunset(1000);

    private long ticktime;

    private AssociedTimes(long time) {
        this.ticktime = time;
    }

    public long ticks() {
        return ticktime;
    }
}
