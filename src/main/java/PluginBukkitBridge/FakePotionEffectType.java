package PluginBukkitBridge;

import org.bukkit.potion.PotionEffectType;

/**
 * Created by florian on 29.10.14.
 */
public class FakePotionEffectType extends PotionEffectType{

    String name;
    boolean instant;

    public FakePotionEffectType(int id, String name, boolean instant) {
        super(id);
        this.name = name;
        this.instant = instant;
    }

    @Override
    public double getDurationModifier() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isInstant() {
        return instant;
    }
}
