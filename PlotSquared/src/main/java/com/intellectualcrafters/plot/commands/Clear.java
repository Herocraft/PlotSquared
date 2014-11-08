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
import com.intellectualcrafters.plot.PlotMain;
import org.bukkit.entity.Player;

public class Clear extends SubCommand {

    public Clear() {
        super(Command.CLEAR, "Clear a plot", "clear", CommandCategory.ACTIONS, true);

        // TODO console clear plot at location

    }

    @Override
    public boolean execute(final Player plr, final String... args) {
        if (!PlayerFunctions.isInPlot(plr)) {
            return sendMessage(plr, C.NOT_IN_PLOT);
        }
        final Plot plot = PlayerFunctions.getCurrentPlot(plr);
        if (!PlayerFunctions.getTopPlot(plr.getWorld(), plot).equals(PlayerFunctions.getBottomPlot(plr.getWorld(), plot))) {
            return sendMessage(plr, C.UNLINK_REQUIRED);
        }
        if (((plot == null) || !plot.hasOwner() || !plot.getOwner().equals(plr.getUniqueId())) && !PlotMain.hasPermission(plr, "plots.admin")) {
            return sendMessage(plr, C.NO_PLOT_PERMS);
        }
        assert plot != null;
        plot.clear(plr);
        return true;
    }
}