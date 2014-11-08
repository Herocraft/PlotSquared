////////////////////////////////////////////////////////////////////////////////////////////////////
// PlotSquared - A plot manager and world generator for the Bukkit API                             /
// Copyright (c) 2014 IntellectualSites/IntellectualCrafters                                       /
//                                                                                                 /
// This program is free software; you can redistribute it and/or modify                            /
// it under the terms of the GNU General Public License as published by                            /
// the Free Software Foundation; either version 3 of the License, or                               /
// (at your option) any later version.                                                             /
//                                                                                                 /
// This program is distributed in the hope that it will be useful,                                 /
// but WITHOUT ANY WARRANTY; without even the implied warranty of                                  /
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the                                   /
// GNU General Public License for more details.                                                    /
//                                                                                                 /
// You should have received a copy of the GNU General Public License                               /
// along with this program; if not, write to the Free Software Foundation,                         /
// Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA                               /
//                                                                                                 /
// You can contact us via: support@intellectualsites.com                                           /
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.intellectualcrafters.plot.commands;

import com.intellectualcrafters.plot.C;
import com.intellectualcrafters.plot.PlayerFunctions;
import com.intellectualcrafters.plot.Plot;
import com.intellectualcrafters.plot.listeners.PlotPlusListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class MusicSubcommand extends SubCommand {
    public MusicSubcommand() {
        super("music", "plots.music", "Play music in plot", "music", "mus", CommandCategory.ACTIONS, true);
    }

    @Override
    public boolean execute(final Player player, final String... args) {
        if (!PlayerFunctions.isInPlot(player)) {
            sendMessage(player, C.NOT_IN_PLOT);
            return true;
        }
        final Plot plot = PlayerFunctions.getCurrentPlot(player);
        if (!plot.hasRights(player)) {
            sendMessage(player, C.NO_PLOT_PERMS);
            return true;
        }
        final org.bukkit.inventory.Inventory inventory = Bukkit.createInventory(null, 9, ChatColor.RED + "Plot Jukebox");
        for (final PlotPlusListener.RecordMeta meta : PlotPlusListener.RecordMeta.metaList) {
            final ItemStack stack = new ItemStack(meta.getMaterial());
            final ItemMeta itemMeta = stack.getItemMeta();
            itemMeta.setDisplayName(ChatColor.GOLD + meta.toString());
            itemMeta.setLore(Arrays.asList(ChatColor.GRAY + "Click to play the record"));
            stack.setItemMeta(itemMeta);
            inventory.addItem(stack);
        }
        player.openInventory(inventory);
        return true;
    }
}