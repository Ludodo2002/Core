package fr.redcraft.wishescore;

import fr.redcraft.wishescore.api.CommandManager;
import fr.redcraft.wishescore.commands.*;
import fr.redcraft.wishescore.commands.gamemode.AdventureCommand;
import fr.redcraft.wishescore.commands.gamemode.CreatifCommand;
import fr.redcraft.wishescore.commands.gamemode.SpectatorCommand;
import fr.redcraft.wishescore.commands.gamemode.SurvivalCommand;
import fr.redcraft.wishescore.commands.signs.ClearSignCommand;
import fr.redcraft.wishescore.commands.signs.CopySignCommand;
import fr.redcraft.wishescore.commands.signs.PasteSignCommand;
import fr.redcraft.wishescore.commands.signs.SetSignCommand;
import fr.redcraft.wishescore.event.BlockEventListener;
import fr.redcraft.wishescore.event.PlayerEventListener;
import fr.redcraft.wishescore.manager.AFKManager;
import fr.redcraft.wishescore.manager.AbstractManager;
import fr.redcraft.wishescore.manager.PlayerManager;
import fr.redcraft.wishescore.task.AFKCheck;
import fr.redcraft.wishescore.user.UserData;
import org.bukkit.Bukkit;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

public class Core extends JavaPlugin {

    public static Plugin instance;
    public static String PREFIX = "§5WishesCore §f: §r";
    public static CommandManager commandManager;
    public static AFKManager afkManager;
    public static HashMap<Player, Sign> signs = new HashMap<>();
    public static HashMap<Player,String> strsignes = new HashMap<>();
    private List<? extends AbstractManager> managers;

    @Override
    public void onEnable() {
        instance = this;
        commandManager = new CommandManager(this);
        commandManager.registerCommand(new TopCommand());
        commandManager.registerCommand(new SignCommands()).addSubCommand(new SetSignCommand()).addSubCommand(new ClearSignCommand()).addSubCommand(new CopySignCommand()).addSubCommand(new PasteSignCommand());
        commandManager.registerCommand(new WeatherCommand());
        commandManager.registerCommand(new GCCommand());
        commandManager.registerCommand(new TimeCommand());
        commandManager.registerCommand(new ListCommand());
        commandManager.registerCommand(new LightningCommand());
        commandManager.registerCommand(new GiveItemCommand());
        commandManager.registerCommand(new HealCommand());
        commandManager.registerCommand(new FeedCommand());
        commandManager.registerCommand(new SetSpawnCommand());
        commandManager.registerCommand(new SpawnCommand());
        commandManager.registerCommand(new CleanInventoryCommand());
        commandManager.registerCommand(new HelpCommand());
        commandManager.registerCommand(new FlyCommand());
        commandManager.registerCommand(new MsgCommand());
        commandManager.registerCommand(new ReplyCommand());
        commandManager.registerCommand(new MsgToggleCommand());
        commandManager.registerCommand(new SocialSpyCommand());
        commandManager.registerCommand(new InvseeCommand());
        commandManager.registerCommand(new AFKCommand());
        commandManager.registerCommand(new CreatifCommand());
        commandManager.registerCommand(new AdventureCommand());
        commandManager.registerCommand(new SurvivalCommand());
        commandManager.registerCommand(new SpectatorCommand());
        commandManager.registerCommand(new PTimeCommand());

        //
        this.managers = new ArrayList<>(Arrays.asList(
                new PlayerManager(this)
        ));
        afkManager = new AFKManager();
        Bukkit.getPluginManager().registerEvents(new BlockEventListener(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerEventListener(),this);
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new AFKCheck(afkManager), 0L, 600L);
        this.managers.forEach(AbstractManager::onEnable);
        injectPlayer();
    }

    @Override
    public void onDisable() {
        getLogger().log(Level.CONFIG,"test");
        this.managers.forEach(AbstractManager::onDisable);
    }

    public static Plugin getInstance() {
        return instance;
    }

    public static CommandManager getCommandManager() {
        return commandManager;
    }

    public static void injectPlayer(){
        Bukkit.getOnlinePlayers().forEach(player -> {
            Core.getAfkManager().playerJoined(player);
            if(!PlayerManager.getPlayerMap().containsKey(player.getUniqueId())){
                PlayerManager.getPlayerMap().put(player.getUniqueId(),new UserData(player.getUniqueId(),true,false));
            }
        });
    }

    public static AFKManager getAfkManager() {
        return afkManager;
    }
}
