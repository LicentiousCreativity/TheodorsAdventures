package de.licentiouscreativity.theodorsadventures;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Enemy extends Entity{

	public Enemy(final int posX, final int posY, final int moveSpeed, final int jumpSpeed, final Rectangle bounceHead, final Rectangle bounceBody, final String walkSheetName, final SpriteBatch batch) {
		super(posX, posY, moveSpeed, jumpSpeed, bounceHead, bounceBody, walkSheetName, batch);
	}
}
