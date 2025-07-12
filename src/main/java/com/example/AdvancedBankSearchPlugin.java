package com.example;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import net.runelite.api.Client;
import net.runelite.api.events.ScriptCallbackEvent;
import net.runelite.api.widgets.WidgetID;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
    name = "Better Bank Search",
    description = "A improved search filter for the OSRS banks",
    tags = {"bank", "search", "filter"}
)
public class AdvancedBankSearchPlugin extends Plugin {
    @Inject private Client client;
    @Inject private ClientThread clientThread;
    @Inject private AdvancedBankSearchConfig config;

    @Provides
    AdvancedBankSearchConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(AdvancedBankSearchConfig.class);
    }

    @Override
    protected void startup() throws Exception {
        log.info("Better bank search started!");
    }

    @Override
    protected void shutDown() throws Exception {
        log.info("Better bank search stopped!");
    }

    @Subscribe
    public void onScriptCallback(ScriptCallbackEvent event) {
        if(!event.getEventName().equals("bankSearchFilter")){
            return;
        }

        String input = client.getVarcStrValue(VarClientStr.BANK_SEARCH_INPUT);

        
    }
}