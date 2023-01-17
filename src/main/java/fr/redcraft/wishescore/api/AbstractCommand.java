package fr.redcraft.wishescore.api;

import org.bukkit.command.CommandSender;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class AbstractCommand {

    private CommandType cmdType;
    private boolean hasArgs;
    private List<String> handledCommands = new ArrayList<>();

    protected AbstractCommand(CommandType type, String... command) {
        this.handledCommands.addAll(Arrays.asList(command));
        this.hasArgs = false;
        this.cmdType = type;
    }

    protected AbstractCommand(CommandType type, boolean hasArgs, String... command) {
        this.handledCommands.addAll(Arrays.asList(command));
        this.hasArgs = hasArgs;
        this.cmdType = type;
    }

    @Deprecated
    protected AbstractCommand(boolean noConsole, String... command) {
        this.handledCommands.addAll(Arrays.asList(command));
        this.hasArgs = false;
        this.cmdType = noConsole ? CommandType.PLAYER_ONLY : CommandType.CONSOLE_OK;
    }

    @Deprecated
    protected AbstractCommand(boolean noConsole, boolean hasArgs, String... command) {
        this.handledCommands.addAll(Arrays.asList(command));
        this.hasArgs = hasArgs;
        this.cmdType = noConsole ? CommandType.PLAYER_ONLY : CommandType.CONSOLE_OK;
    }

    public final List<String> getCommands() {
        return Collections.unmodifiableList(handledCommands);
    }


    protected abstract ReturnType runCommand(CommandSender sender, String... args);

    protected abstract List<String> onTab(CommandSender sender, String... args);

    public abstract String getPermissionNode();

    public abstract String getSyntax();

    public abstract String getDescription();


    public boolean isNoConsole() {
        return cmdType == CommandType.PLAYER_ONLY;
    }

    public static enum ReturnType {SUCCESS, NEEDS_PLAYER, FAILURE, SYNTAX_ERROR,NEED_ARGUMENTS,ERROR_PLAYER}

    public static enum CommandType {PLAYER_ONLY, CONSOLE_OK}
}
