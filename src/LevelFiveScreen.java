import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LevelFiveScreen extends GameScreen implements Screen {

    public LevelFiveScreen(GaryGame game){
        super(game);
        super.setBackground(new Texture(Gdx.files.internal("assets/LevelFiveBackground.png")));
        super.addPlatform(new VerticalMovingPlatform(Constants.WORLD_WIDTH/2 - 500, 0, 300, 10, 200));
        super.addPlatform(new VerticalMovingPlatform(Constants.WORLD_WIDTH/2 + 200, 0, 300, 10, 200));
        super.addPlatform(new VerticalMovingPlatform(Constants.WORLD_WIDTH/2 - 150, Constants.WORLD_HEIGHT-10, 300, 10, -200));
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        for (int i = 0; i < GameScreen.getPlatforms().size(); i++) {
            if(GameScreen.getPlatforms().get(i).getRectangle().y > Constants.WORLD_HEIGHT-10 || GameScreen.getPlatforms().get(i).getRectangle().y <= 0) {
                ((VerticalMovingPlatform) GameScreen.getPlatforms().get(i)).setVelocity(-((VerticalMovingPlatform) GameScreen.getPlatforms().get(i)).getVelocity());
                GameScreen.getPlatforms().get(i).updateY(((VerticalMovingPlatform) GameScreen.getPlatforms().get(i)).getVelocity() * delta);
            }
        }
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
