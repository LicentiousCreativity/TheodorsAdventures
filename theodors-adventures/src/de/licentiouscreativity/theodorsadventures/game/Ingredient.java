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
		
		//TODO funktioniert noch nicht zu 100% (springen!)
		//if the field movement is empty, the other movements will be executed
		if (movement != null) {
			int currentDistance = (int) (theodorX - posX);
			//if the current distance is enough
			if (currentDistance >= _distance || -currentDistance >= _distance) {
				executeMovement(movement);
				//reset movement to execute the other movements
				movement = null;
			}
		} else if (!movements.isEmpty()){
			
			//get the last position
			posX -= dirX * delta;
			posY -= dirY * delta;
			
			//make sure that every positionKey is reached
			for (int i = 0; i < dirX * delta; i++) {
				
				//move the Ingredient 
				posX++;
				
				//one run is needed
				for (int j = 0; j <= dirY * delta; j++) {

					//move the Ingredient if needed (posY)
					if (dirY != 0) {
						posY++;
					}
					
					Vector2 positionKey = new Vector2(posX, posY);
					
					if (movements.containsKey(positionKey)) {
						final String movement = movements.get(positionKey);
						System.out.println(positionKey.toString());
						executeMovement(movement);
						
						movements.remove(positionKey);
					}
				}
			}
		}
	}
	
	public void addMovement(final float x, final float y, final int distance, final String movement) {
		//if is moving put the movement in the List
		if (dirX != 0) {
			movements.put(new Vector2(x + distance, y), movement);
			System.out.println("-put-" + x + distance + ":" + y);
		//else execute the movement with the right distance
		} else {
			this.movement = movement;
		}
	}
	
	private void executeMovement(final String movement) {
		if (movement == "right") {
			moveRight();
		} else if (movement == "left") {
			moveLeft();
		} else if (movement == "jump") {
			jump();
		} else if (movement == "stop") {
			stopMove();
		}
	}
}
