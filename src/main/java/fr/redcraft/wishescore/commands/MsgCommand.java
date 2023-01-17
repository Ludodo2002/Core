package fr.redcraft.wishescore.commands;

import fr.redcraft.wishescore.Core;
import fr.redcraft.wishescore.api.AbstractCommand;
import fr.redcraft.wishescore.manager.PlayerManager;
import fr.redcraft.wishescore.user.UserData;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

public class MsgCommand extends AbstractCommand {

    public static HashMap<String, String> messages = new HashMap<String, String>();

    public MsgCommand(){
        super(false,"msg","w", "m", "t", "pm", "tell", "whisper");
    }

    @Override
    protected ReturnType runCommand(CommandSender sender, String... args) {
        if(args.length < 2){
            return ReturnType.SYNTAX_ERROR;
        }

        Player target = Bukkit.getPlayer(args[0]);
        UserData ePlayer = PlayerManager.getPlayerData(target);
        if(!ePlayer.isMsgtoggle()){
            sender.sendMessage(Core.PREFIX + "The player has disabled private messages");
            return ReturnType.FAILURE;
        }

        if(target == null){
            return ReturnType.ERROR_PLAYER;
        }

        StringBuilder messageBuilder = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            messageBuilder.append(args[i]).append(" ");
        }
        messages.put(sender.getName(), args[0]);
        messages.put(args[0], sender.getName());
        String message = messageBuilder.toString().trim();
        target.sendMessage(sender.getName() + " -> §7[§5ME§7] §e:§r " + message);
        sender.sendMessage("§7[§5ME§7]§f -> " + messages.get(sender.getName()) + " §e:§r " + message);
        return ReturnType.SUCCESS;
    }

    @Override
    protected List<String> onTab(CommandSender sender, String... args) {
        return null;
    }

    @Override
    public String getPermissionNode() {
        return "wish.msg";
    }

    @Override
    public String getSyntax() {
        return "/msg [player] [message]";
    }

    @Override
    public String getDescription() {
        return "send message to another player";
    }
}