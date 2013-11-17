package de.licentiouscreativity.theodorsadventures.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.licentiouscreativity.theodorsadventures.TheodorsAdventures;

public abstract class AbstractScreen implements Screen {

	protected final Game game;
	protected final OrthographicCamera camera;
	protected final SpriteBatch batch;

	public AbstractScreen(final Game game) {
		this.game = game;
		
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();
	}

	@Override
	public void show() {
		Gdx.app.log(TheodorsAdventures.LOG, "Showing screen: " + getName());
	}

	@Override
	public void resize(final int width, final int height) {
		Gdx.app.log(TheodorsAdventures.LOG, "Resizing screen: " + getName() + " to: " + width + " x " + height);
	}

	@Override
	public void render(final float delta) {
		// the following code clears the screen with the given RGB color (black)
		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
        camera.apply(Gdx.gl10);
        batch.setProjectionMatrix(camera.combined);
	}

	@Override
	public void hide() {
		Gdx.app.log(TheodorsAdventures.LOG, "Hiding screen: " + getName());
	}

	@Override
	public void pause() {
		Gdx.app.log(TheodorsAdventures.LOG, "Pausing screen: " + getName());
	}

	@Override
	public void resume() {
		Gdx.app.log(TheodorsAdventures.LOG, "Resuming screen: " + getName());
	}

	@Override
	public void dispose() {
		Gdx.app.log(TheodorsAdventures.LOG, "Disposing screen: " + getName());

		// dispose the collaborators
	}

	protected String getName() {
		return getClass().getSimpleName();
	}
}
