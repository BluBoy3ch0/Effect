package main.Listeners;

import main.Commands.Effects;
import main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class InventoryClick implements Listener {


        Plugin plugin;

        public InventoryClick(Main plugin) {
            this.plugin = plugin;
            plugin.getServer().getPluginManager().registerEvents(this, plugin);
        }
        @EventHandler
        public void onInventoryClick (InventoryClickEvent e){

            Player player = (Player) e.getWhoClicked();
            ItemStack item = e.getCurrentItem();
            Inventory inventory = e.getClickedInventory();

            if ( inventory == null) {return;}
            if ( item == null) {return;}
            if (inventory.equals(Main.gui)) {
                e.setCancelled(true);
                if (e.getSlot() == 53) {
                    player.closeInventory();
                    return;
                }
                if (e.getSlot() == 52) {
                    for (PotionEffect p : player.getActivePotionEffects()) {
                        player.removePotionEffect(p.getType());
                    }
                    return;
                }
                if (e.getSlot() == 45) {
                    if (e.isLeftClick()) {if ((item.getAmount()+1) > 64) {return;} else {item.setAmount(item.getAmount()+1);}}
                    if (e.isRightClick()) {if ((item.getAmount()+10) > 64) {item.setAmount(64); } else {item.setAmount(item.getAmount()+10);}}

                    ItemMeta meta = item.getItemMeta();
                    assert meta != null;
                    meta.setDisplayName(ChatColor.GREEN + "Time: " + item.getAmount() + "m");
                    item.setItemMeta(meta);
                    return;
                }

                if (e.getSlot() == 46) {
                    ItemStack minset = inventory.getItem(45);
                    assert minset != null;
                    minset.setAmount(1);
                    ItemMeta meta = minset.getItemMeta();
                    assert meta != null;
                    meta.setDisplayName(ChatColor.GREEN + "Time: " + minset.getAmount() + "m");
                    minset.setItemMeta(meta);
                    return;
                }
                if (e.getSlot() == 47) {

                    if (e.isLeftClick()) {if ((item.getAmount()+1) > 64) {return;} else {item.setAmount(item.getAmount()+1);}}
                    if (e.isRightClick()) {if ((item.getAmount()+10) > 64) {item.setAmount(64); } else {item.setAmount(item.getAmount()+10);}}

                    ItemMeta meta = item.getItemMeta();
                    assert meta != null;
                    meta.setDisplayName(ChatColor.GREEN + "Amplifier: " + item.getAmount());
                    item.setItemMeta(meta);
                    return;
                }

                if (e.getSlot() == 48) {
                    ItemStack ampset = inventory.getItem(47);
                    assert ampset != null;
                    ampset.setAmount(1);
                    ItemMeta meta = ampset.getItemMeta();
                    assert meta != null;
                    meta.setDisplayName(ChatColor.GREEN + "Amplifier: " + ampset.getAmount() );
                    ampset.setItemMeta(meta);
                    return;
                }
                ItemStack minset = inventory.getItem(45);
                if (minset == null) return;
                int duration = (minset.getAmount()*20)*60;

                ItemStack ampset = inventory.getItem(47);
                if (ampset == null) return;
                int amplifier = (ampset.getAmount()-1);

                ItemMeta name = item.getItemMeta();
                if (name == null) return;
                String potion = name.getDisplayName().replaceAll(" ", "_");

                PotionEffectType effect = PotionEffectType.getByName(ChatColor.stripColor(potion));
                if (effect == null) return;
                Bukkit.getLogger().info("["+player.getName()+"] "+effect.getName()+" Amplifier "+amplifier+ " and Time " + duration+" ticks or "+((duration/60)/20)+" minute"+( ((duration/60)/20) == 1 ? "" : "s" ));
                PotionEffect app = effect.createEffect(duration, amplifier);
                assert app != null;
                player.addPotionEffect(app);

            }

        }


}
