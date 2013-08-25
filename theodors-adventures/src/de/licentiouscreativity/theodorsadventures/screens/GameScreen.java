package de.licentiouscreativity.theodorsadventures.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.math.Rectangle;

import de.licentiouscreativity.theodorsadventures.Button;
import de.licentiouscreativity.theodorsadventures.Input;
import de.licentiouscreativity.theodorsadventures.Map;
import de.licentiouscreativity.theodorsadventures.Theodor;

public class GameScreen extends AbstractScreen{

	private final BitmapFontCache font;
	
	//Buttons
	private final Button buttonMoveRight, buttonMoveLeft, buttonJump;
	
	private final Map map;
	private final Theodor theodor;
	
	public GameScreen(final Game game) {
		super(game);
		
		font = new BitmapFontCache(new BitmapFont());
		
		buttonMoveRight = new Button("data/button.png", "data/button.png", 200f, 350f);
		buttonMoveLeft = new Button("data/button.png", "data/button.png", 50f, 350f);
		buttonJump = new Button("data/button.png", "data/button.png", 600f, 350f);
		
		
		map = new Map();
		theodor = new Theodor(150, 200, 80, -400, new Rectangle(), new Rectangle(), "data/character/tests/kreis.png", batch); //TODO add walkSheetName
		
		Gdx.input.setInputProcessor(new Input(theodor, buttonMoveRight, buttonMoveLeft, buttonJump));
	}

	
	@Override
	public void resize(final int width, final int height) {
		super.resize(width, height);
	}
	
	@Override
	public void render(final float delta) {
		super.render(delta);
		
		update(delta);
		
		batch.begin();
		
		buttonMoveLeft.render(batch);
		buttonMoveRight.render(batch);
		buttonJump.render(batch);
		
		map.render();
		theodor.render();
		
		batch.end();
	}
	
	private void update(final float delta) {
		theodor.update(delta);
		map.update(delta);
	}
	
	@Override
	public void show() {
		super.show();
	}
	
	@Override
	public void hide() {
		super.hide();
	}
	
	@Override
	public void pause() {
		super.pause();
	}
	
	@Override
	public void resume() {
		super.resume();
	}
	
	@Override
	public void dispose() {
		super.dispose();
		
		batch.dispose();
	}
}
