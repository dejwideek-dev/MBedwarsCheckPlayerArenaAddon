package pl.dejwideek.cpaaddon;

import co.aikar.commands.PaperCommandManager;
import de.marcely.bedwars.api.BedwarsAddon;
import pl.dejwideek.cpaaddon.commands.CheckCmd;
import pl.dejwideek.cpaaddon.commands.ReloadCmd;

@SuppressWarnings("ALL")
public class CPAAddon extends BedwarsAddon {
    private static CPAPlugin plugin;

    public CPAAddon(CPAPlugin plugin) {
        super(plugin);
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return plugin.getName();
    }

    public void registerCommands() {
        PaperCommandManager manager =
                new PaperCommandManager(plugin);

        manager.registerCommand(new CheckCmd(plugin));
        manager.registerCommand(new ReloadCmd(plugin));
    }
}
