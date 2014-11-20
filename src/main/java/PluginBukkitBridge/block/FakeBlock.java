package PluginBukkitBridge.block;

import PluginBukkitBridge.FakeWorld;
import PluginBukkitBridge.MyPlugin;
import PluginBukkitBridge.Util;
import PluginBukkitBridge.WorldManager;
import PluginReference.BlockHelper;
import PluginReference.MC_Block;
import PluginReference.MC_World;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

import java.util.Collection;
import java.util.List;

public class FakeBlock implements Block
{
    int x,y,z;
    public MC_World world;

    public FakeBlock(int x, int y, int z, MC_World world) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.world = world;
    }

    @Override
    public byte getData() {
        return (byte) world.getBlockAt(x,y,z).getSubtype();
    }

    @Override
    public Block getRelative(int modX, int modY, int modZ) {
        return getWorld().getBlockAt(getX() + modX, getY() + modY, getZ() + modZ);
    }

    @Override
    public Block getRelative(BlockFace face) {
        return getRelative(face, 1);
    }

    @Override
    public Block getRelative(BlockFace face, int distance) {
        return getRelative(face.getModX() * distance, face.getModY() * distance, face.getModZ() * distance);
    }

    @Override
    public Material getType() {
        return Material.getMaterial(getTypeId());
    }

    @Override
    public int getTypeId() {
        return world.getBlockAt(x,y,z).getId();
    }

    @Override
    public byte getLightLevel() {
        MyPlugin.fixme("stub method");
        return 0;
    }

    @Override
    public byte getLightFromSky() {
        MyPlugin.fixme("stub method");
        return 0;
    }

    @Override
    public byte getLightFromBlocks() {
        MyPlugin.fixme("stub method");
        return 0;
    }

    @Override
    public World getWorld() {
        return WorldManager.getWorld(world.getName());
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getZ() {
        return z;
    }

    @Override
    public Location getLocation() {
        return new Location(getWorld(), x, y, z);
    }

    @Override
    public Location getLocation(Location loc) {
        loc.setWorld(getWorld());
        loc.setX(getX());
        loc.setY(getY());
        loc.setZ(getZ());
        return loc;
    }

    @Override
    public Chunk getChunk() {
        return getWorld().getChunkAt(this);
    }

    @Override
    public void setData(final byte data) {
        world.setBlockAt(x,y,z, world.getBlockAt(x,y,z), data);
    }

    @Override
    public void setData(byte data, boolean applyPhysics) {
        // MyPlugin.fixme();
        setData(data);
    }

    @Override
    public void setType(final Material type) {
        setTypeId(type.getId());
    }

    @Override
    public boolean setTypeId(int type) {
        world.setBlockAt(x,y,z,getBlock(type), getData());
        return true;
    }

    @Override
    public boolean setTypeId(int type, boolean applyPhysics) {
        // MyPlugin.fixme();
        return setTypeId(type);
    }

    @Override
    public boolean setTypeIdAndData(final int type, final byte data, boolean applyPhysics) {
        // MyPlugin.fixme();
        world.setBlockAt(x,y,z,getBlock(type), data);
        return true;
    }

    @Override
    public BlockFace getFace(Block block) {
        BlockFace[] values = BlockFace.values();

        for (BlockFace face : values) {
            if ((this.getX() + face.getModX() == block.getX()) &&
                    (this.getY() + face.getModY() == block.getY()) &&
                    (this.getZ() + face.getModZ() == block.getZ())
                    ) {
                return face;
            }
        }

        return null;
    }

    @Override
    public BlockState getState() {
        return Util.wrapBlockState(this);
    }

    @Override
    public Biome getBiome() {
        return Util.wrapBiome(world.getBiomeTypeAt(x,z));
    }

    @Override
    public void setBiome(Biome bio) {
        world.setBiomeTypeAt(x,z,Util.wrapBiome(bio));
    }

    @Override
    public boolean isBlockPowered() {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean isBlockIndirectlyPowered() {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean isBlockFacePowered(BlockFace face) {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean isBlockFaceIndirectlyPowered(BlockFace face) {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public int getBlockPower(BlockFace face) {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public int getBlockPower() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return getTypeId() == 0;
    }

    @Override
    public boolean isLiquid() {
        return world.getBlockAt(x,y,z).isLiquid();
    }

    @Override
    public double getTemperature() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public double getHumidity() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public PistonMoveReaction getPistonMoveReaction() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public boolean breakNaturally() {
        world.breakNaturallyAt(x,y,z);
        return true;
    }

    @Override
    public boolean breakNaturally(ItemStack tool) {
        world.breakNaturallyAt(x,y,z, Util.getItemStack(tool));
        return true;
    }

    @Override
    public Collection<ItemStack> getDrops() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public Collection<ItemStack> getDrops(ItemStack tool) {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public void setMetadata(String metadataKey, MetadataValue newMetadataValue) {
        ((FakeWorld)getWorld()).blockMetadataStore.setMetadata(this, metadataKey, newMetadataValue);
    }

    @Override
    public List<MetadataValue> getMetadata(String metadataKey) {
        return ((FakeWorld)getWorld()).blockMetadataStore.getMetadata(this, metadataKey);
    }

    @Override
    public boolean hasMetadata(String metadataKey) {
        return ((FakeWorld)getWorld()).blockMetadataStore.hasMetadata(this, metadataKey);
    }

    @Override
    public void removeMetadata(String metadataKey, Plugin owningPlugin) {
        ((FakeWorld)getWorld()).blockMetadataStore.removeMetadata(this, metadataKey, owningPlugin);
    }

    private MC_Block getBlock(int id){
        MC_Block block = world.getBlockFromName(BlockHelper.getBlockName(id));
        if(block == null){
            MyPlugin.logger.warning("Unknown block: " + Material.getMaterial(id).name() + ", using dirt");
            block = world.getBlockFromName("dirt");
        }
        return block;
    }

}
