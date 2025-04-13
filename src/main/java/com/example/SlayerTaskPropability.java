package com.example;

import com.google.inject.Provides;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
	name = "Slayer Task Propability"
)
public class SlayerTaskPropability extends Plugin
{
	
	@Inject
	private Client client;

	@Inject
	private ExampleConfig config;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Example started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Example stopped!");
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Example says " + config.greeting(), null);
		}
	}

	@Provides
	ExampleConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(ExampleConfig.class);
	}
	/**
	 * Map of the most desireable tasks 
	 */
	public static final Map<String, Double> slayerTaskPropabiliesMap = map.of(
		"Abyssal Demons", 0.05,
		"Nechryael", 0.03,
		"Greater Demons", 0.08,
		"Dust Devils", 0.07,
		"Smoke Devils", 0.02,
		"Kraken", 0.01,
		"Hydra", 0.015,
		"Cerberus", 0.012,
		"Dagannoth Kings", 0.025,
		"Lizardman Shaman", 0.018,
		"Skeletal Wyverns", 0.04
	);
	/**
	 * 
	 * @param event
	 */
	@Subscribe
	public void onAssignedSlayerTask(SlayerTaskAssigned event) {
		String assignedTask = event.getTask().getName();
		double propability = slayerTaskPropabiliesMap.getOrDefault(event, 0.0);
		if(propability > 0) {
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "The propability of recieving this task was: " + propability, null);
		}
	}

	/**
	 * List of the best/most desireable slayer tasks
	 * @return List<String>
	 */
	public static final List<String> listOfBestSlayerTasks = list.of(
		"Abyssal Demons", "Nechryaels", "Dust Devils", "Smoke Devils", "Black Demons", "Araxxyte",
		"Kraken", "Hydra", "Cerberus", "Dagannoth Kings",
    	"Smoke Devils", "Lizardman Shaman", "Skeletal Wyverns", "Araxxor"
	);
	
}
