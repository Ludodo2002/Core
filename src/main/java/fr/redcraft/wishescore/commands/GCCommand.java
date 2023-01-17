package fr.redcraft.wishescore.commands;

import fr.redcraft.wishescore.Core;
import fr.redcraft.wishescore.api.AbstractCommand;
import fr.redcraft.wishescore.helper.Helper;
import fr.redcraft.wishescore.helper.NumberUtil;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.command.CommandSender;

import java.lang.management.ManagementFactory;
import java.util.List;
import java.util.logging.Level;

public class GCCommand extends AbstractCommand {

    public GCCommand(){
        super(false,"gc","tps","uptime","memory","lag");
    }

    @Override
    protected ReturnType runCommand(CommandSender sender, String... args) {
        final double tps = MinecraftServer.TPS;
        final ChatColor color;
        if (tps >= 18.0) {
            color = ChatColor.GREEN;
        } else if (tps >= 15.0) {
            color = ChatColor.YELLOW;
        } else {
            color = ChatColor.RED;
        }
        sender.sendMessage("§5------------------------------------");
        sender.sendMessage("§f§lUptime §r§7: §5" + Helper.formatDateDiff(ManagementFactory.getRuntimeMXBean().getStartTime()));
        sender.sendMessage("§f§lTPS §r§7: §r" + color + NumberUtil.formatDouble(tps));
        sender.sendMessage("§f§lRAM-Max §r§7 : §r" + Runtime.getRuntime().maxMemory() / 1024 / 1024);
        sender.sendMessage("§f§lRAM-Total §r§7 : §r" + Runtime.getRuntime().totalMemory() / 1024 / 1024);
        sender.sendMessage("§f§lRAM-Free §r§7 : §r" + Runtime.getRuntime().freeMemory() / 1024 / 1024);
        sender.sendMessage("§5------------------------------------");

        final List<World> worlds = Bukkit.getWorlds();
        for (final World w : worlds) {
            String worldType = "World";
            switch (w.getEnvironment()) {
                case NETHER:
                    worldType = "Nether";
                    break;
                case THE_END:
                    worldType = "The End";
                    break;
            }

            int tileEntities = 0;

            try {
                for (Chunk chunk : w.getLoadedChunks()) {
                    tileEntities += chunk.getTileEntities().length;
                }
            } catch (final java.lang.ClassCastException ex) {
                Core.instance.getLogger().log(Level.SEVERE, "Corrupted chunk data on world " + w, ex);
            }
            sender.sendMessage("§fWorld '§5" + w.getName() + "§f': §5" + w.getLoadedChunks().length + " §fChunks, §5" + w.getEntities().size() + " §fentities, §5" + tileEntities + " §ftiles.");
        }
        return ReturnType.SUCCESS;
    }

    @Override
    protected List<String> onTab(CommandSender sender, String... args) {
        return null;
    }

    @Override
    public String getPermissionNode() {
        return "wish.gc";
    }

    @Override
    public String getSyntax() {
        return "gc";
    }

    @Override
    public String getDescription() {
        return "Reports memory, uptime and tick info.";
    }
}
