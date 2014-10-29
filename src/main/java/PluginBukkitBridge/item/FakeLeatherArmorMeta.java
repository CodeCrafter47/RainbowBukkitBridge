package PluginBukkitBridge.item;

import lombok.SneakyThrows;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.jnbt.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;

/**
 * Created by florian on 29.10.14.
 */
public class FakeLeatherArmorMeta extends FakeItemMeta implements LeatherArmorMeta{
    public FakeLeatherArmorMeta(CompoundTag tag) {
        super(tag);
    }

    boolean hasColor(){
        return tag != null && tag.getValue().containsKey("display") && ((CompoundTag)tag.getValue().get("display")).getValue().containsKey("color");
    }

    @Override
    public Color getColor() {
        if(!hasColor())return FakeItemFactory.DEFAULT_LEATHER_COLOR;
        return Color.fromRGB(((IntTag)((CompoundTag)tag.getValue().get("display")).getValue().get("color")).getValue());
    }

    @Override
    public void setColor(Color color) {
        if(tag == null){
            tag = new CompoundTag("tag", new HashMap<String, Tag>());
        }
        if(!tag.getValue().containsKey("display")){
            tag.getValue().put("display", new CompoundTag("display", new HashMap<String, Tag>()));
        }
        CompoundTag display = (CompoundTag) tag.getValue().get("display");
        display.getValue().put("color", new IntTag("color", color.asRGB()));
    }

    @Override
    @SneakyThrows
    public FakeLeatherArmorMeta clone() {
        if(tag == null)return new FakeLeatherArmorMeta(null);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        NBTOutputStream out = new NBTOutputStream(os);
        out.writeTag(tag);
        out.close();
        byte[] bytes = os.toByteArray();
        NBTInputStream in = new NBTInputStream(new ByteArrayInputStream(bytes));
        return new FakeLeatherArmorMeta((CompoundTag) in.readTag());
    }

    @Override
    public boolean applicableTo(Material type) {
        switch(type) {
            case LEATHER_HELMET:
            case LEATHER_CHESTPLATE:
            case LEATHER_LEGGINGS:
            case LEATHER_BOOTS:
                return true;
            default:
                return false;
        }
    }

    @Override
    boolean equalsCommon(FakeItemMeta meta) {
        if (!super.equalsCommon(meta)) {
            return false;
        }
        if (meta instanceof FakeLeatherArmorMeta) {
            FakeLeatherArmorMeta that = (FakeLeatherArmorMeta) meta;

            return getColor().equals(that.getColor());
        }
        return true;
    }

    @Override
    boolean notUncommon(FakeItemMeta meta) {
        return super.notUncommon(meta) && (meta instanceof FakeLeatherArmorMeta || !hasColor());
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty() && isLeatherArmorEmpty();
    }

    boolean isLeatherArmorEmpty() {
        return !(hasColor());
    }
}
