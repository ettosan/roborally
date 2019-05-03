package inf112.skeleton.app.graphics;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

@SuppressWarnings("Since15")
public class GFX extends ApplicationAdapter {
    private boolean createGFX;
    private Menu menu;
    private GameGFX gameGFX;
    private EndGFX end;

    public void create() {
        createGFX = true;
        menu = new Menu();
        gameGFX = new GameGFX();
        end = new EndGFX();
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
        else if(gameGFX.gameOver()){
            gameGFX.dispose();
            end.render();
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

