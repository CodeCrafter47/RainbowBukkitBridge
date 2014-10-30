package PluginBukkitBridge.item;

import lombok.SneakyThrows;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.Repairable;
import org.jnbt.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FakeItemMeta implements ItemMeta, Repairable {
    CompoundTag tag;

    public FakeItemMeta(CompoundTag tag) {
        this.tag = tag;
    }

    @Override
    public boolean hasDisplayName() {
        if(tag == null)return false;
        if(!tag.getValue().containsKey("display"))return false;
        CompoundTag display = (CompoundTag) tag.getValue().get("display");
        return display.getValue().containsKey("Name");
    }

    @Override
    public String getDisplayName() {
        if(tag == null)return null;
        if(!tag.getValue().containsKey("display"))return null;
        CompoundTag display = (CompoundTag) tag.getValue().get("display");
        if(!display.getValue().containsKey("Name"))return null;
        return ((StringTag)display.getValue().get("Name")).getValue();
    }

    @Override
    public void setDisplayName(String s) {
        if(tag == null){
            tag = new CompoundTag("tag", new HashMap<String, Tag>());
        }
        if(!tag.getValue().containsKey("display")){
            tag.getValue().put("display", new CompoundTag("display", new HashMap<String, Tag>()));
        }
        CompoundTag display = (CompoundTag) tag.getValue().get("display");
        if(s != null){
            display.getValue().put("Name", new StringTag("Name", s));
        } else {
            if(display.getValue().containsKey("Name")){
                display.getValue().remove("Name");
            }
        }
    }

    @Override
    public boolean hasLore() {
        if(tag == null)return false;
        if(!tag.getValue().containsKey("display"))return false;
        CompoundTag display = (CompoundTag) tag.getValue().get("display");
        return display.getValue().containsKey("Lore");
    }

    @Override
    public List<String> getLore() {
        if(tag == null)return null;
        if(!tag.getValue().containsKey("display"))return null;
        CompoundTag display = (CompoundTag) tag.getValue().get("display");
        if(!display.getValue().containsKey("Lore"))return null;
        ArrayList<String> list = new ArrayList<>();
        for(Tag t : ((ListTag)display.getValue().get("Lore")).getValue()){
            list.add(((StringTag)t).getValue());
        }
        return list;
    }

    @Override
    public void setLore(List<String> strings) {
        if(tag == null){
            tag = new CompoundTag("tag", new HashMap<String, Tag>());
        }
        if(!tag.getValue().containsKey("display")){
            tag.getValue().put("display", new CompoundTag("display", new HashMap<String, Tag>()));
        }
        CompoundTag display = (CompoundTag) tag.getValue().get("display");
        if(strings != null){
            List<Tag> tags = new ArrayList<>();
            for(String s: strings)tags.add(new StringTag("", s));
            ListTag list = new ListTag("Lore", StringTag.class, tags);
            display.getValue().put("Lore", list);
        } else {
            if(display.getValue().containsKey("Lore")){
                display.getValue().remove("Lore");
            }
        }
    }

    @Override
    public boolean hasEnchants() {
        if(tag == null)return false;
        if(!tag.getValue().containsKey("ench"))return false;
        ListTag list = (ListTag) tag.getValue().get("ench");
        return !list.getValue().isEmpty();
    }

    @Override
    public boolean hasEnchant(Enchantment enchantment) {
        return 0 != getEnchantLevel(enchantment);
    }

    @Override
    public int getEnchantLevel(Enchantment enchantment) {
        if(tag == null)return 0;
        if(!tag.getValue().containsKey("ench"))return 0;
        ListTag list = (ListTag) tag.getValue().get("ench");
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
    public Map<Enchantment, Integer> getEnchants() {
        if(tag == null)return new HashMap<>();
        Map<Enchantment, Integer> map = new HashMap<>();
        if(tag.getValue().containsKey("ench")) {
            ListTag list = (ListTag) tag.getValue().get("ench");
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
    public boolean addEnchant(Enchantment enchantment, int level, boolean ignoreRestrictions) {
        if(level == 0)return removeEnchant(enchantment);
        if (ignoreRestrictions || level >= enchantment.getStartLevel() && level <= enchantment.getMaxLevel()) {
            int old = getEnchantLevel(enchantment);
            if(tag == null){
                tag = new CompoundTag("tag", new HashMap<String, Tag>());
            }
            if(!tag.getValue().containsKey("ench")){
                tag.getValue().put("ench", new ListTag("ench", CompoundTag.class, new ArrayList<Tag>()));
            }
            CompoundTag ench = null;
            ListTag list = (ListTag) tag.getValue().get("ench");
            for (Tag tag : list.getValue()) {
                CompoundTag compoundTag = (CompoundTag) tag;
                if (compoundTag.getValue().containsKey("id")) {
                    if(((ShortTag) compoundTag.getValue().get("id")).getValue() == enchantment.getId()){
                        ench = compoundTag;
                    }
                }
            }
            if(ench == null){
                ench = new CompoundTag("", new HashMap<String, Tag>());
                ench.getValue().put("id", new ShortTag("id", (short) enchantment.getId()));
                list.getValue().add(ench);
            }
            ench.getValue().put("lvl", new ShortTag("lvl", (short) level));
            return old != level;
        }
        return false;
    }

    @Override
    public boolean removeEnchant(Enchantment enchantment) {
        if(tag == null)return false;
        if(tag.getValue().containsKey("ench")) {
            ListTag list = (ListTag) tag.getValue().get("ench");
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
    public boolean hasConflictingEnchant(Enchantment enchantment) {
        return checkConflictingEnchants(getEnchants(), enchantment);
    }

    @Override
    public Spigot spigot() {
        return new Spigot(){
            @Override
            public void setUnbreakable(boolean unbreakable) {
                if (tag == null){
                    tag = new CompoundTag("tag", new HashMap<String, Tag>());
                }
                int value = unbreakable ? 1 : 0;
                tag.getValue().put("Unbreakable", new IntTag("Unbreakable", value));
            }

            @Override
            public boolean isUnbreakable() {
                if(tag == null)return false;
                return tag.getValue().containsKey("Unbreakable") && ((IntTag) tag.getValue().get("Unbreakable")).getValue() == 1;
            }
        };
    }

    static boolean checkConflictingEnchants(Map<Enchantment, Integer> enchantments, Enchantment ench) {
        if (enchantments == null || enchantments.isEmpty()) {
            return false;
        }

        for (Enchantment enchant : enchantments.keySet()) {
            if (enchant.conflictsWith(ench)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean hasRepairCost() {
        if(tag == null)return false;
        return tag.getValue().containsKey("RepairCost");
    }

    @Override
    public int getRepairCost() {
        if(tag == null)return 0;
        return ((IntTag)tag.getValue().get("RepairCost")).getValue();
    }

    @Override
    public void setRepairCost(int i) {
        if(tag == null){
            tag = new CompoundTag("tag", new HashMap<String, Tag>());
        }
        tag.getValue().put("RepairCost", new IntTag("RepairCost", i));
    }

    @Override
    @SneakyThrows
    public FakeItemMeta clone() {
        if(tag == null)return new FakeItemMeta(null);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        NBTOutputStream out = new NBTOutputStream(os);
        out.writeTag(tag);
        out.close();
        byte[] bytes = os.toByteArray();
        NBTInputStream in = new NBTInputStream(new ByteArrayInputStream(bytes));
        return new FakeItemMeta((CompoundTag) in.readTag());
    }

    @Override
    public Map<String, Object> serialize() {
        return null;
    }

    public CompoundTag getTag(){
        return tag;
    }

    public boolean applicableTo(Material type) {
        return type != Material.AIR;
    }

    public boolean isEmpty() {
        return !(hasDisplayName() || hasEnchants() || hasAttributes() || hasLore() || hasRepairCost());
    }

    public boolean hasAttributes() {
        return tag != null && tag.getValue().containsKey("AttributeModifiers");
    }

    /**
     * This method is almost as weird as notUncommon.
     * Only return false if your common internals are unequal.
     * Checking your own internals is redundant if you are not common, as notUncommon is meant for checking those 'not common' variables.
     */
    boolean equalsCommon(FakeItemMeta that) {
        return ((this.hasDisplayName() ? that.hasDisplayName() && this.getDisplayName().equals(that.getDisplayName()) : !that.hasDisplayName()))
                && (this.hasEnchants() ? that.hasEnchants() && this.getEnchants().equals(that.getEnchants()) : !that.hasEnchants())
                && (this.hasLore() ? that.hasLore() && this.getLore().equals(that.getLore()) : !that.hasLore())
       // fixme         && (this.hasAttributes() ? that.hasAttributes() && this.attributes.equals(that.attributes) : !that.hasAttributes())
                && (this.hasRepairCost() ? that.hasRepairCost() && this.getRepairCost() == that.getRepairCost() : !that.hasRepairCost());
    }

    /**
     * This method is a bit weird...
     * Return true if you are a common class OR your uncommon parts are empty.
     * Empty uncommon parts implies the NBT data would be equivalent if both were applied to an item
     */
    boolean notUncommon(FakeItemMeta meta) {
        return true;
    }
}
