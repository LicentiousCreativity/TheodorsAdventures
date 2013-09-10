package de.licentiouscreativity.theodorsadventures.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.math.Rectangle;

import de.licentiouscreativity.theodorsadventures.game.Button;
import de.licentiouscreativity.theodorsadventures.game.Input;
import de.licentiouscreativity.theodorsadventures.game.Map;
import de.licentiouscreativity.theodorsadventures.game.Theodor;

public class GameScreen extends AbstractScreen{

	private final BitmapFontCache font;
	
	private enum GameState {
		Play, Pause, GameOver;
	}
	
	private static GameState gameState = GameState.Play;
	
	//Buttons
	private final Button buttonMoveRight, buttonMoveLeft, buttonJump, buttonPause, buttonResume, buttonExit;
	
	private final Map map;
	private final Theodor theodor;
	
	public GameScreen(final Game game) {
		super(game);
		
		font = new BitmapFontCache(new BitmapFont());
		
		// Gameplay buttons
		
		buttonMoveRight = new Button("data/button.png", "data/button.png", 200f, 350f);
		buttonMoveLeft = new Button("data/button.png", "data/button.png", 50f, 350f);
		buttonJump = new Button("data/button.png", "data/button.png", 600f, 350f);
		buttonPause = new Button("data/pause.png", "data/pause.png", 0f, 0f);
		
		// Pausescreen buttons
		buttonResume = new Button("data/play.png", "data/play.png", 0f, 0f);
		buttonExit = new Button("data/exit.png", "data/exit.png", 350f, 220f);
		
		map = new Map();
		theodor = new Theodor(150, 200, 80, -400, new Rectangle(), new Rectangle(), "data/character/tests/kreis.png", batch); //TODO add walkSheetName
		
		Gdx.input.setInputProcessor(new Input(theodor, buttonMoveRight, buttonMoveLeft, buttonJump, buttonPause, buttonResume));
	}

	
	@Override
	public void resize(final int width, final int height) {
		super.resize(width, height);
	}
	
	@Override
	public void render(final float delta) {
		super.render(delta);
		if (gameState == GameState.Play)
		{
			update(delta);
		}
		batch.begin();
		
		buttonMoveLeft.render(batch);
		buttonMoveRight.render(batch);
		buttonJump.render(batch);
		buttonPause.render(batch);
		
		map.render();
		theodor.render();

		if (gameState == GameState.Pause)
		{
			Gdx.gl.glClearColor(255, 0, 255, 0f);
		    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
			buttonResume.render(batch);
			buttonExit.render(batch);
		}
		
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
		gameState = GameState.Pause;
	}
	
	// pause game on pc's
	public static void pauseGame()
	{
		gameState = GameState.Pause;
		System.out.println("Game stopped!");
	}
	
	@Override
	public void resume() {
		super.resume();
		gameState = GameState.Play;
	}
	
	// resume to game on pc's
	public static void resumeGame()
	{
		gameState = GameState.Play;
		System.out.println("Resume to game!");
	}
	
	@Override
	public void dispose() {
		super.dispose();
		
		batch.dispose();
	}
	
	/*
	 * return current gameState value as int:
	 * 1 = Play
	 * 2 = Pause
	 * 3 = GameOver
	 */
	public static int returnGameState()
	{
		int gameStateValue = 1;
		
		if (gameState == GameState.Play)
		{
			gameStateValue=1;
		}
		else if (gameState == GameState.Pause)
		{
			gameStateValue=2;
		}
		else if (gameState == GameState.GameOver)
		{
			gameStateValue=3;
		}
		
		return gameStateValue;
	}
}
