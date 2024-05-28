package me.ashesh;

import me.ashesh.Event.MMDeath;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class MMOCoreEXP extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("[MMOCoreEXP] Trying to enable plugin");

        this.getServer().getPluginManager().registerEvents(new MMDeath(), this);
        if (this.getServer().getPluginManager().getPlugin("MMOCore") == null || this.getServer().getPluginManager().getPlugin("MythicLib") == null) {
            Bukkit.getLogger().severe("[MMOCoreEXP] MMOCore or MythicLib not installed!. disabled plugin");
            getServer().getPluginManager().disablePlugin(this);
        }

        if (this.getServer().getPluginManager().getPlugin("Vault") == null) {
            Bukkit.getLogger().severe("[MMOCoreEXP] Disabling MMOCoreExp Money drop module");
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
