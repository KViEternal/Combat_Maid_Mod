package org.KViEternal.combat_maid.client;

import io.netty.util.internal.logging.Log4JLoggerFactory;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Combat_MaidClient implements ClientModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("combat_maid");

    @Override
    public void onInitializeClient() {
        LOGGER.info("Combat Maids mod successfully initialized! (Client-Side)");
    }
}
