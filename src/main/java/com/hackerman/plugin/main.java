package com.hackerman.plugin;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.commands.CommandHandler;
import com.eu.habbo.habbohotel.users.Habbo;
import com.eu.habbo.plugin.EventHandler;
import com.eu.habbo.plugin.EventListener;
import com.eu.habbo.plugin.HabboPlugin;
import com.eu.habbo.plugin.events.emulator.EmulatorLoadedEvent;
import com.hackerman.plugin.hhcore.checkIntegrity;
import com.hackerman.plugin.hhcore.log.error;
import com.hackerman.plugin.hhcore.log.generic;
import com.hackerman.plugin.network.changeUsername;
import com.hackerman.plugin.network.exit;

import java.io.IOException;

import static com.hackerman.plugin.hhcore.disableSelf.disableSelf;
import static com.hackerman.plugin.hhcore.loadSelf.loadSelf;
import static com.hackerman.plugin.install.main.load;

public class zmain extends HabboPlugin implements EventListener {
    public static String pluginShort = "webkit";
    public static String pluginPrefix = "WEBKIT";
    public static String pluginName = "Webkit";
    public static String pluginAuthor = "Hackerman";
    public static String version = "1.0.3";
    public static int productId = 5;

    public void onEnable () throws Exception {
        Emulator.getPluginManager().registerEvents(this, this);
        System.out.println ( "[~] Detected plugin `" + pluginName + "` by " + pluginAuthor + " version " + version);
    }

    @EventHandler
    public void onEmulatorLoadedEVERYTHING ( EmulatorLoadedEvent e ) throws IOException {
        if (checkIntegrity.checkIntegrity()) {
            Emulator.getPluginManager().registerEvents(this, this);
            long startTime = System.currentTimeMillis();

            try {
                load();
                generic.logMessage("Registery -> OK");
            } catch (Exception ex) {
                generic.logInstall("Registery -> ERROR");
                error.logError("loadRegistery","Webkit > Emulator Load > Load Register","", 2, false, ex);
            }

            loadSelf();
            Emulator.getRconServer().addRCONMessage("changeusername", changeUsername.class);
            Emulator.getRconServer().addRCONMessage("exit", exit.class);
            System.out.println("[~] Loaded plugin " + pluginName + " " + version + " in " + (System.currentTimeMillis() - startTime) + "ms -> OK!");
        }
    }

    public void onDisable () throws Exception {
        disableSelf();
        System.out.println ( "[~] Disabled plugin `" + pluginName + "` by " + pluginAuthor + " version " + version + " -> OK!");
    }

    public boolean hasPermission ( Habbo habbo , String s ) {
        return false;
    }
}
