package com.kaloudasdev.cooldown;

import org.bukkit.plugin.java.JavaPlugin;

public final class CooldownAPI {
    private static CooldownManager manager;
    
    public static void init(JavaPlugin plugin) {
        manager = CooldownManager.getInstance();
        CooldownManager.init(plugin);
    }
    
    public static CooldownManager getManager() {
        if (manager == null) {
            throw new IllegalStateException("CooldownAPI not initialized");
        }
        return manager;
    }
    
    public static boolean hasCooldown(String key) {
        return getManager().hasCooldown(key);
    }
    
    public static long getRemaining(String key) {
        return getManager().getRemaining(key);
    }
    
    public static void setCooldown(String key, long seconds) {
        getManager().setCooldown(key, seconds);
    }
    
    public static void removeCooldown(String key) {
        getManager().removeCooldown(key);
    }
}
