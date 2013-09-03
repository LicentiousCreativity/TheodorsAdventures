package de.licentiouscreativity.theodorsadventures.game;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Ingredient extends Entity{
	
	//Distance between the Player and the Ingredient
	//Number of the Ingredient in the HashMap
	private final int _distance, _number;
	
	//The field movement has priority
	private String movement;
	private final HashMap<Vector2, String> movements;
	
	public Ingredient(final int posX, final int posY, final int moveSpeed, final int jumpSpeed, final int distance, final int _number, final Rectangle bounceHead, final Rectangle bounceBody, final String walkSheetName, final SpriteBatch batch) {
		super(posX, posY, moveSpeed, jumpSpeed, bounceHead, bounceBody, walkSheetName, batch);
		
		this._number = _number;
		this._distance = _number*distance;
		
		movement = null;
		movements = new HashMap<Vector2, String>();
	}
	
	public void update(final float delta, final float theodorX) {
		super.update(delta);
		
		
	}
}
