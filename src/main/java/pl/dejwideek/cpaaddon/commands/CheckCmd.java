package pl.dejwideek.cpaaddon.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Description;
import de.marcely.bedwars.api.BedwarsAPI;
import de.marcely.bedwars.api.remote.RemoteAPI;
import de.marcely.bedwars.api.remote.RemoteArena;
import de.marcely.bedwars.api.remote.RemotePlayer;
import dev.dejvokep.boostedyaml.YamlDocument;
import org.bukkit.entity.Player;
import pl.dejwideek.cpaaddon.CPAPlugin;
import pl.dejwideek.cpaaddon.color.ColorAPI;

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
            YamlDocument config = plugin.config;
            ColorAPI colorApi = new ColorAPI();

            String permission = plugin.config.getString("permissions.check");
            String usageMsg = plugin.config.getString("messages.usage");
            String arenaInfoMsg = plugin.config.getString("messages.arena-info");
            String noInsideMsg = plugin.config.getString("messages.no-inside-arena");
            String notFoundMsg = plugin.config.getString("messages.not-found");
            String noPermsMsg = plugin.config.getString("messages.no-permission");

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
