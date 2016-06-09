package PluginBukkitBridge.entity;

import PluginBukkitBridge.MyPlugin;
import PluginBukkitBridge.PermissionHelper;
import PluginBukkitBridge.Util;
import PluginBukkitBridge.inventory.FakeEnderchest;
import PluginBukkitBridge.inventory.FakePlayerInventory;
import PluginReference.MC_GameMode;
import PluginReference.MC_Player;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Villager;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MainHand;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.permissions.PermissibleBase;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;

import java.util.Set;

public class FakeHumanEntity extends FakeLivingEntity implements HumanEntity {

    public MC_Player player;

    public PermissibleBase permissions;

    EntityDamageEvent lastDamageCause = null;

    public FakeHumanEntity(MC_Player argEnt) {
        super(argEnt);
        player = argEnt;
        permissions = new PermissibleBase(this);
    }

	public void refreshReference(MC_Player entity){
		super.refreshReference(entity);
		player = entity;
	}

    @Override
    public String getName() {
        return player.getName();
    }

    @Override
    public PlayerInventory getInventory() {
        return new FakePlayerInventory(player);
    }

    @Override
    public Inventory getEnderChest() {
        return new FakeEnderchest(player);
    }

    @Override
    public MainHand getMainHand() {
        MyPlugin.fixme();
        return MainHand.RIGHT;
    }

    @Override
    public boolean setWindowProperty(InventoryView.Property arg0, int arg1) {
        MyPlugin.fixme("stub method");
        return false;
    }

    @Override
    public InventoryView getOpenInventory() {
        MyPlugin.fixme("stub method");
        return null;
    }

    @Override
    public InventoryView openEnchanting(Location arg0, boolean arg1) {
        MyPlugin.fixme("stub method");
        return null;
    }

    @Override
    public InventoryView openInventory(Inventory arg0) {
        MyPlugin.fixme("stub method");
        return null;
    }

    @Override
    public void openInventory(InventoryView arg0) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public InventoryView openMerchant(Villager villager, boolean b) {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public InventoryView openWorkbench(Location arg0, boolean arg1) {
        MyPlugin.fixme("stub method");
        return null;
    }

    @Override
    public void closeInventory() {
        MyPlugin.fixme("stub method");
    }

    @Override
    public ItemStack getItemInHand() {
        ItemStack is = Util.getItemStack(player.getItemInHand());
        if(is == null)return new ItemStack(Material.AIR);
        return is;
    }

    @Override
    public void setItemInHand(ItemStack item) {
        player.setItemInHand(Util.getItemStack(item));
    }

    @Override
    public ItemStack getItemOnCursor() {
        MyPlugin.fixme("stub method");
        return null;
    }

    @Override
    public void setItemOnCursor(ItemStack arg0) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public boolean isSleeping() {
        return player.isSleeping();
    }

    @Override
    public int getSleepTicks() {
        MyPlugin.fixme("stub method");
        return 0;
    }


    @Override
    public GameMode getGameMode() {
        return GameMode.valueOf(player.getGameMode().name());
    }

    @Override
    public void setGameMode(GameMode mode) {
        player.setGameMode(MC_GameMode.valueOf(mode.name()));
    }

    @Override
    public boolean isBlocking() {
        MyPlugin.fixme("stub method");
        return false;
    }

    @Override
    public int getExpToLevel() {
        MyPlugin.fixme("stub method");
        return 0;
    }

    @Override
    public boolean isPermissionSet(String arg0) {
        // fixme that's not the same
        return hasPermission(arg0);
    }

    @Override
    public boolean isPermissionSet(Permission arg0) {
        // fixme that's not the same
        return hasPermission(arg0);
    }

    @Override
    public boolean hasPermission(String perm) {
        return PermissionHelper.hasPermission(player, perm);
    }

    @Override
    public boolean hasPermission(Permission arg0) {
        return hasPermission(arg0.getName());
    }

    @Override
    public PermissionAttachment addAttachment(Plugin arg0) {
        return permissions.addAttachment(arg0);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin arg0, int arg1) {
        return permissions.addAttachment(arg0, arg1);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin arg0, String arg1, boolean arg2) {
        return permissions.addAttachment(arg0, arg1, arg2);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin arg0, String arg1, boolean arg2, int arg3) {
        return permissions.addAttachment(arg0, arg1, arg2, arg3);
    }

    @Override
    public void recalculatePermissions() {
        permissions.recalculatePermissions();
    }

    @Override
    public void removeAttachment(PermissionAttachment arg0) {
        permissions.removeAttachment(arg0);
    }

    @Override
    public Set<PermissionAttachmentInfo> getEffectivePermissions() {
        // fixme also include rainbow permissions?
        return permissions.getEffectivePermissions();
    }

    @Override
    public boolean isOp() {
        return player.isOp();
    }

    @Override
    public void setOp(boolean arg0) {
        if(arg0)MyPlugin.server.executeCommand("op "+getName());
        else MyPlugin.server.executeCommand("deop "+getName());
    }

    @Override
    public double getLastDamage() {
        return lastDamageCause.getFinalDamage();
    }

    @Override
    public int _INVALID_getLastDamage() {
        return (int)getLastDamage();
    }

    @Override
    public void setLastDamage(double v) {
        lastDamageCause.setDamage(v);
    }

    @Override
    public void _INVALID_setLastDamage(int i) {
        setLastDamage(i);
    }

    @Override
    public EntityDamageEvent getLastDamageCause() {
        return lastDamageCause;
    }

    @Override
    public void setLastDamageCause(EntityDamageEvent arg0) {
        lastDamageCause = arg0;
    }
}
