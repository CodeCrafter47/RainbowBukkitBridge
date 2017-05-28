package PluginBukkitBridge.entity.attribute;

import PluginBukkitBridge.MyPlugin;
import PluginBukkitBridge.Util;
import PluginReference.MC_Attribute;
import PluginReference.MC_AttributeModifier;
import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;

import java.util.Collection;

public class FakeAttributeInstance implements AttributeInstance {
    private final MC_Attribute attribute;

    public FakeAttributeInstance(MC_Attribute attribute) {
        this.attribute = attribute;
    }

    @Override
    public Attribute getAttribute() {
        return Util.unwrapAttributeType(attribute.getType());
    }

    @Override
    public double getBaseValue() {
        return attribute.getBaseValue();
    }

    @Override
    public void setBaseValue(double v) {
        attribute.setBaseValue(v);
    }

    @Override
    public Collection<AttributeModifier> getModifiers() {
        return Collections2.transform(attribute.getModifiers(), new Function<MC_AttributeModifier, AttributeModifier>() {
            @Override
            public AttributeModifier apply(MC_AttributeModifier input) {
                return Util.wrapAttributeModifier(input);
            }
        });
    }

    @Override
    public void addModifier(AttributeModifier attributeModifier) {
        attribute.addModifier(Util.wrapAttributeModifier(attributeModifier));
    }

    @Override
    public void removeModifier(AttributeModifier attributeModifier) {
        attribute.removeModifier(Util.wrapAttributeModifier(attributeModifier));
    }

    @Override
    public double getValue() {
        return attribute.getEffectiveValue();
    }

    @Override
    public double getDefaultValue() {
        MyPlugin.fixme();
        return 0;
    }
}
