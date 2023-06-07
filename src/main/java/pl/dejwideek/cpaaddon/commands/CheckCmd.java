package pl.dejwideek.cpaaddon.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Description;
import de.marcely.bedwars.api.BedwarsAPI;
import de.marcely.bedwars.api.remote.RemoteAPI;
import de.marcely.bedwars.api.remote.RemoteArena;
import de.marcely.bedwars.api.remote.RemotePlayer;
import org.bukkit.entity.Player;
import pl.dejwideek.cpaaddon.CPAPlugin;
import pl.dejwideek.cpaaddon.color.ColorAPI;
import pl.dejwideek.cpaaddon.configs.Config;

@SuppressWarnings("ALL")
public class CheckCmd extends BaseCommand {

    private static CPAPlugin plugin;

    public CheckCmd(CPAPlugin plugin) {
        this.plugin = plugin;
    }

    @CommandAlias("checkplayerarena|checkarenaplayer|checkarena|checkplayer|check")
    @CommandCompletion("@players")
    @Description("Check player arena")
    public void check(Player player, String[] strings) {
        BedwarsAPI.onReady(() -> {
            Config config = plugin.config;
            ColorAPI colorApi = new ColorAPI();

            String permission = config.PERMISSIONS.CHECK;
            String usageMsg = config.MESSAGES.USAGE;
            String arenaInfoMsg = config.MESSAGES.ARENA_INFO;
            String noInsideMsg = config.MESSAGES.NO_INSIDE_ARENA;
            String notFoundMsg = config.MESSAGES.NOT_FOUND;
            String noPermsMsg = config.MESSAGES.NO_PERMISSION;

            if(player.hasPermission(permission)) {
                if(strings.length == 0) {
                    player.sendMessage(colorApi.process(usageMsg));
                }
                if(strings.length >= 1) {
                    RemotePlayer target = RemoteAPI.get().getOnlinePlayer(strings[0]);

                    if(target != null) {
                        RemoteArena a = RemoteAPI.get().getArenaByPlayingPlayer(target);

                        if(a != null) {
                            player.sendMessage(colorApi.process(arenaInfoMsg
                                    .replaceAll("%player%", target.getName())
                                    .replaceAll("%arena%", a.getName())));
                        }
                        if(a == null) {
                            player.sendMessage(colorApi.process(noInsideMsg));
                        }
                    }
                    if(target == null) {
                        player.sendMessage(colorApi.process(notFoundMsg));
                    }
                    return;
                }
            }
            if(!player.hasPermission(permission)) {
                player.sendMessage(colorApi.process(noPermsMsg
                        .replaceAll("%permission%", permission)));
                return;
            }
        });
        return;
    }
}
