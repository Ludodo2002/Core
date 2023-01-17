package fr.redcraft.wishescore.commands.signs;

import fr.redcraft.wishescore.Core;
import fr.redcraft.wishescore.api.AbstractCommand;
import fr.redcraft.wishescore.helper.Helper;
import org.bukkit.block.Sign;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PasteSignCommand extends AbstractCommand {

    public PasteSignCommand(){
        super(true,"paste");
    }

    @Override
    protected ReturnType runCommand(CommandSender sender, String... args) {
        Player player = (Player) sender;
        if (args.length > 0) {
            if (Core.signs.containsKey(player)) {
                if (Helper.isInt(args[0])) {
                    Integer line = Integer.parseInt(args[0]);
                    Sign sign = Core.signs.get(player);
                    if(Core.strsignes.containsKey(player)){
                        String strline = Core.strsignes.get(player);
                        sign.setLine(line,strline);
                        sign.update();
                        sign.update(true);
                        player.sendMessage(Core.PREFIX + "You just pasted a line");
                    }else {
                        player.sendMessage(Core.PREFIX + "§cYou did not copy any line");
                        return ReturnType.FAILURE;
                    }
                }else {
                    player.sendMessage(Core.PREFIX + "§cYou must use an integer");
                    return ReturnType.FAILURE;
                }
            }else {
                player.sendMessage(Core.PREFIX + "§cYou must click on a sign to modify it");
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
            types.add("0");
            types.add("1");
            types.add("2");
            types.add("3");
            Collections.sort(types);
            return Helper.getStartingWith(types, args[0]);
        }
        return null;
    }

    @Override
    public String getPermissionNode() {
        return "wish.editsign.paste";
    }

    @Override
    public String getSyntax() {
        return "/editsign paste [line number]";
    }

    @Override
    public String getDescription() {
        return "paste line of sign";
    }
}

