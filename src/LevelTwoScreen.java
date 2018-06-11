import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class LevelTwoScreen extends GameScreen implements Screen {

    public LevelTwoScreen(GaryGame game){
        super(game);
        super.setBackground(new Texture(Gdx.files.internal("assets/LevelTwoBackground.png")));
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
