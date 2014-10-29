package PluginBukkitBridge.item;

import lombok.SneakyThrows;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.jnbt.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by florian on 29.10.14.
 */
public class FakeEnchantedBookMeta extends FakeItemMeta implements EnchantmentStorageMeta {
    public FakeEnchantedBookMeta(CompoundTag tag) {
        super(tag);
    }

    @Override
    public boolean hasStoredEnchants() {
        if(tag == null)return false;
        if(!tag.getValue().containsKey("StoredEnchantments"))return false;
        ListTag list = (ListTag) tag.getValue().get("StoredEnchantments");
        return !list.getValue().isEmpty();
    }

    @Override
    public boolean hasStoredEnchant(Enchantment enchantment) {
        return 0 != getStoredEnchantLevel(enchantment);
    }

    @Override
    public int getStoredEnchantLevel(Enchantment enchantment) {
        if(tag == null)return 0;
        if(!tag.getValue().containsKey("StoredEnchantments"))return 0;
        ListTag list = (ListTag) tag.getValue().get("StoredEnchantments");
        for(Tag tag: list.getValue()){
            CompoundTag compoundTag = (CompoundTag) tag;
            if(compoundTag.getValue().containsKey("id") && compoundTag.getValue().containsKey("lvl")){
                if(((ShortTag) compoundTag.getValue().get("id")).getValue() == enchantment.getId()){
                    return ((ShortTag) compoundTag.getValue().get("lvl")).getValue();
                }
            }
        }
        return 0;
    }

    @Override
    public Map<Enchantment, Integer> getStoredEnchants() {
        if(tag == null)return new HashMap<>();
        Map<Enchantment, Integer> map = new HashMap<>();
        if(tag.getValue().containsKey("StoredEnchantments")) {
            ListTag list = (ListTag) tag.getValue().get("StoredEnchantments");
            for (Tag tag : list.getValue()) {
                CompoundTag compoundTag = (CompoundTag) tag;
                if (compoundTag.getValue().containsKey("id") && compoundTag.getValue().containsKey("lvl")) {
                    map.put(Enchantment.getById(((ShortTag) compoundTag.getValue().get("id")).getValue()),
                            Integer.valueOf(((ShortTag) compoundTag.getValue().get("lvl")).getValue()));
                }
            }
        }
        return map;
    }

    @Override
    public boolean addStoredEnchant(Enchantment enchantment, int level, boolean ignoreRestrictions) {
        if(level == 0)return removeEnchant(enchantment);
        if (ignoreRestrictions || level >= enchantment.getStartLevel() && level <= enchantment.getMaxLevel()) {
            int old = getEnchantLevel(enchantment);
            if(tag == null){
                tag = new CompoundTag("tag", new HashMap<String, Tag>());
            }
            if(!tag.getValue().containsKey("StoredEnchantments")){
                tag.getValue().put("StoredEnchantments", new ListTag("ench", CompoundTag.class, new ArrayList<Tag>()));
            }
            CompoundTag ench = null;
            ListTag list = (ListTag) tag.getValue().get("ench");
            for (Tag tag : list.getValue()) {
                CompoundTag compoundTag = (CompoundTag) tag;
                if (compoundTag.getValue().containsKey("id") && compoundTag.getValue().containsKey("lvl")) {
                    if(((ShortTag) compoundTag.getValue().get("id")).getValue() == enchantment.getId()){
                        ench = compoundTag;
                    }
                }
            }
            if(ench == null){
                ench = new CompoundTag("", new HashMap<String, Tag>());
                ench.getValue().put("id", new ShortTag("id", (short) enchantment.getId()));
            }
            ench.getValue().put("lvl", new ShortTag("lvl", (short) level));
            return old != level;
        }
        return false;
    }

    @Override
    public boolean removeStoredEnchant(Enchantment enchantment) throws IllegalArgumentException {
        if(tag == null)return false;
        if(tag.getValue().containsKey("StoredEnchantments")) {
            ListTag list = (ListTag) tag.getValue().get("StoredEnchantments");
            CompoundTag toRemove = null;
            for (Tag tag : list.getValue()) {
                CompoundTag compoundTag = (CompoundTag) tag;
                if (compoundTag.getValue().containsKey("id") && compoundTag.getValue().containsKey("lvl")) {
                    if(((ShortTag) compoundTag.getValue().get("id")).getValue() == enchantment.getId()){
                        toRemove = compoundTag;
                    }
                }
            }
            if(toRemove != null){
                list.getValue().remove(toRemove);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasConflictingStoredEnchant(Enchantment enchantment) {
        return checkConflictingEnchants(getStoredEnchants(), enchantment);
    }

    @Override
    public boolean applicableTo(Material type) {
        switch (type) {
            case ENCHANTED_BOOK:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty() && !hasStoredEnchants();
    }

    @Override
    boolean equalsCommon(FakeItemMeta meta) {
        if (!super.equalsCommon(meta)) {
            return false;
        }
        if (meta instanceof FakeEnchantedBookMeta) {
            FakeEnchantedBookMeta that = (FakeEnchantedBookMeta) meta;

            return (hasStoredEnchants() ? that.hasStoredEnchants() && this.getStoredEnchants().equals(that.getStoredEnchants()) : !that.hasStoredEnchants());
        }
        return true;
    }

    @Override
    boolean notUncommon(FakeItemMeta meta) {
        return super.notUncommon(meta) && (meta instanceof FakeEnchantedBookMeta || !hasStoredEnchants());
    }

    @Override
    @SneakyThrows
    public FakeEnchantedBookMeta clone() {
        if(tag == null)return new FakeEnchantedBookMeta(null);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        NBTOutputStream out = new NBTOutputStream(os);
        out.writeTag(tag);
        out.close();
        byte[] bytes = os.toByteArray();
        NBTInputStream in = new NBTInputStream(new ByteArrayInputStream(bytes));
        return new FakeEnchantedBookMeta((CompoundTag) in.readTag());
    }
}
