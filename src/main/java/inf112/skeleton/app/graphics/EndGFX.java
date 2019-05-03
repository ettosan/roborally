package inf112.skeleton.app.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class EndGFX extends Stage {
    private Sprite menu;
    private Texture menuBack;
    private OrthographicCamera camera;
    private FitViewport viewport;
    private Batch batch;

    public EndGFX() {
        menuBack = new Texture(Gdx.files.internal("assets/menuBack.png"));
        menu = new Sprite(menuBack);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        batch = new SpriteBatch();
        menu.setPosition(10,10);
    }
    public void render(){
        batch.begin();
        menu.draw(batch);
        batch.end();
    }

}
