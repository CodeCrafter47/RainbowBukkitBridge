package PluginBukkitBridge.item;

import lombok.SneakyThrows;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Material;
import org.bukkit.inventory.meta.FireworkMeta;
import org.jnbt.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.*;

public class FakeFireworkMeta extends FakeItemMeta implements FireworkMeta {
    public FakeFireworkMeta(CompoundTag tag) {
        super(tag);
    }

    static int getNBT(Type type) {
        switch (type) {
            case BALL:
                return 0;
            case BALL_LARGE:
                return 1;
            case STAR:
                return 2;
            case CREEPER:
                return 3;
            case BURST:
                return 4;
            default:
                throw new AssertionError(type);
        }
    }

    static Type getEffectType(int nbt) {
        switch (nbt) {
            case 0:
                return Type.BALL;
            case 1:
                return Type.BALL_LARGE;
            case 2:
                return Type.STAR;
            case 3:
                return Type.CREEPER;
            case 4:
                return Type.BURST;
            default:
                throw new AssertionError(nbt);
        }
    }

    @Override
    public void addEffect(FireworkEffect fireworkEffect) throws IllegalArgumentException {
        addEffects(fireworkEffect);
    }

    @Override
    public void addEffects(FireworkEffect... fireworkEffects) throws IllegalArgumentException {
        addEffects(Arrays.asList(fireworkEffects));
    }

    @Override
    public void addEffects(Iterable<FireworkEffect> fireworkEffects) throws IllegalArgumentException {
        if(tag == null){
            tag = new CompoundTag("tag", new HashMap<String, Tag>());
        }
        if(!tag.getValue().containsKey("Fireworks")){
            tag.getValue().put("Fireworks", new CompoundTag("Fireworks", new HashMap<String, Tag>()));
        }
        CompoundTag fireworks = (CompoundTag) tag.getValue().get("Fireworks");
        if(!fireworks.getValue().containsKey("Explosions")){
            fireworks.getValue().put("Explosions", new ListTag("Explosions", CompoundTag.class, new ArrayList<Tag>()));
        }
        List<Tag> explosions = ((ListTag)fireworks.getValue().get("Explosions")).getValue();
        for(FireworkEffect effect: fireworkEffects){
            Map<String, Tag> tags = new HashMap<>();
            int[] colors = new int[effect.getColors().size()];
            for(int i = 0; i < colors.length; i++)colors[i] = effect.getColors().get(i).asRGB();
            tags.put("Colors", new IntArrayTag("Colors", colors));
            int[] fadeColors = new int[effect.getFadeColors().size()];
            for(int i = 0; i < fadeColors.length; i++)fadeColors[i] = effect.getFadeColors().get(i).asRGB();
            tags.put("FadeColors", new IntArrayTag("FadeColors", fadeColors));
            tags.put("Flicker", new ByteTag("Flicker", (byte) (effect.hasFlicker() ? 1 : 0)));
            tags.put("Trail", new ByteTag("Trail", (byte) (effect.hasTrail() ? 1 : 0)));
            tags.put("Type", new ByteTag("Type", (byte) getNBT(effect.getType())));
            explosions.add(new CompoundTag("Explosion", tags));
        }
    }

    @Override
    public List<FireworkEffect> getEffects() {
        if(!hasEffects())return new ArrayList<>();
        CompoundTag fireworks = (CompoundTag) tag.getValue().get("Fireworks");
        List<Tag> explosions = ((ListTag)fireworks.getValue().get("Explosions")).getValue();
        List<FireworkEffect> effects = new ArrayList<>();
        for(Tag t : explosions){
            CompoundTag effectTag = (CompoundTag) t;
            List<Color> colors = new ArrayList<>();
            for(int i : ((IntArrayTag)effectTag.getValue().get("Colors")).getValue()){
                colors.add(Color.fromRGB(i));
            }
            List<Color> fadeColors = new ArrayList<>();
            for(int i : ((IntArrayTag)effectTag.getValue().get("FadeColors")).getValue()){
                fadeColors.add(Color.fromRGB(i));
            }
            boolean flicker = ((ByteTag)effectTag.getValue().get("Flicker")).getValue() != 0;
            boolean trail = ((ByteTag)effectTag.getValue().get("Trail")).getValue() != 0;
            Type type = getEffectType(((ByteTag)effectTag.getValue().get("Type")).getValue());
            effects.add(FireworkEffect.builder().withColor(colors).withFade(fadeColors).flicker(flicker).trail(trail).with(type).build());
        }
        return effects;
    }

