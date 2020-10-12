package main;


import main.Commands.Effects;
import main.Listeners.InventoryClick;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.random;

public class Main extends JavaPlugin {

    public void onEnable() {
        load();
    }

    public void load() {
        long now = System.currentTimeMillis();


        registerCommands();
        registerListeners();

        getLogger().info("Load finished - "+(System.currentTimeMillis() - now)+"ms");
    }

    public void registerListeners() {
        new InventoryClick(this);
    }

    public void registerCommands() {
        new Effects(this);
    }

    public static Inventory gui = Bukkit.createInventory(null, 54, ChatColor.YELLOW + "Effects");

    public static void openEffectGUI(Player p) {

         //1639 <- max time
        int i = 0;
        for (PotionEffectType e : PotionEffectType.values()) {
            ItemStack item = new ItemStack(Material.POTION, (i + 1));

            ItemMeta m = item.getItemMeta();
            assert m != null;

            m.setDisplayName(ChatColor.AQUA+e.getName().replaceAll("_", " "));
            for (ItemFlag flag : m.getItemFlags()) {
                m.removeItemFlags(flag);
            }
            PotionMeta pm = (PotionMeta)m;
            pm.clearCustomEffects();
            pm.addCustomEffect(e.createEffect(0,0), false);
            item.setItemMeta(pm);
            item.setItemMeta(m);

            gui.setItem(i, item);

            i = i + 1;
        }
        ItemStack i53 = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta m53 = i53.getItemMeta();
        assert m53 != null;
        m53.setDisplayName(ChatColor.RED + "CLOSE");
        i53.setItemMeta(m53);
        gui.setItem(53, i53);

        ItemStack i52 = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        ItemMeta m52 = i52.getItemMeta();
        assert m52 != null;
        m52.setDisplayName(ChatColor.YELLOW + "Clear All Effects");
        i52.setItemMeta(m52);
        gui.setItem(52, i52);

        ItemStack i45 = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
        ItemMeta m45 = i45.getItemMeta();
        assert m45 != null;
        m45.setDisplayName(ChatColor.GREEN + "Time: " + i45.getAmount() + "m");
        ArrayList<String> m45lore = new ArrayList<String>();
        m45lore.add(" ");
        m45lore.add("Left Click for +1");
        m45lore.add("Right Click for +10");
        m45.setLore(m45lore);
        i45.setItemMeta(m45);
        gui.setItem(45, i45);

        ItemStack i46 = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta m46 = i46.getItemMeta();
        assert m46 != null;
        m46.setDisplayName(ChatColor.RED + "Reset Time");
        i46.setItemMeta(m46);
        gui.setItem(46, i46);

        ItemStack i47 = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
        ItemMeta m47 = i47.getItemMeta();
        assert m47 != null;
        m47.setDisplayName(ChatColor.GREEN + "Amplifier: " + i47.getAmount());
        ArrayList<String> m47lore = new ArrayList<String>();
        m47lore.add(" ");
        m47lore.add("Left Click for +1");
        m47lore.add("Right Click for +10");
        m47.setLore(m47lore);
        i47.setItemMeta(m47);
        gui.setItem(47, i47);

        ItemStack i48 = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta m48 = i48.getItemMeta();
        assert m48 != null;
        m48.setDisplayName(ChatColor.RED + "Reset Amplifier");
        i48.setItemMeta(m48);
        gui.setItem(48, i48);

        p.openInventory(gui);

    }

}
