package PluginBukkitBridge.item;

import lombok.SneakyThrows;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.inventory.meta.FireworkEffectMeta;
import org.jnbt.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by florian on 29.10.14.
 */
public class FakeFireworkEffectMeta extends FakeItemMeta implements FireworkEffectMeta {
    public FakeFireworkEffectMeta(CompoundTag tag) {
        super(tag);
    }

    static int getNBT(FireworkEffect.Type type) {
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

    static FireworkEffect.Type getEffectType(int nbt) {
        switch (nbt) {
            case 0:
                return FireworkEffect.Type.BALL;
            case 1:
                return FireworkEffect.Type.BALL_LARGE;
            case 2:
                return FireworkEffect.Type.STAR;
            case 3:
                return FireworkEffect.Type.CREEPER;
            case 4:
                return FireworkEffect.Type.BURST;
            default:
                throw new AssertionError(nbt);
        }
    }

    @Override
    public void setEffect(FireworkEffect effect) {
        if(tag == null){
            tag = new CompoundTag("tag", new HashMap<String, Tag>());
        }

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
        tag.getValue().put("Explosion", new CompoundTag("Explosion", tags));
    }

    @Override
    public boolean hasEffect() {
        return tag != null && tag.getValue().containsKey("Explosion");
    }

    @Override
    public FireworkEffect getEffect() {
        if(!hasEffect())return null;
        CompoundTag effectTag = (CompoundTag) tag.getValue().get("Explosion");
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
        FireworkEffect.Type type = getEffectType(((ByteTag)effectTag.getValue().get("Type")).getValue());
        return FireworkEffect.builder().withColor(colors).withFade(fadeColors).flicker(flicker).trail(trail).with(type).build();
    }

    @Override
    public boolean applicableTo(Material type) {
        switch (type) {
            case FIREWORK_CHARGE:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty() && !hasEffect();
    }

    @Override
    boolean equalsCommon(FakeItemMeta meta) {
        if (!super.equalsCommon(meta)) {
            return false;
        }
        if (meta instanceof FakeFireworkEffectMeta) {
            FakeFireworkEffectMeta that = (FakeFireworkEffectMeta) meta;

            return (hasEffect() ? that.hasEffect() && this.getEffect().equals(that.getEffect()) : !that.hasEffect());
        }
        return true;
    }

    @Override
    boolean notUncommon(FakeItemMeta meta) {
        return super.notUncommon(meta) && (meta instanceof FakeFireworkEffectMeta || !hasEffect());
    }

    @Override
    @SneakyThrows
    public FakeFireworkEffectMeta clone() {
        if(tag == null)return new FakeFireworkEffectMeta(null);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        NBTOutputStream out = new NBTOutputStream(os);
        out.writeTag(tag);
        out.close();
        byte[] bytes = os.toByteArray();
        NBTInputStream in = new NBTInputStream(new ByteArrayInputStream(bytes));
        return new FakeFireworkEffectMeta((CompoundTag) in.readTag());
    }
}
