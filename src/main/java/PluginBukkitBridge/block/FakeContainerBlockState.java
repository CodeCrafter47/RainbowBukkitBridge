package PluginBukkitBridge.block;

import PluginBukkitBridge.Util;
import PluginReference.MC_Container;
import org.bukkit.block.ContainerBlock;
import org.bukkit.inventory.InventoryHolder;

/**
 * Created by florian on 28.10.14.
 */
public abstract class FakeContainerBlockState extends FakeBlockState implements ContainerBlock, InventoryHolder {

    public final MC_Container container;

    public FakeContainerBlockState(FakeBlock arg) {
        super(arg);
        container = arg.world.getContainerAt(Util.getLocation(arg.getLocation()));
    }
}
