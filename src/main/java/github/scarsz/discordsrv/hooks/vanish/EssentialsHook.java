/*
 * DiscordSRV - A Minecraft to Discord and back link plugin
 * Copyright (C) 2016-2017 Austin Shapiro AKA Scarsz
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package github.scarsz.discordsrv.hooks.vanish;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Method;

public class EssentialsHook {

    public static boolean isVanished(Player player) {
        try {
            Plugin essentials = Bukkit.getPluginManager().getPlugin("Essentials");
            Method getUser = essentials.getClass().getMethod("getUser", String.class);
            Object essentialsPlayer = getUser.invoke(essentials, player.getName());
            if (essentialsPlayer != null) {
                Method isVanished = essentialsPlayer.getClass().getMethod("isVanished");
                return (boolean) isVanished.invoke(essentialsPlayer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
