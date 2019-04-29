package inf112.skeleton.app.graphics;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

@SuppressWarnings("Since15")
public class GFX extends ApplicationAdapter {
    private boolean createGFX;
    private Menu menu;
    private GameGFX gameGFX;

    public void create() {
        createGFX = true;
        menu = new Menu();
        gameGFX = new GameGFX();
        Gdx.input.setInputProcessor(menu);

    }

    @Override
    public void render() {
        if(menu.isMenuActive()) {
            menu.render();
        }
        else if(createGFX){
            menu.dispose();
            gameGFX.create(menu.getNumberOfRealPlayers(), menu.getNumbersOfAI(), menu.getTiledMap());
            createGFX = false;
            }
        else {
            gameGFX.render();
            Gdx.input.setInputProcessor(gameGFX);
        }
    }
}
/**
 * VIKTIG!
 * Menyen sender per nå kun antall ekte spillere videre.
 *
 */