    @Override
    public int getEffectsSize() {
        if(!hasEffects())return 0;
        CompoundTag fireworks = (CompoundTag) tag.getValue().get("Fireworks");
        List<Tag> explosions = ((ListTag)fireworks.getValue().get("Explosions")).getValue();
        return explosions.size();
    }

    @Override
    public void removeEffect(int i) throws IndexOutOfBoundsException {
        if(!hasEffects())return;
        CompoundTag fireworks = (CompoundTag) tag.getValue().get("Fireworks");
        List<Tag> explosions = ((ListTag)fireworks.getValue().get("Explosions")).getValue();
        explosions.remove(i);
    }

    @Override
    public void clearEffects() {
        if(!hasEffects())return;
        CompoundTag fireworks = (CompoundTag) tag.getValue().get("Fireworks");
        fireworks.getValue().put("Explosions", new ListTag("Explosions", CompoundTag.class, new ArrayList<Tag>()));
    }

    @Override
    public boolean hasEffects() {
        return tag != null && tag.getValue().containsKey("Fireworks") &&
                ((CompoundTag)tag.getValue().get("Fireworks")).getValue().containsKey("Explosions");
    }

    @Override
    public int getPower() {
        if(!hasPower())return 0;
        CompoundTag fireworks = (CompoundTag) tag.getValue().get("Fireworks");
        return ((ByteTag)fireworks.getValue().get("Flight")).getValue();
    }

    @Override
    public void setPower(int i) throws IllegalArgumentException {
        if(tag == null){
            tag = new CompoundTag("tag", new HashMap<String, Tag>());
        }
        if(!tag.getValue().containsKey("Fireworks")){
            tag.getValue().put("Fireworks", new CompoundTag("Fireworks", new HashMap<String, Tag>()));
        }
        CompoundTag fireworks = (CompoundTag) tag.getValue().get("Fireworks");
        fireworks.getValue().put("Flight", new ByteTag("Flight", (byte) i));
    }

    @Override
    public boolean applicableTo(Material type) {
        switch(type) {
            case FIREWORK:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty() && isFireworkEmpty();
    }

    boolean isFireworkEmpty() {
        return  !(hasEffects() || hasPower());
    }

    boolean hasPower() {
        return tag != null && tag.getValue().containsKey("Fireworks") &&
                ((CompoundTag)tag.getValue().get("Fireworks")).getValue().containsKey("Flight");
    }

    @Override
    boolean equalsCommon(FakeItemMeta meta) {
        if (!super.equalsCommon(meta)) {
            return false;
        }

        if (meta instanceof FakeFireworkMeta) {
            FakeFireworkMeta that = (FakeFireworkMeta) meta;

            return (hasPower() ? that.hasPower() && this.getPower() == that.getPower() : !that.hasPower())
                    && (hasEffects() ? that.hasEffects() && this.getEffects().equals(that.getEffects()) : !that.hasEffects());
        }

        return true;
    }

    @Override
    boolean notUncommon(FakeItemMeta meta) {
        return super.notUncommon(meta) && (meta instanceof FakeFireworkMeta || isFireworkEmpty());
    }

    @Override
    @SneakyThrows
    public FakeFireworkMeta clone() {
        if(tag == null)return new FakeFireworkMeta(null);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        NBTOutputStream out = new NBTOutputStream(os);
        out.writeTag(tag);
        out.close();
        byte[] bytes = os.toByteArray();
        NBTInputStream in = new NBTInputStream(new ByteArrayInputStream(bytes));
        return new FakeFireworkMeta((CompoundTag) in.readTag());
    }
}
