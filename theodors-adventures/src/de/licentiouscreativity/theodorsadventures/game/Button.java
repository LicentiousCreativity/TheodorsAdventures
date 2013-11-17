package de.licentiouscreativity.theodorsadventures.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

//TODO Touch funktioniert nach y Umkehrung nicht mehr
public class Button {
	
	private int pointer;
	private float posX, posY;
	private boolean pressed, touchAble;
	private Texture up, down;
	
	public final Rectangle bounds;
	
	public Button(final String up, final String down, final float posX, final float posY) {
		pointer = -1;
		this.posX = posX;
		this.posY = posY;
		pressed = false;
		touchAble = true;
		this.up = new Texture(up);
		this.down = new Texture(down);
		
		bounds = new Rectangle(posX, posY, this.up.getWidth(), this.up.getHeight());
	}
	
	public Button(final String up, final String down, final float posX, final float posY, final boolean touchAble) {
		pointer = -1;
		this.posX = posX;
		this.posY = posY;
		pressed = false;
		this.touchAble = touchAble;
		this.up = new Texture(up);
		this.down = new Texture(down);
		
		bounds = new Rectangle(posX, posY, this.up.getWidth(), this.up.getHeight());
	}
	
	public void render(SpriteBatch batch) {
		if (!pressed) {
			batch.draw(up, posX, posY);
		} else if (pressed) {
			batch.draw(down, posX, posY);
		}
	}
	
	public boolean touchDown(final float x, final float y, final int pointer) {
		if (bounds.contains(x, y) && touchAble) {
			pressed = true;
			this.pointer = pointer;
			return true;
		}
		return false;
	}
	
	public boolean touchUp(final float x, final float y, final int pointer) {
		if (this.pointer == pointer && touchAble) {
			pressed = false;
			this.pointer = -1;
			return true;
		}
		
		return false;
	}
	
	public boolean touchDraggedOff(final float x, final float y, final int pointer) {
		if (!bounds.contains(x, y) && this.pointer == pointer && touchAble) {
			pressed = false;
			this.pointer = -1;
			return true;
		}
		
		return false;
	}

	public int getPointer() {
		return pointer;
	}

	public float getPosX() {
		return posX;
	}

	public float getPosY() {
		return posY;
	}
	
	public boolean isPressed() {
		return pressed;
	}
	
	public boolean isTouchAble() {
		return touchAble;
	}

	public void setPointer(int pointer) {
		this.pointer = pointer;
	}

	public void setPosX(float posX) {
		this.posX = posX;
	}

	public void setPosY(float posY) {
		this.posY = posY;
	}

	public void setPressed(boolean pressed) {
		this.pressed = pressed;
	}
	
	public void setTouchAble(boolean touchAble) {
		this.touchAble = touchAble;
	}
}
