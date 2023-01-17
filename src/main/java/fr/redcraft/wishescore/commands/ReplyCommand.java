package fr.redcraft.wishescore.commands;

import fr.redcraft.wishescore.Core;
import fr.redcraft.wishescore.api.AbstractCommand;
import fr.redcraft.wishescore.manager.PlayerManager;
import fr.redcraft.wishescore.user.UserData;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Objects;

public class ReplyCommand extends AbstractCommand {

    public ReplyCommand(){
        super(false,"r","reply");
    }

    @Override
    protected ReturnType runCommand(CommandSender sender, String... args) {
        if(args.length < 1){
            return ReturnType.SYNTAX_ERROR;
        }
        UserData ePlayer = PlayerManager.getPlayerData(Objects.requireNonNull(Bukkit.getPlayer((String) MsgCommand.messages.get(sender.getName()))));
        if(!ePlayer.isMsgtoggle()){
            sender.sendMessage(Core.PREFIX + "The player has disabled private messages");
            return ReturnType.FAILURE;
        }
        if (!MsgCommand.messages.containsKey(sender.getName()))
        {
            sender.sendMessage(Core.PREFIX + "§cYou have no one to answer to");
            return ReturnType.FAILURE;
        }
        if (Bukkit.getPlayer((String)MsgCommand.messages.get(sender.getName())) == null)
        {
            return ReturnType.ERROR_PLAYER;
        }

        StringBuilder messageBuilder = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            messageBuilder.append(args[i]).append(" ");
        }
        String message = messageBuilder.toString().trim();
        Player target = Bukkit.getPlayer(MsgCommand.messages.get(sender.getName()));
        target.sendMessage(sender.getName() + " -> §7[§5ME§7] §e: §r " + message);
        sender.sendMessage("§7[§5ME§7]§f -> " + MsgCommand.messages.get(sender.getName()) + " §e:§r " + message);


        return ReturnType.SUCCESS;
    }

    @Override
    protected List<String> onTab(CommandSender sender, String... args) {
        return null;
    }

    @Override
    public String getPermissionNode() {
        return "wish.reply";
    }

    @Override
    public String getSyntax() {
        return "/r [player]";
    }

    @Override
    public String getDescription() {
        return "send message to anther player";
    }
}
