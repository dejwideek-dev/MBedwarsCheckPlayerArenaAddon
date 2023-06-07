package pl.dejwideek.cpaaddon.configs;

import net.elytrium.java.commons.config.YamlConfig;

@SuppressWarnings("ALL")
public class Config extends YamlConfig {

    @Ignore public static Config IMP = new Config();

    @Comment("Check Player Arena Addon for MBedwars by dejwideek")

    @Create public PERMISSIONS PERMISSIONS;

    @Comment("Permissions")
    public static class PERMISSIONS {
        public String CHECK = "bw.checkplayerarena.check";
        public String RELOAD = "bw.checkplayerarena.reload";
    }

    @Create public MESSAGES MESSAGES;

    @Comment({"Messages", "RGB colors are supported. (1.16+ only)"})
    public static class MESSAGES {
        public String USAGE = "&cUsage: /checkarena <player>";
        public String NOT_FOUND = "&cThis player isn't online!";
        public String NO_INSIDE_ARENA = "&cThis player isn't playing!";

        @Comment("Available placeholder: %permission%")
        public String NO_PERMISSION = "&cYou must have permission &4%permission% &cto do this!";

        @Comment("Available placeholders: %player%, %arena%")
        public String ARENA_INFO = "&6%player% &eis playing on &c%arena%";

        public String RELOADED = "&eSuccessfully reloaded configuration file!";
    }
}
