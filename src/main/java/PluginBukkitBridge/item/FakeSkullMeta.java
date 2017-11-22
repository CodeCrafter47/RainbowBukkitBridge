package PluginBukkitBridge.item;

import lombok.SneakyThrows;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.meta.SkullMeta;
import org.jnbt.*;

import PluginBukkitBridge.MyPlugin;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;


public class FakeSkullMeta extends FakeItemMeta implements SkullMeta {
    public FakeSkullMeta(CompoundTag tag) {
        super(tag);
    }

    @Override
    public String getOwner() {
        if(!hasOwner())return null;
        return ((StringTag)tag.getValue().get("SkullOwner")).getValue();
    }

    @Override
    public boolean hasOwner() {
        return tag != null && tag.getValue().containsKey("SkullOwner");
    }

    @Override
    public boolean setOwner(String s) {
        if(tag == null){
            tag = new CompoundTag("tag", new HashMap<String, Tag>());
        }
        tag.getValue().put("SkullOwner", new StringTag("SkullOwner", s));
        // fixme only true when change
        return true;
    }

    @Override
    @SneakyThrows
    public FakeSkullMeta clone() {
        if(tag == null)return new FakeSkullMeta(null);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        NBTOutputStream out = new NBTOutputStream(os);
        out.writeTag(tag);
        out.close();
        byte[] bytes = os.toByteArray();
        NBTInputStream in = new NBTInputStream(new ByteArrayInputStream(bytes));
        return new FakeSkullMeta((CompoundTag) in.readTag());
    }

    @Override
    boolean equalsCommon(FakeItemMeta meta) {
        if (!super.equalsCommon(meta)) {
            return false;
        }
        if (meta instanceof FakeSkullMeta) {
            FakeSkullMeta that = (FakeSkullMeta) meta;

            return (this.hasOwner() ? that.hasOwner() && this.getOwner().equals(that.getOwner()) : !that.hasOwner());
        }
        return true;
    }

    @Override
    boolean notUncommon(FakeItemMeta meta) {
        return super.notUncommon(meta) && (meta instanceof FakeSkullMeta || !hasOwner());
    }

    @Override
    public boolean applicableTo(Material type) {
        switch(type) {
            case SKULL_ITEM:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty() && isSkullEmpty();
    }

    boolean isSkullEmpty() {
        return !(hasOwner());
    }

    @Override
    public OfflinePlayer getOwningPlayer() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public boolean setOwningPlayer(OfflinePlayer arg0) {
        MyPlugin.fixme();
        return false;
    }
}
