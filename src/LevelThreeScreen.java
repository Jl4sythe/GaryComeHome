import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class LevelThreeScreen extends GameScreen implements Screen {

    public LevelThreeScreen(GaryGame game){
        super(game);
        super.setBackground(new Texture(Gdx.files.internal("assets/LevelThreeBackground.png")));
        super.addPlatform(new MovingPlatform(1, Constants.WORLD_HEIGHT/3 - 30, 300, 10, 200));
        super.addPlatform(new MovingPlatform(Constants.WORLD_WIDTH-300,2 *Constants.WORLD_HEIGHT/3 - 30, 300, 10, -200));
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
