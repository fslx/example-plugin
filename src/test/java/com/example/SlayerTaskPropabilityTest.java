package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class SlayerTaskPropabilityTest
{
	public static void main(String[] args) throws Exception
	{
		final SlayerTaskPropability slayerTaskPropabilityObject;
		private static final Map<String, Double> slayerTaskPropabilies = Map.of(
			"Abyssal Demons", 0.05,
			"Nechryael", 0.03,
			"Greater Demons", 0.08,
			"Dust Devils", 0.08
		);

		@Test
		void testValidTaskPropability() {
			assertEquals(0.05, slayerTaskPropabilies.get("Abyssal Demons"));
			assertEquals(0.07, slayerTaskPropabilies.get("Dust Devils"));
		}
		@Test
		void testInvalidTaskReturnNull() {
			assertNull(slayerTaskPropabilies.get("Random Task"));
		}

		@Test
		void testIfBestTaskListContainsCorrectTasks() {
			assertTrue(slayerTaskPropabilityObject.listOfBestSlayerTasks.contains("Abyssal Demons"));
			assertTrue(slayerTaskPropabilityObject.listOfBestSlayerTasks.contains("Araxxor"));
		}
		@Test
		void testThatBestTaskListDoesNotContainBadTasks() {
			assertFalse(slayerTaskPropabilityObject.listOfBestSlayerTasks.contains("Fire Giants"));
			assertFalse(slayerTaskPropabilityObject.listOfBestSlayerTasks.contains("Kraken"));
		}


		ExternalPluginManager.loadBuiltin(SlayerTaskPropability.class);
		RuneLite.main(args);
	}
}