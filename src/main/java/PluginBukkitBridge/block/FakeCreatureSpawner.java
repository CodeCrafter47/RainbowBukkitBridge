package PluginBukkitBridge.block;

import PluginBukkitBridge.MyPlugin;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;

/**
 * Created by florian on 14.10.14.
 */
public class FakeCreatureSpawner extends FakeBlockState implements CreatureSpawner {
    public FakeCreatureSpawner(FakeBlock arg) {
        super(arg);
    }

    @Override
    public EntityType getSpawnedType() {
        MyPlugin.fixme();
        return EntityType.BAT;
    }

    @Override
    public void setSpawnedType(EntityType entityType) {
        MyPlugin.fixme();
    }

    @Override
    public void setCreatureTypeByName(String s) {
        MyPlugin.fixme();
    }

    @Override
    public String getCreatureTypeName() {
        MyPlugin.fixme();
        return "";
    }

    @Override
    public int getDelay() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public void setDelay(int i) {
        MyPlugin.fixme();
    }
}
