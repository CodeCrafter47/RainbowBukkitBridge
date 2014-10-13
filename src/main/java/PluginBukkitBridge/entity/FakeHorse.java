package PluginBukkitBridge.entity;

import PluginBukkitBridge.inventory.FakeHorseInventory;
import PluginBukkitBridge.MyPlugin;
import PluginBukkitBridge.Util;
import PluginReference.MC_Horse;
import org.bukkit.Bukkit;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.Horse;
import org.bukkit.inventory.HorseInventory;

/**
 * Created by florian on 12.10.14.
 */
public class FakeHorse extends FakeAnimal implements Horse{
    MC_Horse horse;

    public FakeHorse(MC_Horse ageable) {
        super(ageable);
        horse = ageable;
    }

    @Override
    public Variant getVariant() {
        return Util.wrapHorseType(horse.getHorseType());
    }

    @Override
    public void setVariant(Variant variant) {
        horse.setHorseType(Util.wrapHorseType(variant));
    }

    @Override
    public Color getColor() {
        MyPlugin.fixme("stub method");
        return Color.BROWN;
    }

    @Override
    public void setColor(Color color) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public Style getStyle() {
        return Util.wrapHorseVariant(horse.getHorseVariant());
    }

    @Override
    public void setStyle(Style style) {
        horse.setHorseVariant(Util.wrapHorseVariant(style));
    }

    @Override
    public boolean isCarryingChest() {
        return horse.hasChest();
    }

    @Override
    public void setCarryingChest(boolean b) {
        horse.setHasChest(b);
    }

    @Override
    public int getDomestication() {
        return horse.getTemper();
    }

    @Override
    public void setDomestication(int i) {
        horse.setTemper(i);
    }

    @Override
    public int getMaxDomestication() {
        MyPlugin.fixme("stub method");
        return 0;
    }

    @Override
    public void setMaxDomestication(int i) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public double getJumpStrength() {
        MyPlugin.fixme("stub method");
        return 0;
    }

    @Override
    public void setJumpStrength(double v) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public HorseInventory getInventory() {
        return new FakeHorseInventory(horse);
    }

    @Override
    public boolean isTamed() {
        return horse.isTame();
    }

    @Override
    public void setTamed(boolean b) {
        horse.setTamed(b);
    }

    @Override
    public AnimalTamer getOwner() {
        return Bukkit.getPlayer(horse.getOwnerUUID());
    }

    @Override
    public void setOwner(AnimalTamer animalTamer) {
        horse.setOwner(((FakePlayer)animalTamer).player);
    }
}
