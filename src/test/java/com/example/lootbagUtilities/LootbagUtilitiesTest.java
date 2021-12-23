package com.example.lootbagUtilities;

import net.runelite.api.MenuAction;
import net.runelite.api.MenuEntry;
import net.runelite.api.ItemID;
import org.junit.Test;
import com.example.lootbagUtilities.LootbagUtilities.DestroyableItem;

import net.runelite.client.menus.TestMenuEntry;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class LootbagUtilitiesTest {
    // Wrapper around annoying MenuEntry setters
    private static MenuEntry newMenuEntry(
            String option,
            String target,
            int identifier,
            MenuAction type,
            int param0,
            int param1)
    {
        MenuEntry entry = new TestMenuEntry();
        entry.setOption(option);
        entry.setTarget(target);
        entry.setIdentifier(identifier);
        entry.setType(type);
        entry.setParam0(param0);
        entry.setParam1(param1);
        return entry;
    }

    //Script to extract menu entries (copy and paste into runelite jshell):
	/*
subscribe(MenuOpened.class, (MenuOpened e) -> {
    for(MenuEntry entry: client.getMenuEntries()) {
        log.info(
            "newMenuEntry(\"{}\", \"{}\", {}, MenuAction.{}, {}, {}),",
            entry.getOption(),
            entry.getTarget(),
            entry.getIdentifier(),
            entry.getType(),
		    entry.getParam0(),
            entry.getParam1(),
        );
    }
});
	 */
    static MenuEntry cancelOption = newMenuEntry("Cancel", "", 0, MenuAction.CANCEL, 0, 0);
    static int emerald = ItemID.UNCUT_EMERALD+1;
    static MenuEntry[] rightClickNotedEmeralds = {
            cancelOption,
            newMenuEntry("Examine", "<col=ff9040>Uncut emerald", emerald, MenuAction.EXAMINE_ITEM, 5, 9764864),
            newMenuEntry("Drop", "<col=ff9040>Uncut emerald", emerald, MenuAction.ITEM_FIFTH_OPTION, 5, 9764864),
            newMenuEntry("Use", "<col=ff9040>Uncut emerald", emerald, MenuAction.ITEM_USE, 5, 9764864),
    };
    static MenuEntry[] rightClickGuard = {
            cancelOption,
            newMenuEntry("Examine", "<col=ffff00>Guard<col=ff00>  (level-21)", 9334, MenuAction.EXAMINE_NPC, 0, 0),
            newMenuEntry("Walk here", "", 0, MenuAction.WALK, 228, 34),
            newMenuEntry("Pickpocket", "<col=ffff00>Guard<col=ff00>  (level-21)", 9334, MenuAction.NPC_THIRD_OPTION, 0, 0),
            newMenuEntry("Attack", "<col=ffff00>Guard<col=ff00>  (level-21)", 9334, MenuAction.NPC_SECOND_OPTION, 0, 0),
    };
    static MenuEntry[] rightClickTreeAndSkeleton = {
            cancelOption,
            newMenuEntry("Examine", "<col=ffff>Oak", 10820, MenuAction.EXAMINE_OBJECT, 83, 78),
            newMenuEntry("Examine", "<col=ffff00>Skeleton<col=ff00>  (level-22)", 22109, MenuAction.EXAMINE_NPC, 0, 0),
            newMenuEntry("Walk here", "", 0, MenuAction.WALK, 333, 89),
            newMenuEntry("Chop down", "<col=ffff>Oak", 10820, MenuAction.GAME_OBJECT_FIRST_OPTION, 83, 78),
            newMenuEntry("Attack", "<col=ffff00>Skeleton<col=ff00>  (level-22)", 22109, MenuAction.NPC_SECOND_OPTION, 0, 0),
    };
    static int eek = ItemID.EEK;
    static MenuEntry[] rightClickInventoryEek = {
            cancelOption,
            newMenuEntry("Examine", "<col=ff9040>Eek", eek, MenuAction.EXAMINE_ITEM, 9, 9764864),
            newMenuEntry("Destroy", "<col=ff9040>Eek", eek, MenuAction.ITEM_FIFTH_OPTION, 9, 9764864),
            newMenuEntry("Use", "<col=ff9040>Eek", eek, MenuAction.ITEM_USE, 9, 9764864),
            newMenuEntry("Talk-to", "<col=ff9040>Eek", eek, MenuAction.ITEM_THIRD_OPTION, 9, 9764864),
            newMenuEntry("Hold", "<col=ff9040>Eek", eek, MenuAction.ITEM_SECOND_OPTION, 9, 9764864),
    };
    static MenuEntry[] rightClickEquippedEek = {
            cancelOption,
            newMenuEntry("Examine", "<col=ff9040>Eek</col>", 10, MenuAction.CC_OP_LOW_PRIORITY, -1, 25362450),
            newMenuEntry("Talk-to", "<col=ff9040>Eek</col>", 2, MenuAction.CC_OP, -1, 25362450),
            newMenuEntry("Remove", "<col=ff9040>Eek</col>", 1, MenuAction.CC_OP, -1, 25362450),
    };
    static int plankSack = ItemID.PLANK_SACK;
    static MenuEntry[] shiftRightClickPlankSack = {
            cancelOption,
            newMenuEntry("Examine", "<col=ff9040>Plank sack", plankSack, MenuAction.EXAMINE_ITEM, 23, 9764864),
            newMenuEntry("Drop", "<col=ff9040>Plank sack", plankSack, MenuAction.ITEM_FIFTH_OPTION, 23, 9764864),
            newMenuEntry("Empty", "<col=ff9040>Plank sack", plankSack, MenuAction.ITEM_FOURTH_OPTION, 23, 9764864),
            newMenuEntry("Use", "<col=ff9040>Plank sack", plankSack, MenuAction.ITEM_USE, 23, 9764864),
            newMenuEntry("Check", "<col=ff9040>Plank sack", plankSack, MenuAction.ITEM_THIRD_OPTION, 23, 9764864),
            newMenuEntry("Fill", "<col=ff9040>Plank sack", plankSack, MenuAction.ITEM_FIRST_OPTION, 23, 9764864),
    };
    static int lootingBag = ItemID.LOOTING_BAG;
    static MenuEntry[] rightClickLootingBag = {
            cancelOption,
            newMenuEntry("Examine", "<col=ff9040>Looting bag", lootingBag, MenuAction.EXAMINE_ITEM, 27, 9764864),
            newMenuEntry("Destroy", "<col=ff9040>Looting bag", lootingBag, MenuAction.ITEM_FIFTH_OPTION, 27, 9764864),
            newMenuEntry("Settings", "<col=ff9040>Looting bag", lootingBag, MenuAction.ITEM_FOURTH_OPTION, 27, 9764864),
            newMenuEntry("Use", "<col=ff9040>Looting bag", lootingBag, MenuAction.ITEM_USE, 27, 9764864),
            newMenuEntry("Deposit", "<col=ff9040>Looting bag", lootingBag, MenuAction.ITEM_THIRD_OPTION, 27, 9764864),
            newMenuEntry("Check", "<col=ff9040>Looting bag", lootingBag, MenuAction.ITEM_SECOND_OPTION, 27, 9764864),
            newMenuEntry("Open", "<col=ff9040>Looting bag", lootingBag, MenuAction.ITEM_FIRST_OPTION, 27, 9764864),
    };
    static int runePouch = ItemID.RUNE_POUCH;
    static MenuEntry[] rightClickRunePouch = {
            cancelOption,
            newMenuEntry("Examine", "<col=ff9040>Rune pouch", runePouch, MenuAction.EXAMINE_ITEM, 24, 9764864),
            newMenuEntry("Destroy", "<col=ff9040>Rune pouch", runePouch, MenuAction.ITEM_FIFTH_OPTION, 24, 9764864),
            newMenuEntry("Empty", "<col=ff9040>Rune pouch", runePouch, MenuAction.ITEM_FOURTH_OPTION, 24, 9764864),
            newMenuEntry("Use", "<col=ff9040>Rune pouch", runePouch, MenuAction.ITEM_USE, 24, 9764864),
            newMenuEntry("Open", "<col=ff9040>Rune pouch", runePouch, MenuAction.ITEM_FIRST_OPTION, 24, 9764864),
    };
    static int seedBox = ItemID.OPEN_SEED_BOX;
    static MenuEntry[] rightClickSeedBox = {
            cancelOption,
            newMenuEntry("Examine", "<col=ff9040>Open seed box", seedBox, MenuAction.EXAMINE_ITEM, 26, 9764864),
            newMenuEntry("Destroy", "<col=ff9040>Open seed box", seedBox, MenuAction.ITEM_FIFTH_OPTION, 26, 9764864),
            newMenuEntry("Close", "<col=ff9040>Open seed box", seedBox, MenuAction.ITEM_FOURTH_OPTION, 26, 9764864),
            newMenuEntry("Use", "<col=ff9040>Open seed box", seedBox, MenuAction.ITEM_USE, 26, 9764864),
            newMenuEntry("Check", "<col=ff9040>Open seed box", seedBox, MenuAction.ITEM_THIRD_OPTION, 26, 9764864),
            newMenuEntry("Empty", "<col=ff9040>Open seed box", seedBox, MenuAction.ITEM_SECOND_OPTION, 26, 9764864),
            newMenuEntry("Fill", "<col=ff9040>Open seed box", seedBox, MenuAction.ITEM_FIRST_OPTION, 26, 9764864),
    };

    // Assert that calling removeDestroyOptions
    static void assertOptionsUnchanged(MenuEntry[] entries, DestroyableItem[] config) {
        MenuEntry[] modifiedEntries = LootbagUtilities.removeDestroy(entries, config);
        assertArrayEquals(entries, modifiedEntries);
    }

    static void assertNoDestroyOption(MenuEntry[] entries)  {
        for(MenuEntry e: entries) {
            assert(!e.getOption().equals("Destroy"));
        }
    }

    @Test
    public void testRemoveDestroy() {
        for (int i = 0; i < 2 << 3; i++) {
            boolean removeLootingBagDestroy = i % 2 == 0;
            boolean removeRunePouchDestroy = i / 2 % 2 == 0;
            boolean removeSeedBoxDestroy = i / 4 % 2 == 0;
            MockLootbagUtilitiesConfig config = new MockLootbagUtilitiesConfig(
                    true,
                    removeLootingBagDestroy,
                    removeRunePouchDestroy,
                    false,
                    removeSeedBoxDestroy,
                    false,
                    false,
                    false,
                    false,
                    false
            );

            DestroyableItem[] removeConfig = LootbagUtilities.genDestroyList(config, () -> false);
            // doesn't mess with right clicks on a few miscellaneous objects
            assertOptionsUnchanged(rightClickNotedEmeralds, removeConfig);
            assertOptionsUnchanged(rightClickGuard, removeConfig);
            assertOptionsUnchanged(rightClickTreeAndSkeleton, removeConfig);
            assertOptionsUnchanged(rightClickInventoryEek, removeConfig);
            assertOptionsUnchanged(rightClickEquippedEek, removeConfig);
            assertOptionsUnchanged(shiftRightClickPlankSack, removeConfig);

            // Properly removes the destroy option and only the destroy option
            MenuEntry[] modifiedRightClickLootingBag = LootbagUtilities.removeDestroy(rightClickLootingBag, removeConfig);
            if (removeLootingBagDestroy) {
                assertEquals(rightClickLootingBag.length - 1, modifiedRightClickLootingBag.length);
                assertNoDestroyOption(modifiedRightClickLootingBag);
            } else {
                assertArrayEquals(rightClickLootingBag, modifiedRightClickLootingBag);
            }

            MenuEntry[] modifiedRightClickRunePouch =  LootbagUtilities.removeDestroy(rightClickRunePouch, removeConfig);
            if (removeRunePouchDestroy) {
                assertEquals(rightClickRunePouch.length - 1, modifiedRightClickRunePouch.length);
                assertNoDestroyOption(modifiedRightClickRunePouch);
            } else {
                assertArrayEquals(rightClickRunePouch, modifiedRightClickRunePouch);
            }

            MenuEntry[] modifiedRightClickSeedBox = LootbagUtilities.removeDestroy(rightClickSeedBox, removeConfig);
            if (removeSeedBoxDestroy) {
                assertEquals(rightClickSeedBox.length - 1, modifiedRightClickSeedBox.length);
                assertNoDestroyOption(modifiedRightClickSeedBox);
            } else {
                assertArrayEquals(rightClickSeedBox, modifiedRightClickSeedBox);
            }
        }
    }

    @Test
    public void testConsumeEvent() {
        //TODO: test LootBagUtilities.consumeEvent
    }
}
