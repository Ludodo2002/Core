package fr.redcraft.wishescore.data;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.logging.Level;

public abstract class ConfigFile<T> {

    public abstract File file();
    public abstract String name();

    private final JavaPlugin plugin;

    public ConfigFile(JavaPlugin plugin) {
        this.plugin = plugin;
        create(true);
    }

    public abstract Function<YamlConfiguration, T> loadProcess();
    public abstract Consumer<T> saveProcess();

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public boolean create(boolean sendCreationMessage) {
        if (file().exists()) return true;
        file().getParentFile().mkdirs();
        try {
            file().createNewFile();
            if (sendCreationMessage) getPlugin().getLogger().log(Level.INFO,"Configuration \"" + name() + "\" create !");
            return true;
        } catch (IOException e) { getPlugin().getLogger().log(Level.WARNING,"Erreur à la création de la configuration \""+name()+"\" !"); }
        return false;
    }

    public YamlConfiguration config() {
        if (!file().exists()) if (!create(true)) return null;
        return YamlConfiguration.loadConfiguration(file());
    }

    public T load() {
        YamlConfiguration configuration = config();
        if (configuration == null) return null;
        try { return loadProcess().apply(config()); }
        catch (Exception e) { e.printStackTrace(); return null;}
    }

    public void save(T obj) {
        try { saveProcess().accept(obj); }
        catch (Exception e) { e.printStackTrace(); }
    }

}

