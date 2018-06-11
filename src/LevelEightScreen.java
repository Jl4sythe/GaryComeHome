import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class LevelEightScreen extends GameScreen implements Screen {

    public LevelEightScreen(GaryGame game){
        super(game);
        super.setBackground(new Texture(Gdx.files.internal("assets/LevelEightBackground.png")));
        super.addPlatform(new Platform(Constants.WORLD_WIDTH/2 - 500, Constants.WORLD_HEIGHT/3 - 30, 300, 10));
        super.addPlatform(new Platform(Constants.WORLD_WIDTH/2 + 200, Constants.WORLD_HEIGHT/3 - 30, 300, 10));
        super.addPlatform(new Platform(Constants.WORLD_WIDTH/2 - 150, 2 * Constants.WORLD_HEIGHT/3 - 30, 300, 10));
        super.addPlatform(new ImpassablePlatform(0,0,Constants.WORLD_WIDTH, 10));
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }


    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
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
    public void hide() {
        super.hide();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
