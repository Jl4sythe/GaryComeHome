import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class LevelFourScreen extends GameScreen implements Screen {

    private float timer;


    public LevelFourScreen(GaryGame game){
        super(game);
        super.setBackground(new Texture(Gdx.files.internal("assets/LevelFourBackground.png")));
//        super.addPlatform(new Platform(Constants.WORLD_WIDTH/2 - 500, Constants.WORLD_HEIGHT/3 - 30, 300, 10));
//        super.addPlatform(new Platform(Constants.WORLD_WIDTH/2 + 200, Constants.WORLD_HEIGHT/3 - 30, 300, 10));
        super.addPlatform(new Platform(Constants.WORLD_WIDTH/2 - 150, 2 * Constants.WORLD_HEIGHT/3 - 30, 300, 10));
        super.addPlatform(new VerticalMovingPlatform(10, 0,300, 10, 150));
        super.addPlatform(new VerticalMovingPlatform(Constants.WORLD_WIDTH - 310, Constants.WORLD_HEIGHT - Constants.GARY_HEIGHT,300, 10, -150));
        timer = 0;
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        timer += delta;
        if(timer >= 3f){
            super.addPlatform(new VerticalMovingPlatform(10, -10,300, 10, 150));
            super.addPlatform(new VerticalMovingPlatform(Constants.WORLD_WIDTH - 310, Constants.WORLD_HEIGHT,300, 10, -150));
            timer =0;
        }

        for (int i = 0; i < GameScreen.getPlatforms().size() ; i++) {
            if((GameScreen.getPlatforms().get(i).getRectangle().y > Constants.WORLD_HEIGHT)|| GameScreen.getPlatforms().get(i).getRectangle().y < -10)
                GameScreen.getPlatforms().remove(i);
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
