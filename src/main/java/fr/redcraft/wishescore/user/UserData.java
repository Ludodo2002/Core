package fr.redcraft.wishescore.user;

import java.util.UUID;

public class UserData {

    public UUID uuid;
    public boolean msgtoggle;
    public boolean socialspy;

    public UserData(UUID uuid,boolean msgtoggle,boolean socialspy){
        this.uuid = uuid;
        this.msgtoggle = msgtoggle;
        this.socialspy = socialspy;
    }

    public UUID getUuid() {
        return uuid;
    }

    public boolean isMsgtoggle() {
        return msgtoggle;
    }

    public boolean isSocialspy() {
        return socialspy;
    }
}