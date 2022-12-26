package com.dm.earth.cabricality.util;

import net.minecraft.client.gui.screen.Screen;

import org.jetbrains.annotations.Nullable;
import org.quiltmc.qsl.lifecycle.api.client.event.ClientTickEvents;

public class ScreenUtil {
	@Nullable
	private static Screen nextScreen = null;

	public static void openScreenInWorld(@Nullable Screen screen) {
		openScreenInWorld(screen, true);
	}

	public static void openScreenInWorld(@Nullable Screen screen, boolean replaceCurrent) {
		if (replaceCurrent || nextScreen == null) nextScreen = screen;
	}

	public static void registerEvents() {
		ClientTickEvents.START.register(client -> {
			if (client.world != null && client.currentScreen == null && nextScreen != null) {
				client.setScreen(nextScreen);
				nextScreen = null;
			}
		});
	}
}
