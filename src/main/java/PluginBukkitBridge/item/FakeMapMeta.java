package PluginBukkitBridge.item;

import lombok.SneakyThrows;
import org.bukkit.inventory.meta.MapMeta;
import org.jnbt.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;

/**
 * Created by florian on 29.10.14.
 */
public class FakeMapMeta extends FakeItemMeta implements MapMeta{
    public FakeMapMeta(CompoundTag tag) {
        super(tag);
    }

    boolean hasScaling() {
        return tag != null && tag.getValue().containsKey("map_is_scaling");
    }

    @Override
    public boolean isScaling() {
        if(!hasScaling())return false;
        return ((ByteTag)tag.getValue().get("map_is_scaling")).getValue() != 0;
    }

    @Override
    public void setScaling(boolean b) {
        if(tag == null){
            tag = new CompoundTag("tag", new HashMap<String, Tag>());
        }
        tag.getValue().put("map_is_scaling", new ByteTag("map_is_scaling", (byte) (b ? 1 : 0)));
    }

    @Override
    @SneakyThrows
    public FakeMapMeta clone() {
        if(tag == null)return new FakeMapMeta(null);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        NBTOutputStream out = new NBTOutputStream(os);
        out.writeTag(tag);
        out.close();
        byte[] bytes = os.toByteArray();
        NBTInputStream in = new NBTInputStream(new ByteArrayInputStream(bytes));
        return new FakeMapMeta((CompoundTag) in.readTag());
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty() && isMapEmpty();
    }

    boolean isMapEmpty() {
        return !hasScaling();
    }

    @Override
    boolean equalsCommon(FakeItemMeta meta) {
        if (!super.equalsCommon(meta)) {
            return false;
        }
        if (meta instanceof FakeMapMeta) {
            FakeMapMeta that = (FakeMapMeta) meta;

            return (this.isScaling() == that.isScaling());
        }
        return true;
    }

    @Override
    boolean notUncommon(FakeItemMeta meta) {
        return super.notUncommon(meta) && (meta instanceof FakeMapMeta || isMapEmpty());
    }
}
