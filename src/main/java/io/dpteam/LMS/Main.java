package io.dpteam.LMS;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public int n = 10;

	public Main() {
		super();
	}

	public void onEnable() {
		Bukkit.getServer().getLogger().info("[DPT.MC] LMS Eventer Enabled");
	}

	public void onDisable() {
		Bukkit.getServer().getLogger().info("[DPT.MC] LMS Eventer Disabled");
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("event")) {
		}

		if (!sender.hasPermission("lms.event")) {
			sender.sendMessage("You can't start an event.");
			return true;
		} else {
			this.getServer().getScheduler().scheduleAsyncRepeatingTask(this, new Runnable() {
				public void run() {
					if (Main.this.n != -1) {
						if (Main.this.n != 0) {
							Bukkit.broadcastMessage("§bFFA§7> §cEvent starts in " + Main.this.n + " seconds.");
							--Main.this.n;
						} else {
							Player[] var4;
							int var3 = (var4 = (Player[])Bukkit.getOnlinePlayers().toArray(new Player[0])).length;

							for(int var2 = 0; var2 < var3; ++var2) {
								Player p = var4[var2];
								World w = p.getWorld();
								// Change this location:
								Location l = new Location(w, 0.0D, 0.0D, 0.0D);
								p.teleport(l);
								Bukkit.broadcastMessage("§bFFA§7> §cEvent has started!");
								--Main.this.n;
							}
						}
					}
				}
			}, 0L, 20L);
			return true;
		}
	}
}
