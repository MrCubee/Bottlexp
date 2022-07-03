package fr.nkri.bootlexp;

import org.bukkit.plugin.java.JavaPlugin;

public class MBottleXp extends JavaPlugin {
	
	@Override
	public void onEnable() {
		saveDefaultConfig();
		
		getCommand("bootlexp").setExecutor(new CBottleXp(this));
		getServer().getPluginManager().registerEvents(new EBottleXp(this), this);
		
		super.onEnable();
	}

}
