package de.licentiouscreativity.theodorsadventures.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

import de.licentiouscreativity.theodorsadventures.screens.AbstractScreen;
import de.licentiouscreativity.theodorsadventures.screens.GameScreen;
import de.licentiouscreativity.theodorsadventures.screens.GameScreen.GameState;

public class Input implements InputProcessor{
	
	private final GameScreen screen;
	private final Theodor theodor;
	private final Button buttonMoveRight, buttonMoveLeft, buttonJump, buttonPause, buttonResume;

	private final float screenWidth, screenHeight;
	
	
	public Input(final GameScreen screen, final Theodor theodor, final Button buttonMoveRight, final Button buttonMoveLeft, final Button buttonJump, final Button buttonPause, final Button buttonResume) {
		this.screen = screen;
		this.theodor = theodor;
		this.buttonMoveRight = buttonMoveRight;
		this.buttonMoveLeft = buttonMoveLeft;
		this.buttonJump = buttonJump;
		this.buttonPause = buttonPause;
		this.buttonResume = buttonResume;
	
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
	}
	
	@Override
	public boolean keyDown(int keycode) {
		
		if (keycode == Keys.RIGHT) {
			theodor.moveRight();
			return true;
		}
		
		if (keycode == Keys.LEFT) {
			theodor.moveLeft();
			return true;
		}
		
		if (keycode == Keys.SPACE) {
			theodor.jump();
			return true;
		}
		
		//activate pausemenu on a desktop
		if (keycode == Keys.ESCAPE) {
			System.out.println("Button Escape was pressed!");
			if (screen.returnGameState() == GameState.Play)
				screen.pause();
			else if (screen.returnGameState() == GameState.Pause)
				screen.resume();
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		
		if (keycode == Keys.RIGHT) {
			if (Gdx.input.isKeyPressed(Keys.LEFT)) {
				theodor.moveLeft();
			} else {
				theodor.stopMove();
			}
			return true;
		}
		
		if (keycode == Keys.LEFT) {
			if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
				theodor.moveRight();
			} else {
				theodor.stopMove();
			}
			return true;
		}
		
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		float x = screenX*(800f/screenWidth);
		float y = screenY*(480f/screenHeight);
		
		//buttonMoveRight
		if (buttonMoveRight.touchDown(x, y, pointer)) {
			theodor.moveRight();
			return true;
		}
		
		//buttonMoveLeft
		if (buttonMoveLeft.touchDown(x, y, pointer)) {
			theodor.moveLeft();
			return true;
		}
		
		//buttonJump
		if (buttonJump.touchDown(x, y, pointer)) {
			theodor.jump();
			return true;
		}
		
		//buttonPause
		if (buttonPause.touchDown(x, y, pointer)) {
			System.out.println("pause-button was touched!");
			screen.pause();
			return true;
		}
		
		//buttonResume
		if (buttonResume.touchDown(x, y, pointer))
		{
			System.out.println("resume-button was touched!");
			screen.resume();
			return true;
		}
		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		float x = screenX*(800f/screenWidth);
		float y = screenY*(480f/screenHeight);
		
		//buttonMoveRight
		if (buttonMoveRight.touchUp(x, y, pointer)) {
			if (buttonMoveLeft.isPressed()) {
				theodor.moveLeft();
			} else {
				theodor.stopMove();
			}
			
			return true;
		}

		//buttonMoveLeft
		if (buttonMoveLeft.touchUp(x, y, pointer)) {
			if (buttonMoveRight.isPressed()) {
				theodor.moveRight();
			} else {
				theodor.stopMove();
			}
			
			return true;
		}
		
		//buttonJump just for the image
		if (buttonJump.touchUp(x, y, pointer)) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		float x = screenX*(800f/screenWidth);
		float y = screenY*(480f/screenHeight);
		
		//ButtonMoveRight
		if (theodor.isMoveRight()) {
			if (buttonMoveRight.touchDraggedOff(x, y, pointer)) {
				if (buttonMoveLeft.isPressed()) {
					theodor.moveLeft();
				} else {
					theodor.stopMove();
				}
				
				return true;
			}
		}
		
		//ButtonMoveLeft
		if (theodor.isMoveLeft()) {
			if (buttonMoveLeft.touchDraggedOff(x, y, pointer)) {
				if (buttonMoveRight.isPressed()) {
					theodor.moveRight();
				} else {
					theodor.stopMove();
				}
				
				return true;
			}
		}
		
		//buttonJump just for the image
		if (buttonJump.touchDraggedOff(x, y, pointer)) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
