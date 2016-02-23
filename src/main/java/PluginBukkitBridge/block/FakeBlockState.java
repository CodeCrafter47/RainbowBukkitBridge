package PluginBukkitBridge.block;

import PluginBukkitBridge.MyPlugin;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.material.MaterialData;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class FakeBlockState implements BlockState {
    Location location;
    int type;
    byte meta;

    public FakeBlockState(FakeBlock arg) {
        location = arg.getLocation();
        type = arg.getTypeId();
        meta = arg.getData();
    }

    public FakeBlockState(Location location, int type, byte meta) {
        this.location = location;
        this.type = type;
        this.meta = meta;
    }

    @Override
    public List<MetadataValue> getMetadata(String arg0) {
        return getBlock().getMetadata(arg0);
    }

    @Override
    public boolean hasMetadata(String arg0) {
        return getBlock().hasMetadata(arg0);
    }

    @Override
    public void removeMetadata(String arg0, Plugin arg1) {
        getBlock().removeMetadata(arg0, arg1);
    }

    @Override
    public void setMetadata(String arg0, MetadataValue arg1) {
        getBlock().setMetadata(arg0, arg1);
    }

    @Override
    public Block getBlock() {
        return location.getBlock();
    }

    @Override
    public Chunk getChunk() {
        return getBlock().getChunk();
    }

    @Override
    public MaterialData getData() {
        return getType().getNewData(meta);
    }

    @Override
    public byte getLightLevel() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public Location getLocation() {
        return location.clone();
    }

    @Override
    public Location getLocation(Location arg0) {
        arg0.setX(location.getX());
        arg0.setY(location.getY());
        arg0.setZ(location.getZ());
        arg0.setWorld(location.getWorld());
        return arg0;
    }

    @Override
    public byte getRawData() {
        return meta;
    }

    @Override
    public Material getType() {
        return Material.getMaterial(type);
    }

    @Override
    public int getTypeId() {
        return type;
    }

    @Override
    public World getWorld() {
        return location.getWorld();
    }

    @Override
    public int getX() {
        return location.getBlockX();
    }

    @Override
    public int getY() {
        return location.getBlockY();
    }

    @Override
    public int getZ() {
        return location.getBlockZ();
    }

    @Override
    public void setData(MaterialData arg0) {
        setRawData(arg0.getData());
    }

    @Override
    public void setRawData(byte arg0) {
        meta = arg0;
    }

    @Override
    public boolean isPlaced() {
        return true;
    }

    @Override
    public void setType(Material arg0) {
        type = arg0.getId();
    }

    @Override
    public boolean setTypeId(int arg0) {
        type = arg0;
        return true;
    }

    @Override
    public boolean update() {
        return update(false, true);
    }

    @Override
    public boolean update(boolean arg0) {
        return update(arg0, true);
    }

    @Override
    public boolean update(boolean force, boolean applyPhysics) {
        if (!force && getWorld().getBlockAt(getLocation()).getType() != getType())return false;
        if (!applyPhysics)MyPlugin.fixme("ignore parameter applyPhysics");
        getBlock().setTypeId(type);
        getBlock().setData(meta);
        return true;
    }

}
