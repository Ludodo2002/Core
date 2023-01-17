package fr.redcraft.wishescore.commands;

import fr.redcraft.wishescore.Core;
import fr.redcraft.wishescore.api.AbstractCommand;
import fr.redcraft.wishescore.helper.Helper;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WeatherCommand extends AbstractCommand {

    public WeatherCommand(){
        super(true,"weather");
    }

    @Override
    protected ReturnType runCommand(CommandSender sender, String... args) {
        Player player = (Player) sender;
        if (args.length > 1) {
            String type = args[0];
            if(type.equalsIgnoreCase("sun") || type.equalsIgnoreCase("strom") || type.equalsIgnoreCase("rain")){
                if(Helper.isInt(args[1])){
                    Integer duration = Integer.parseInt(args[1]);
                    switch (type){
                        case "sun":
                            player.getWorld().setStorm(false);
                            break;
                        case "strom":
                            player.getWorld().setThundering(true);
                            player.getWorld().setThunderDuration(duration);
                            break;
                        case "rain":
                            player.getWorld().setStorm(true);
                            player.getWorld().setWeatherDuration(duration);
                            break;
                    }
                    player.sendMessage(Core.PREFIX + "You just changed the weather");
                }else {
                    player.sendMessage(Core.PREFIX + "§cYou must use an integer");
                    return ReturnType.FAILURE;
                }
            }else {
                player.sendMessage(Core.PREFIX + "WeatherType : sun,Strom,rain");
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
            types.add("sun");
            types.add("strom");
            types.add("rain");
            Collections.sort(types);
            return Helper.getStartingWith(types, args[0]);
        }
        if (args.length == 2) {
            ArrayList<String> types = new ArrayList<>();
            types.add("[duration]:Integer");
            Collections.sort(types);
            return Helper.getStartingWith(types, args[1]);
        }
        return null;
    }

    @Override
    public String getPermissionNode() {
        return "wish.weather";
    }

    @Override
    public String getSyntax() {
        return "weather <weather_type> [duration]";
    }

    @Override
    public String getDescription() {
        return "Change weather of the world";
    }
}
