package PluginBukkitBridge.item;

import PluginBukkitBridge.MyPlugin;
import lombok.SneakyThrows;
import org.bukkit.Material;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jnbt.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class FakePotionMeta extends FakeItemMeta implements PotionMeta {
    public FakePotionMeta(CompoundTag tag) {
        super(tag);
    }

    @Override
    public void setBasePotionData(PotionData potionData) {
        MyPlugin.fixme();
    }

    @Override
    public PotionData getBasePotionData() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public boolean hasCustomEffects() {
        if(tag == null)return false;
        return tag.getValue().containsKey("CustomPotionEffects");
    }

    @Override
    public List<PotionEffect> getCustomEffects() {
        if(tag == null)return new ArrayList<>();
        if(!tag.getValue().containsKey("CustomPotionEffects"))return new ArrayList<>();
        ListTag CustomPotionEffects = (ListTag) tag.getValue().get("CustomPotionEffects");
        ArrayList<PotionEffect> list = new ArrayList<>();
        for(Tag t : CustomPotionEffects.getValue()){
            CompoundTag potionEffect = (CompoundTag) t;
            PotionEffect effect = new PotionEffect(PotionEffectType.getById(((ByteTag)potionEffect.getValue().get("Id")).getValue()),
                    ((IntTag)potionEffect.getValue().get("Duration")).getValue(),
                    ((ByteTag)potionEffect.getValue().get("Amplifier")).getValue(),
                    potionEffect.getValue().containsKey("Ambient") && (((ByteTag)potionEffect.getValue().get("Ambient")).getValue() == 1));
            list.add(effect);
        }
        return list;
    }

    @Override
    public boolean addCustomEffect(PotionEffect potionEffect, boolean b) {
        if(hasCustomEffect(potionEffect.getType())){
            if(b){
                removeCustomEffect(potionEffect.getType());
            } else {
                return false;
            }
        }
        if(tag == null){
            tag = new CompoundTag("tag", new HashMap<String, Tag>());
        }
        if(!tag.getValue().containsKey("CustomPotionEffects")){
            tag.getValue().put("CustomPotionEffects", new ListTag("CustomPotionEffects", CompoundTag.class, new ArrayList<Tag>()));
        }
        HashMap<String, Tag> potion = new HashMap<>();
        potion.put("Id", new ByteTag("Id", (byte) potionEffect.getType().getId()));
        potion.put("Duration", new IntTag("Duration", potionEffect.getDuration()));
        potion.put("Amplifier", new ByteTag("Amplifier", (byte) potionEffect.getAmplifier()));
        potion.put("Ambient", new ByteTag("Ambient", (byte) (potionEffect.isAmbient() ? 1 : 0)));
        ((ListTag)tag.getValue().get("CustomPotionEffects")).getValue().add(new CompoundTag("", potion));
        return true;
    }

    @Override
    public boolean removeCustomEffect(PotionEffectType type) {
        if(tag == null)return false;
        if(tag.getValue().containsKey("CustomPotionEffects")) {
            ListTag list = (ListTag) tag.getValue().get("CustomPotionEffects");
            CompoundTag toRemove = null;
            for (Tag tag : list.getValue()) {
                CompoundTag compoundTag = (CompoundTag) tag;
                if (compoundTag.getValue().containsKey("Id")) {
                    if(((ShortTag) compoundTag.getValue().get("Id")).getValue() == type.getId()){
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
    public boolean hasCustomEffect(PotionEffectType type) {
        if(tag == null)return false;
        if(!tag.getValue().containsKey("CustomPotionEffects"))return false;
        ListTag list = (ListTag) tag.getValue().get("CustomPotionEffects");
        for(Tag tag: list.getValue()){
            CompoundTag compoundTag = (CompoundTag) tag;
            if(compoundTag.getValue().containsKey("Id")){
                if(((ShortTag) compoundTag.getValue().get("Id")).getValue() == type.getId()){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean setMainEffect(PotionEffectType type) {
        if(tag == null)return false;
        if(tag.getValue().containsKey("CustomPotionEffects")) {
            ListTag list = (ListTag) tag.getValue().get("CustomPotionEffects");
            CompoundTag effect = null;
            for (Tag tag : list.getValue()) {
                CompoundTag compoundTag = (CompoundTag) tag;
                if (compoundTag.getValue().containsKey("Id")) {
                    if(((ShortTag) compoundTag.getValue().get("Id")).getValue() == type.getId()){
                        effect = compoundTag;
                    }
                }
            }
            if(effect != null){
                list.getValue().remove(effect);
                list.getValue().add(0, effect);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean clearCustomEffects() {
        if(tag == null)return false;
        if(tag.getValue().containsKey("CustomPotionEffects")) {
            tag.getValue().remove("CustomPotionEffects");
            return true;
        }
        return false;
    }

    @Override
    @SneakyThrows
    public FakePotionMeta clone() {
        if(tag == null)return new FakePotionMeta(null);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        NBTOutputStream out = new NBTOutputStream(os);
        out.writeTag(tag);
        out.close();
        byte[] bytes = os.toByteArray();
        NBTInputStream in = new NBTInputStream(new ByteArrayInputStream(bytes));
        return new FakePotionMeta((CompoundTag) in.readTag());
    }

    @Override
    public boolean applicableTo(Material type) {
        switch(type) {
            case POTION:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty() && !hasCustomEffects();
    }


    @Override
    boolean notUncommon(FakeItemMeta meta) {
        return super.notUncommon(meta) && (meta instanceof FakePotionMeta || !hasCustomEffects());
    }

    @Override
    public boolean equalsCommon(FakeItemMeta meta) {
        if (!super.equalsCommon(meta)) {
            return false;
        }
        if (meta instanceof FakePotionMeta) {
            FakePotionMeta that = (FakePotionMeta) meta;

            return (this.hasCustomEffects() ? that.hasCustomEffects() && this.getCustomEffects().equals(that.getCustomEffects()) : !that.hasCustomEffects());
        }
        return true;
    }
}
