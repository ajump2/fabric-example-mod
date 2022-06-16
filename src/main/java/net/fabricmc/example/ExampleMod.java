package net.fabricmc.example;


import net.fabricmc.api.ModInitializer;
import net.fabricmc.example.blocks.CustomBlock;
import net.fabricmc.example.entities.CubeEntity;
import net.fabricmc.example.items.CustomItem;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("base_mod");
	public static final CustomItem CUSTOM_ITEM = new CustomItem(new FabricItemSettings());
	public static final Block CUSTOM_BLOCK = new CustomBlock(FabricBlockSettings.of(Material.METAL).strength(4.0f).requiresTool());

	public static final EntityType<CubeEntity> CUBE = Registry.register(Registry.ENTITY_TYPE,
			new Identifier("base_mod", "cube"),
			FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CubeEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
	);

	public static final ItemGroup MOD_GROUP = FabricItemGroupBuilder.create(
					new Identifier("base_mod","other"))
			.icon(() -> new ItemStack(Items.BOWL))
			.appendItems(stacks -> {
				stacks.add(new ItemStack(CUSTOM_ITEM));
				stacks.add(new ItemStack(CUSTOM_BLOCK));
			})
			.build();

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.debug("ExampleMod::onInitialize");

		Registry.register(Registry.ITEM, new Identifier("base_mod","custom_item"), CUSTOM_ITEM);
		Registry.register(Registry.BLOCK, new Identifier("base_mod", "custom_block"), CUSTOM_BLOCK);
		Registry.register(Registry.ITEM, new Identifier("base_mod", "custom_block"), new BlockItem(CUSTOM_BLOCK, new FabricItemSettings().group(MOD_GROUP)));
		FabricDefaultAttributeRegistry.register(CUBE, CubeEntity.createMobAttributes());
	}
}
