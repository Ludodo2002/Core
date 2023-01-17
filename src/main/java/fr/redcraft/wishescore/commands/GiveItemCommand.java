package fr.redcraft.wishescore.commands;

import fr.redcraft.wishescore.Core;
import fr.redcraft.wishescore.api.AbstractCommand;
import fr.redcraft.wishescore.helper.Helper;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class GiveItemCommand extends AbstractCommand {

    public GiveItemCommand(){
        super(true,"item","i");
    }

    @Override
    protected ReturnType runCommand(CommandSender sender, String... args) {
        Player player = (Player) sender;
        if (args.length > 1) {
            if(Material.getMaterial(args[0]) != null){
                if(Helper.isInt(args[1])){
                    Integer amount = Integer.parseInt(args[1]);
                    ItemStack itemStack = new ItemStack(Objects.requireNonNull(Material.getMaterial(args[0])));
                    itemStack.setAmount(amount);
                    player.getInventory().addItem(itemStack);
                }else {
                    player.sendMessage(Core.PREFIX + "§cYou must use an integer");
                    return ReturnType.FAILURE;
                }
            }else {
                player.sendMessage(Core.PREFIX + "Material invalid.");
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
            for(Material material : Material.values()){
                types.add(material.name());
            }
            Collections.sort(types);
            return Helper.getStartingWith(types, args[0]);
        }
        if (args.length == 2) {
            ArrayList<String> types = new ArrayList<>();
            types.add("[amount]:Integer");
            Collections.sort(types);
            return Helper.getStartingWith(types, args[1]);
        }
        return null;
    }

    @Override
    public String getPermissionNode() {
        return "wish.give";
    }

    @Override
    public String getSyntax() {
        return "item <item> [amount]";
    }

    @Override
    public String getDescription() {
        return "Give item";
    }
}
