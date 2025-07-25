package org.yagamipaul.alife.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import org.yagamipaul.alife.MainGameApplication;

public class MainMenuScreen implements Screen {

    final MainGameApplication game;

    OrthographicCamera camera;

    public MainMenuScreen(MainGameApplication game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.4f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);

        game.getBatch().begin();
        game.font.draw(game.getBatch(), "Artificial Life Simulation ", 100, 150);
        game.font.draw(game.getBatch(), "Jean Paul Manjarres C.", 100, 100);
        // game.font.draw(game.getBatch(), "x:"+camera.viewportHeight+" - y:"+camera.viewportWidth, 100, 50);
        game.getBatch().end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}
}
