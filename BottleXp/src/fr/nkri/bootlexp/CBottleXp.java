package fr.nkri.bootlexp;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CBottleXp implements CommandExecutor {

	private MBottleXp main;
	public CBottleXp(MBottleXp main) {
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
	if(sender instanceof Player) {
		
		Player p = (Player) sender;
		
		if(label.equalsIgnoreCase("bootlexp")) {
			
			if(p.hasPermission("bootlexp.use")) {
				
				if(p.getLevel() < main.getConfig().getInt("xp")) {
					p.sendMessage("§cVotre niveau doit être suppérieur à §4" + main.getConfig().getInt("xp"));
					return true;
				}
				
				int amount = main.getConfig().getInt("xp");
				if(args.length > 0) {
					
					try {
						amount = Integer.parseInt(args[0]);
						if(amount > p.getLevel()) {
							p.sendMessage("§cVous n'avez pas assez de niveaux !");
							return true;
						}
						if(amount < main.getConfig().getInt("xp")) {
							p.sendMessage("§4" +amount + " §cn'est pas assez grand !");
							return true;
						}
					}
					catch(NumberFormatException e) {
						p.sendMessage("§4" + args[0] + " §cn'est pas un nombre !");
						return true;
					}
					
				}
				else {
					amount = p.getLevel();
				}
				
				ItemStack itm = new ItemStack(Material.EXP_BOTTLE);
				ItemMeta itmM = itm.getItemMeta();
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("§8• §aNiveaux §8•");
				lore.add(String.valueOf(String.valueOf(String.valueOf(String.valueOf(ChatColor.RED)))) + amount);
				itmM.setLore(lore);
				itmM.setDisplayName("§8• §aNiveaux §8•");
				itm.setItemMeta(itmM);
				
				p.setLevel(p.getLevel() - amount);
				p.getInventory().addItem(new ItemStack[] {itm});
				p.sendMessage("§6Vous venez de récuperer vos niveaux.");
				
			}else {
				p.sendMessage("§cVous n'avez pas la permission !");
			}
			
		}
		
	}
		
		return false;
	}

}
