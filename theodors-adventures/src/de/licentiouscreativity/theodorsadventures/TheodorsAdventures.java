package de.licentiouscreativity.theodorsadventures;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;

import de.licentiouscreativity.theodorsadventures.screens.GameScreen;

public class TheodorsAdventures extends Game {
public static final String LOG = TheodorsAdventures.class.getSimpleName();
	
	private final FPSLogger fpsLogger = new FPSLogger();
	
	@Override
	public void create() {		
		Gdx.app.log(TheodorsAdventures.LOG, "Creating Game");
		
		setScreen(new GameScreen(this));
	}

	@Override
	public void render() {		
		super.render();
		
		//fpsLogger.log();
	}

	@Override
	public void resize(final int width, final int height) {
		Gdx.app.log(TheodorsAdventures.LOG, "Resizing game to " + width + " x, " + height);
	}

	@Override
	public void pause() {
		Gdx.app.log(TheodorsAdventures.LOG, "Pausing Game");
	}

	@Override
	public void resume() {
		Gdx.app.log(TheodorsAdventures.LOG, "Resuming Game");
	}
	
	@Override
	public void dispose() {
		Gdx.app.log(TheodorsAdventures.LOG, "Disposing Game");
	}
}
