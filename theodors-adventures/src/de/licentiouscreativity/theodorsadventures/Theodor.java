package de.licentiouscreativity.theodorsadventures;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Theodor extends Entity{

	private final static int DISTANCE = 150;
	
	private boolean ingFilled;
	
	private final List<Ingredient> ingredients;
	
	public Theodor(final int posX, final int posY, final int moveSpeed, final int jumpSpeed, Rectangle bounceHead, Rectangle bounceBody, String walkSheetName, SpriteBatch batch) {
		super(posX, posY, moveSpeed, jumpSpeed, bounceHead, bounceBody, walkSheetName, batch);
		
		ingFilled = false;
		
		ingredients = new ArrayList<Ingredient>();
		ingredients.add(new Ingredient(posX, posY, moveSpeed, jumpSpeed, DISTANCE, 1, bounceHead, bounceBody, walkSheetName, batch));
	}
	
	@Override
	public void update(float delta) {
		super.update(delta);
		
		if (ingredients.size() > 0) {
			ingFilled = true;
		} else {
			ingFilled = false;
		}
		
		//update ingredients
		for (Ingredient ingredient : ingredients) {
			ingredient.update(delta, posX);
		}
		
		//move map
	}
	
	@Override
	public void render() {
		super.render();
		
		//render ingredients
		for (Ingredient ingredient : ingredients) {
			ingredient.render();
		}
	}
	
	@Override
	public void moveLeft() {
		super.moveLeft();	
		addIngredientMovement("left", 0);
	}
	
	@Override
	public void moveRight() {
		super.moveRight();
		addIngredientMovement("right", 0);
	}
	
	@Override
	public void jump() {
		super.jump();
		addIngredientMovement("jump", 0);
	}
	
	@Override
	public void stopMove() {
		super.stopMove();
		
		int distance = 0;
		
		if (ingFilled) {
			if (ingredients.get(0).isMoveRight()) {
				distance = -DISTANCE + 1;
			} else if (ingredients.get(0).isMoveLeft()) {
				distance = DISTANCE - 1;
			}
		}
		addIngredientMovement("stop", distance);
	}

	/**
	 * gives all Ingredients a new movement with the delay of distance
	 * @param movement
	 * @param distance
	 */
	private void addIngredientMovement(String movement, int distance) {
		if (ingFilled) {
			for (Ingredient ingredient : ingredients) {
				System.out.println(posX + ":" + posY + "-" + movement);
				ingredient.addMovement(posX, posY, distance, movement);
				
			}
		}
	}
	
	
}
