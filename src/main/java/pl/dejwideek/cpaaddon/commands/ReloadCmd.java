package pl.dejwideek.cpaaddon.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Description;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import pl.dejwideek.cpaaddon.CPAPlugin;
import pl.dejwideek.cpaaddon.color.ColorAPI;
import pl.dejwideek.cpaaddon.configs.Config;

@SuppressWarnings("ALL")
public class ReloadCmd extends BaseCommand {

    private static CPAPlugin plugin;

    public ReloadCmd(CPAPlugin plugin) {
        this.plugin = plugin;
    }

    @CommandAlias("checkplayerarenareload|checkarenareload|cpareload|cpreload")
    @Description("Reload config file")
    public void reload(CommandSender commandSender) {
        if(commandSender instanceof Player) {
            Player p = (Player) commandSender;
            Config config = plugin.config;
            ColorAPI colorApi = new ColorAPI();

            String permission = config.PERMISSIONS.RELOAD;
            String reloadedMsg = config.MESSAGES.RELOADED;
            String noPermsMsg = config.MESSAGES.NO_PERMISSION;

            if(p.hasPermission(permission)) {
                plugin.reloadConfig();
                p.sendMessage(colorApi.process(reloadedMsg));
                plugin.getLogger().info("Reloaded configuration file!");
            }
            else {
                p.sendMessage(colorApi.process(noPermsMsg
                        .replaceAll("%permission%", permission)));
            }
        }
        if(commandSender instanceof ConsoleCommandSender) {
            plugin.reloadConfig();
            plugin.getLogger().info("Reloaded configuration file!");
        }
        return;
    }
}
