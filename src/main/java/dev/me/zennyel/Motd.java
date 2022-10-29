package dev.me.zennyel;

import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Motd extends JavaPlugin implements Listener {

    public Motd instance;
    Configuration config;

    @EventHandler
    public void serverList(ServerListPingEvent e){

        config = instance.getConfig();
        String firstLine = config.getString("Motd.tag");
        firstLine.replace("&", "§");
        String secondLine = config.getString("Motd.desc");
        secondLine.replace("&", "§");

        e.setMotd(firstLine + "\n" + secondLine);
        e.setMaxPlayers(config.getInt("Motd.players"));
    }

    @Override
    public void onEnable() {
        instance = this;
        loadConfiguration();
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    public void loadConfiguration(){
        getConfig().options().copyDefaults(false);
        saveDefaultConfig();
    }

}
