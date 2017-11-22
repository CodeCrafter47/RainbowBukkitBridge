package PluginBukkitBridge;

import org.apache.logging.log4j.LogManager;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.chat.BaseComponent;

import java.util.Set;

public class FakeConsoleCommandSender implements ConsoleCommandSender {
    @Override
    public String getName() {
        return "BukkitBridge";
    }

    @Override
    public Server getServer() {
        return MyPlugin.fakeServer;
    }

    @Override
    public void sendMessage(String arg0) {
        LogManager.getLogger("BukkitBridge").info(arg0);
    }

    @Override
    public void sendMessage(String[] arg0) {
        for (String piece : arg0) sendMessage(piece);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin arg0) {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public PermissionAttachment addAttachment(Plugin arg0, int arg1) {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public PermissionAttachment addAttachment(Plugin arg0, String arg1, boolean arg2) {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public PermissionAttachment addAttachment(Plugin arg0, String arg1, boolean arg2, int arg3) {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public Set<PermissionAttachmentInfo> getEffectivePermissions() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public boolean hasPermission(String arg0) {
        return true;
    }

    @Override
    public boolean hasPermission(Permission arg0) {
        return true;
    }

    @Override
    public boolean isPermissionSet(String arg0) {
        return true;
    }

    @Override
    public boolean isPermissionSet(Permission arg0) {
        return true;
    }

    @Override
    public void recalculatePermissions() {
        MyPlugin.fixme();
    }

    @Override
    public void removeAttachment(PermissionAttachment arg0) {
        MyPlugin.fixme();
    }

    @Override
    public boolean isOp() {
        return true;
    }

    @Override
    public void setOp(boolean arg0) {
        MyPlugin.fixme();
    }

    @Override
    public void abandonConversation(Conversation arg0) {
        MyPlugin.fixme();
    }

    @Override
    public void abandonConversation(Conversation arg0, ConversationAbandonedEvent arg1) {
        MyPlugin.fixme();
    }

    @Override
    public void acceptConversationInput(String arg0) {
        MyPlugin.fixme();
    }

    @Override
    public boolean beginConversation(Conversation arg0) {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean isConversing() {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public void sendRawMessage(String arg0) {
        MyPlugin.fixme();
    }

    @Override
    public Spigot spigot() {
        return new Spigot() {
            @Override
            public void sendMessage(BaseComponent component) {
                MyPlugin.fixme();
            }
            
            @Override
            public void sendMessage(BaseComponent... components) {
                MyPlugin.fixme();
            }
        };
    }
}
