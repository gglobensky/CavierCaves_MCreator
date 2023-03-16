
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.gglobensky.caviercaves.init;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;

public class CaviercavesModTabs {
	public static CreativeModeTab TAB_CAVIER_CAVES;

	public static void load() {
		TAB_CAVIER_CAVES = new CreativeModeTab("tabcavier_caves") {
			@Override
			public ItemStack makeIcon() {
				return new ItemStack(CaviercavesModBlocks.WHITE_MARBLE.get());
			}

			@Override
			public boolean hasSearchBar() {
				return true;
			}
		}.setBackgroundSuffix("item_search.png");
	}
}
