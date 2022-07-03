package fr.nkri.bootlexp;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EBottleXp implements Listener {
	
	private MBottleXp main;
	public EBottleXp(MBottleXp main) {
		this.main = main;
	}
	
	@EventHandler
	public void onSplash(PlayerInteractEvent e) {
		
		if((e.getAction() == Action.RIGHT_CLICK_AIR
				|| e.getAction() == Action.RIGHT_CLICK_BLOCK
				|| e.getPlayer().getItemInHand().getType() == Material.EXP_BOTTLE)) {
			
			Player p = e.getPlayer();
			
			if(p.getItemInHand().hasItemMeta()) {
				
				ItemMeta itmM = p.getItemInHand().getItemMeta();
				
				if(itmM.getLore().get(0) != null
						&& itmM.getLore().get(1) != null) {
					
					String s = itmM.getLore().get(0);
					
					if(s.equals("§8• §aNiveaux §8•")) {
						e.setCancelled(true);
						
						int xp = Integer.parseInt(ChatColor.stripColor(itmM.getLore().get(1)));
						
						if(p.getItemInHand().getAmount() > 1) {
							p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
						}
						else {
							p.getInventory().removeItem(new ItemStack[] {p.getItemInHand()});
						}
						
						p.giveExpLevels(xp);
						p.sendMessage("§6" + xp + "§e niveau(x) ont été rajuoté(s)");
						p.updateInventory();
						
					}
					
				}
				
			}
			
		}
		
	}

}
