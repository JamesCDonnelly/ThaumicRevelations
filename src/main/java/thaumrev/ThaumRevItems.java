package thaumrev;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;
import thaumcraft.api.wands.StaffRod;
import thaumcraft.api.wands.WandRod;
import thaumrev.item.*;
import thaumrev.item.armor.ItemWardenArmor;
import thaumrev.item.armor.ItemWardenclothArmor;
import thaumrev.item.baubles.ItemLoveRing;
import thaumrev.item.baubles.ItemWardenAmulet;
import thaumrev.item.focus.ItemFocusIllumination;
import thaumrev.item.wands.ItemWandCore;

import static thaumrev.ThaumRevLibrary.*;

public class ThaumRevItems {
    public static void registerItems() {
        loadMaterials();
        loadItems();
        loadWandCores();

        GameRegistry.registerItem(itemResource, "itemResource");
        GameRegistry.registerItem(itemWardenWeapon, "itemWardenWeapon");
        GameRegistry.registerItem(itemWardenBow, "itemWardenBow");
        GameRegistry.registerItem(itemWardenAmulet, "itemWardenAmulet");

        // GameRegistry.registerItem(itemCrimsonHat, "itemCrimsonHat");

        GameRegistry.registerItem(itemWardenHelm, "itemWardenHelm");
        GameRegistry.registerItem(itemWardenChest, "itemWardenChest");
        GameRegistry.registerItem(itemWardenLegs, "itemWardenLegs");
        GameRegistry.registerItem(itemWardenBoots, "itemWardenBoots");

        GameRegistry.registerItem(itemWardenclothChest, "itemWardenclothChest");
        GameRegistry.registerItem(itemWardenclothLegs, "itemWardenclothLegs");
        GameRegistry.registerItem(itemWardenclothBoots, "itemWardenclothBoots");

        GameRegistry.registerItem(itemLoveRing, "itemLoveRing");
        GameRegistry.registerItem(itemWaslieHammer, "itemWaslieHammer");
        GameRegistry.registerItem(itemFocusIllumination, "itemFocusIllumination");
        // GameRegistry.registerItem(itemFocusPurity, "itemFocusPurity"); // Broken

        GameRegistry.registerItem(itemExcubituraSeeds, "itemExcubituraSeeds");

        GameRegistry.registerItem(itemWandCore, "itemWandCore");
    }

    private static void loadItems() {
        itemResource = new ItemResource();
        itemWardenAmulet = new ItemWardenAmulet();
        itemWardenWeapon = new ItemWardenWeapon();
        itemWardenBow = new ItemWardenBow();
        // itemFocusPurity = new ItemFocusPurity(); // Broken

        // itemCrimsonHat = new ItemCrimsonHat();

        itemWardenHelm = new ItemWardenArmor(0, "itemWardenHelm");
        itemWardenChest = new ItemWardenArmor(1, "itemWardenChest");
        itemWardenLegs = new ItemWardenArmor(2, "itemWardenLegs");
        itemWardenBoots = new ItemWardenArmor(3, "itemWardenBoots");

        itemWardenclothChest = new ItemWardenclothArmor(1, "itemWardenclothChest");
        itemWardenclothLegs = new ItemWardenclothArmor(2, "itemWardenclothLegs");
        itemWardenclothBoots = new ItemWardenclothArmor(3, "itemWardenclothBoots");

        itemLoveRing = new ItemLoveRing();
        itemWaslieHammer = new ItemWaslieHammer();
        itemFocusIllumination = new ItemFocusIllumination();

        itemExcubituraSeeds = new ItemExcubituraSeeds();
    }

    private static void loadMaterials() {
        armorMaterialWarden = EnumHelper.addArmorMaterial(
                "WARDEN",
                50,
                new int[] {3, 6, 8, 3},
                0);
        armorMaterialWardencloth = EnumHelper.addArmorMaterial(
                "WARDENCLOTH",
                50,
                new int[] {0, 2, 1, 0},
                15);
        armorMaterialCrimsoncloth = EnumHelper.addArmorMaterial(
                "CRIMSONCLOTH",
                50,
                new int[] {2, 3, 2, 1},
                15);

        toolMaterialWarden = EnumHelper.addToolMaterial(
                "WARDEN",
                9,
                50,
                12.0F,
                4,
                0);
    }

    private static void loadWandCores() {
        itemWandCore = new ItemWandCore();

        /** Wands **/
        WAND_ROD_WARDENCLOTH = new WandRod(
                "wardencloth",
                150,
                new ItemStack(
                        itemWandCore,
                        1,
                        0),
                12,
                new ResourceLocation("thaumrev", "textures/models/rod_wardencloth.png"));

        WAND_ROD_VOIDWOOD = new WandRod(
                "voidwood",
                200,
                new ItemStack(
                        itemWandCore,
                        1,
                        1),
                12,
                new ResourceLocation("thaumrev", "textures/models/rod_voidwood.png"));

        WAND_ROD_CRIMSONCULT = new WandRod(
                "crimsoncult",
                100,
                new ItemStack(
                        itemWandCore,
                        1,
                        2),
                12,
                new ResourceLocation("thaumrev", "textures/models/rod_crimsoncult.png"));
        WAND_ROD_CRIMSONCULT.setGlowing(true);


        /** Staffs **/
        STAFF_ROD_WARDENCLOTH = new StaffRod(
                "wardencloth",
                375,
                new ItemStack(
                        itemWandCore,
                        1,
                        10),
                32,
                new ResourceLocation("thaumrev", "textures/models/rod_wardencloth_staff.png"));

        STAFF_ROD_VOIDWOOD = new StaffRod(
                "voidwood",
                500,
                new ItemStack(
                        itemWandCore,
                        1,
                        11),
                32,
                new ResourceLocation("thaumrev", "textures/models/rod_voidwood_staff.png"));
        STAFF_ROD_VOIDWOOD.setRunes(true);

        STAFF_ROD_CRIMSONCULT = new StaffRod(
                "crimsoncult",
                1000,
                new ItemStack(
                        itemWandCore,
                        1,
                        12),
                32,
                new ResourceLocation("thaumrev", "textures/models/rod_crimsoncult_staff.png"));
        STAFF_ROD_CRIMSONCULT.setRunes(true);
        STAFF_ROD_CRIMSONCULT.setGlowing(true);
    }
}
