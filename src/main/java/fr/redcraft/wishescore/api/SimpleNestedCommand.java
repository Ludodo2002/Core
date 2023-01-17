package fr.redcraft.wishescore.api;

import java.util.LinkedHashMap;

public class SimpleNestedCommand {

    final AbstractCommand parent;
    final LinkedHashMap<String, AbstractCommand> children = new LinkedHashMap<>();

    protected SimpleNestedCommand(AbstractCommand parent) {
        this.parent = parent;
    }

    public SimpleNestedCommand addSubCommand(AbstractCommand command) {
        command.getCommands().forEach(cmd -> children.put(cmd.toLowerCase(), command));
        return this;
    }

    public AbstractCommand getParent() {
        return parent;
    }
}