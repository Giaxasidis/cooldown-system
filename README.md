# Cooldown System for Spigot/Paper

[![Minecraft API](https://img.shields.io/badge/Minecraft-1.16.5--1.21-5f9ea0?logo=spigotmc&logoColor=white)]()
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)]()
[![Java](https://img.shields.io/badge/Java-17-339933?logo=java&logoColor=white)]()
[![Maven](https://img.shields.io/badge/Maven-3.9%2B-C71A36?logo=apachemaven&logoColor=white)]()

Lightweight cooldown management library for Minecraft Spigot/Paper plugins. Provides annotation-based and programmatic cooldown systems with automatic cleanup.

## Features

- **Annotation-Based Cooldowns** - Add @Cooldown to any method
- **Programmatic API** - Flexible cooldown management at runtime
- **Multiple Key Types** - Player UUID, String, or custom keys
- **Automatic Cleanup** - Expired cooldowns removed automatically
- **Event Integration** - Cancel events based on cooldown status
- **Zero Dependencies** - Uses only native Spigot/Paper API

## Installation

```xml
<dependency>
    <groupId>com.kaloudasdev</groupId>
    <artifactId>cooldown-system</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Usage Examples

### Annotation-Based

```java
@Cooldown(duration = 30, unit = TimeUnit.SECONDS)
@Command(name = "heal")
public void onHeal(Player player) {
    player.setHealth(20);
    player.sendMessage("You have been healed");
}
```

### Programmatic

```java
CooldownManager cooldown = CooldownManager.getInstance();

if (cooldown.hasCooldown(key)) {
    long remaining = cooldown.getRemaining(key);
    player.sendMessage("Wait " + remaining + " seconds");
    return;
}

cooldown.setCooldown(key, 30);
```

## API Reference

| Method | Description |
|--------|-------------|
| `hasCooldown(String key)` | Check if cooldown exists |
| `getRemaining(String key)` | Get remaining seconds |
| `setCooldown(String key, long seconds)` | Apply cooldown |
| `removeCooldown(String key)` | Remove cooldown |
| `clearAll()` | Clear all cooldowns |

## License

MIT © KaloudasDev
