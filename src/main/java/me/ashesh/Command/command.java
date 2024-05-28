package me.ashesh.Command;

import me.ashesh.API;
import me.ashesh.MMOCoreEXP;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class command implements CommandExecutor {

    public void help(CommandSender sender) {
        sender.sendMessage(" ");
        sender.sendMessage("&7Avaible Command &f/MMOCoreEXP");
        sender.sendMessage("  &fReload &7- &fReload Plugin");
        sender.sendMessage("  &fVersion &7- &fPlugin Version");
        sender.sendMessage(" ");

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("mmocoreexp")) {
            if (args.length == 0) {
                help(sender);
            } else {
                switch (args[0].toLowerCase()) {
                    case "version":
                        sender.sendMessage("&7[&eMMOCoreEXP&7] &aVersion " + MMOCoreEXP.getInstance().getDescription().getVersion());
                        break;
                    case "reload":
                        if (sender.hasPermission("MMOCoreEXP.admin")) {
                            MMOCoreEXP.getInstance().reloadConfig();
                            sender.sendMessage(API.chatColorized("&7[&eMMOCoreEXP&7] &aPlugin reloaded!"));
                        } else {
                            sender.sendMessage(API.chatColorized("&7[&eMMOCoreEXP&7] &4You dont have permission!"));
                        }
                        break;
                    default:
                        help(sender);
                        break;
                }
            }
        }
        return true;
    }
}

