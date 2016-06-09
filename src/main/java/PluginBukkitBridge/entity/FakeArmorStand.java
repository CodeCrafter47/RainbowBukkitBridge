package PluginBukkitBridge.entity;

import PluginBukkitBridge.MyPlugin;
import PluginBukkitBridge.Util;
import PluginReference.MC_ArmorStand;
import PluginReference.MC_FloatTriplet;
import PluginReference.MC_ItemStack;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

import java.util.List;

/**
 * Created by florian on 28.11.14.
 */
public class FakeArmorStand extends FakeLivingEntity implements ArmorStand {

	MC_ArmorStand armorStand;

	public FakeArmorStand(MC_ArmorStand argEnt) {
		super(argEnt);
		armorStand = argEnt;
	}

	@Override public ItemStack getItemInHand() {
		return Util.getItemStack(armorStand.getItemInHand());
	}

	@Override public void setItemInHand(ItemStack itemStack) {
		armorStand.setItemInHand(Util.getItemStack(itemStack));
	}

	@Override public ItemStack getBoots() {
		return Util.getItemStack(armorStand.getArmor().get(0));
	}

	@Override public void setBoots(ItemStack itemStack) {
		List<MC_ItemStack> armor = armorStand.getArmor();
		armor.remove(0);
		armor.add(0, Util.getItemStack(itemStack));
		armorStand.setArmor(armor);
	}

	@Override public ItemStack getLeggings() {
		return Util.getItemStack(armorStand.getArmor().get(1));
	}

	@Override public void setLeggings(ItemStack itemStack) {
		List<MC_ItemStack> armor = armorStand.getArmor();
		armor.remove(1);
		armor.add(1, Util.getItemStack(itemStack));
		armorStand.setArmor(armor);
	}

	@Override public ItemStack getChestplate() {
		return Util.getItemStack(armorStand.getArmor().get(2));
	}

	@Override public void setChestplate(ItemStack itemStack) {
		List<MC_ItemStack> armor = armorStand.getArmor();
		armor.remove(2);
		armor.add(2, Util.getItemStack(itemStack));
		armorStand.setArmor(armor);
	}

	@Override public ItemStack getHelmet() {
		return Util.getItemStack(armorStand.getArmor().get(3));
	}

	@Override public void setHelmet(ItemStack itemStack) {
		List<MC_ItemStack> armor = armorStand.getArmor();
		armor.remove(3);
		armor.add(3, Util.getItemStack(itemStack));
		armorStand.setArmor(armor);
	}

	@Override public EulerAngle getBodyPose() {
		return getEulerAngle(armorStand.getPose().get(1));
	}

	@Override public void setBodyPose(EulerAngle eulerAngle) {
		List<MC_FloatTriplet> pose = armorStand.getPose();
		pose.remove(1);
		pose.add(1, getFloats(eulerAngle));
		armorStand.setPose(pose);
	}

	private MC_FloatTriplet getFloats(EulerAngle eulerAngle) {
		return new MC_FloatTriplet((float)eulerAngle.getX(), (float)eulerAngle.getY(), (float)eulerAngle.getZ());
	}

	@Override public EulerAngle getLeftArmPose() {
		return getEulerAngle(armorStand.getPose().get(2));
	}

	@Override public void setLeftArmPose(EulerAngle eulerAngle) {
		List<MC_FloatTriplet> pose = armorStand.getPose();
		pose.remove(2);
		pose.add(2, getFloats(eulerAngle));
		armorStand.setPose(pose);
	}

	@Override public EulerAngle getRightArmPose() {
		return getEulerAngle(armorStand.getPose().get(3));
	}

	@Override public void setRightArmPose(EulerAngle eulerAngle) {
		List<MC_FloatTriplet> pose = armorStand.getPose();
		pose.remove(3);
		pose.add(3, getFloats(eulerAngle));
		armorStand.setPose(pose);
	}

	@Override public EulerAngle getLeftLegPose() {
		return getEulerAngle(armorStand.getPose().get(4));
	}

	@Override public void setLeftLegPose(EulerAngle eulerAngle) {
		List<MC_FloatTriplet> pose = armorStand.getPose();
		pose.remove(4);
		pose.add(4, getFloats(eulerAngle));
		armorStand.setPose(pose);
	}

	@Override public EulerAngle getRightLegPose() {
		return getEulerAngle(armorStand.getPose().get(5));
	}

	@Override public void setRightLegPose(EulerAngle eulerAngle) {
		List<MC_FloatTriplet> pose = armorStand.getPose();
		pose.remove(5);
		pose.add(5, getFloats(eulerAngle));
		armorStand.setPose(pose);
	}

	@Override public EulerAngle getHeadPose() {
		return getEulerAngle(armorStand.getPose().get(0));
	}

	@Override public void setHeadPose(EulerAngle eulerAngle) {
		List<MC_FloatTriplet> pose = armorStand.getPose();
		pose.remove(0);
		pose.add(0, getFloats(eulerAngle));
		armorStand.setPose(pose);
	}

	@Override public boolean hasBasePlate() {
		return armorStand.hasBase();
	}

	@Override public void setBasePlate(boolean b) {
		armorStand.setHasBase(true);
	}

	@Override public boolean hasGravity() {
		MyPlugin.fixme();
		return false;
	}

	@Override public void setGravity(boolean b) {
		MyPlugin.fixme();
	}

	@Override public boolean isVisible() {
		MyPlugin.fixme();
		return true;
	}

	@Override public void setVisible(boolean b) {
		MyPlugin.fixme();
	}

	@Override public boolean hasArms() {
		return armorStand.hasArms();
	}

	@Override public void setArms(boolean b) {
		armorStand.setHasArms(b);
	}

	@Override public boolean isSmall() {
		MyPlugin.fixme();
		return false;
	}

	@Override public void setSmall(boolean b) {
		MyPlugin.fixme();
	}

	@Override
	public boolean isMarker() {
		MyPlugin.fixme();
		return false;
	}

	@Override
	public void setMarker(boolean b) {
		MyPlugin.fixme();
	}

	EulerAngle getEulerAngle(MC_FloatTriplet floatTriplet){
		return new EulerAngle(floatTriplet.x, floatTriplet.y, floatTriplet.z);
	}
}
