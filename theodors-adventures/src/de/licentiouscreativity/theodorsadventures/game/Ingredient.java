package de.licentiouscreativity.theodorsadventures.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

public class Ingredient extends Entity{
	
	//Distance between the Player and the Ingredient
	//Number of the Ingredient in the HashMap
	private final int _distance, _number;
	
	private final ArrayList<Movement> movements;
	private String movement;
	
	public Ingredient(final int posX, final int posY, final int moveSpeed, final int jumpSpeed, final int distance, final int _number, final Rectangle bounceHead, final Rectangle bounceBody, final String walkSheetName, final SpriteBatch batch) {
		super(posX, posY, moveSpeed, jumpSpeed, bounceHead, bounceBody, walkSheetName, batch);
		
		this._number = _number;
		this._distance = _number * distance;
		
		movements = new ArrayList<Movement>();
		movement = null;
	}
	
	public void update(final float delta, final float theodorX) {
		super.update(delta);
		//TODO Springen funktioniert noch nicht gut
		int currentDistance = (int) (theodorX - posX);
		
		if (!(currentDistance >= _distance || -currentDistance >= _distance)) {
			stopMove();
		
		//if distance gets to big Ingredient goes to Theodor
		} else if (currentDistance >= _distance + 5 || -currentDistance >= _distance - 5) {
			
			if (currentDistance > 0) {
				moveRight();
			} else if (currentDistance < 0) {
				moveLeft();
			}
			
		//if the field movement is empty, the other movements will be executed
		} else if (movement != null) {
			executeMovement(movement);
			//reset movement to execute the other movements
			movement = null;
		} else if(movements.size() > 0) {

			ArrayList<Movement> removeMovements = new ArrayList<Movement>();
			
			for (Movement singleMovement : movements) {
				final Circle positionKey = singleMovement.positionKey;
				
				if (positionKey.contains(posX, posY)) {
					final String movement = singleMovement.movement;
										
					executeMovement(movement);
					
					removeMovements.add(singleMovement);
				}
			}
			
			movements.removeAll(removeMovements);
		}
	}
	
		public void addMovement(final float x, final float y, final float distance, final String movement) {
				if (movement == "jump") {
					movements.add(new Movement(new Circle(x + distance*_number, y, 5), movement));
				} else if (dirX != 0) {
					movements.add(new Movement(new Circle(x + distance*_number, y, 2), movement));
					//System.out.println("add " + movement + " - " + (x+distance));
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
