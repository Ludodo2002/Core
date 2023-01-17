package fr.redcraft.wishescore.manager;

import fr.redcraft.wishescore.data.UserDataConfig;
import fr.redcraft.wishescore.user.UserData;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class PlayerManager extends AbstractManager{

    public static PlayerManager instance;
    public UserDataConfig ePlayerData;
    private static Map<UUID, UserData> playerMap;

    public PlayerManager(JavaPlugin plugin) {
        super(plugin);
        instance = this;
    }


    @Override
    public void onEnable() {
        this.ePlayerData = new UserDataConfig(getPlugin());
        playerMap = getePlayerData().load().stream().collect(Collectors.toMap(UserData::getUuid, c->c));
    }

    @Override
    public void onDisable() {
        getePlayerData().save(getPlayerMap().values().stream().toList());
    }

    @Override
    public List<Listener> listeners() {
        return List.of();
    }

    public UserDataConfig getePlayerData() {
        return ePlayerData;
    }

    public static Map<UUID, UserData> getPlayerMap() {
        return playerMap;
    }

    public static UserData getPlayerData(Player player){
        return getPlayerMap().get(player.getUniqueId());
    }
}

