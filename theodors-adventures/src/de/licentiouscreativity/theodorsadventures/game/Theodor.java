package de.licentiouscreativity.theodorsadventures.game;

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
			ingredient.update(delta);
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
}
