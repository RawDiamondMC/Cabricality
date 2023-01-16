package com.dm.earth.cabricality.networking;

import com.dm.earth.cabricality.networking.receiver.HeldItemInfoReceiver;
import org.quiltmc.qsl.networking.api.ServerPlayNetworking;

import static com.dm.earth.cabricality.networking.CabfNetworking.*;

public class CabfReceiver {
	public static void registerClient() {
	}

	public static void registerServer() {
		ServerPlayNetworking.registerGlobalReceiver(HELD_ITEM_INFO, new HeldItemInfoReceiver());
	}
}