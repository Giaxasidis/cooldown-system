package com.kaloudasdev.cooldown;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import org.bukkit.plugin.java.JavaPlugin;

public final class CooldownManager {
    private static CooldownManager instance;
    private final Map<String, CooldownEntry> cooldowns;
    private JavaPlugin plugin;
    
    private CooldownManager() {
        this.cooldowns = new ConcurrentHashMap<>();
    }
    
    public static synchronized CooldownManager getInstance() {
        if (instance == null) {
            instance = new CooldownManager();
        }
        return instance;
    }
    
    public static void init(JavaPlugin plugin) {
        getInstance().plugin = plugin;
    }
    
    public void setCooldown(String key, long seconds) {
        long expiry = System.currentTimeMillis() + (seconds * 1000);
        cooldowns.put(key, new CooldownEntry(key, expiry));
    }
    
    public boolean hasCooldown(String key) {
        CooldownEntry entry = cooldowns.get(key);
        if (entry == null) return false;
        if (entry.isExpired()) {
            cooldowns.remove(key);
            return false;
        }
        return true;
    }
    
    public long getRemaining(String key) {
        CooldownEntry entry = cooldowns.get(key);
        if (entry == null || entry.isExpired()) return 0;
        return (entry.getExpiry() - System.currentTimeMillis()) / 1000;
    }
    
    public void removeCooldown(String key) {
        cooldowns.remove(key);
    }
    
    public void clearAll() {
        cooldowns.clear();
    }
    
    private static class CooldownEntry {
        private final String key;
        private final long expiry;
        
        public CooldownEntry(String key, long expiry) {
            this.key = key;
            this.expiry = expiry;
        }
        
        public long getExpiry() { return expiry; }
        public boolean isExpired() { return System.currentTimeMillis() >= expiry; }
    }
}
