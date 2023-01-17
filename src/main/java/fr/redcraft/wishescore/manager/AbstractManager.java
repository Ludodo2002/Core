package fr.redcraft.wishescore.manager;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;

import java.util.List;

public abstract class AbstractManager implements Listener {

    private final JavaPlugin plugin;
    public JavaPlugin getPlugin() { return plugin; }

    public AbstractManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void onEnable() {
        if (listeners() != null) listeners().forEach((l) ->  plugin.getServer().getPluginManager().registerEvents(l, plugin));
    }

    public void onDisable() {

    }
    public void onReload() {

    }
    public abstract List<Listener> listeners();
}
