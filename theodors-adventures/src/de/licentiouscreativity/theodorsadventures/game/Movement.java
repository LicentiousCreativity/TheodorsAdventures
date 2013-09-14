package de.licentiouscreativity.theodorsadventures.game;

import com.badlogic.gdx.math.Circle;

public class Movement {
	
	public final Circle positionKey;
	public final String movement;
	
	public Movement(final Circle positionKey, final String movement) {
		this.positionKey = positionKey;
		this.movement = movement;
	}
}
