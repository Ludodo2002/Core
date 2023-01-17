package fr.redcraft.wishescore.data;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.logging.Level;

public abstract class ConfigFolder<T> {

    private final JavaPlugin plugin;

    public ConfigFolder(JavaPlugin plugin) {
        this.plugin = plugin;
        create(true);
    }

    public abstract File folder();
    public abstract FilenameFilter filter();

    public abstract String name();

    public abstract Function<YamlConfiguration,T> loadProcess();
    public abstract Consumer<List<T>> saveProcess();

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public void create(boolean sendCreationMessage) {
        if (folder().exists()) return;
        folder().mkdirs();
        if (sendCreationMessage) getPlugin().getLogger().log(Level.INFO,"Dossier de configuration \""+name()+"\" créé !");
    }

    public File[] getFiles() {
        if (!folder().exists()) create(true);
        return folder().listFiles(filter());
    }

    public YamlConfiguration config(File file) {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try { file.createNewFile(); }
            catch (IOException e) { e.printStackTrace(); }
        }
        return YamlConfiguration.loadConfiguration(file);
    }

    public List<T> load() {
        List<T> returned = new ArrayList<>();
        for (File file : getFiles()) {
            YamlConfiguration configuration = config(file);
            returned.add(loadProcess().apply(configuration));
        }
        return returned;
    }

    public void save(List<T> objects) {
        saveProcess().accept(objects);
    }
}
