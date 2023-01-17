package fr.redcraft.wishescore.data;

import fr.redcraft.wishescore.helper.Helper;
import fr.redcraft.wishescore.user.UserData;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;

public class UserDataConfig extends ConfigFolder<UserData> {

    public UserDataConfig(JavaPlugin plugin){
        super(plugin);
    }

    @Override
    public File folder() {
        return new File(getPlugin().getDataFolder(),"data");

    }

    @Override
    public FilenameFilter filter() {
        return (dir, name) -> Helper.isUUID(name.split("\\.")[0]) && name.endsWith(".yml");

    }

    @Override
    public String name() {
        return "Systeme joueurs";
    }

    @Override
    public Function<YamlConfiguration, UserData> loadProcess() {
        return config -> {
            String uuidS = config.getString("uuid");
            if (!Helper.isUUID(uuidS)) return null;
            UUID uuid = UUID.fromString(uuidS);
            boolean msgtoggle = config.getBoolean("msgtoggle");
            boolean socialspy = config.getBoolean("socialspy");
            return new UserData(uuid, msgtoggle,socialspy);
        };
    }

    @Override
    public Consumer<List<UserData>> saveProcess() {
        return ePlayers -> {
            for (UserData playerData : ePlayers) {
                File file = new File(getPlugin().getDataFolder(), "data/" + playerData.getUuid().toString()+".yml");
                YamlConfiguration config = config(file);
                config.set("uuid",playerData.uuid.toString());
                config.set("msgtoggle",playerData.isMsgtoggle());
                config.set("socialspy",playerData.isSocialspy());
                try { config.save(file); }
                catch (IOException e) { throw new UncheckedIOException(e); }
            }
        };
    }
}

