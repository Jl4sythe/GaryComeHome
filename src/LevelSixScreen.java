import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class LevelSixScreen extends GameScreen implements Screen {

    private float timer;

    public LevelSixScreen(GaryGame game){
        super(game);
        super.setBackground(new Texture(Gdx.files.internal("assets/LevelSixBackground.png")));
        super.addPlatform(new VerticalMovingPlatform(Constants.WORLD_WIDTH/2 - 500, Constants.WORLD_HEIGHT - 30, 300, 10,-150));
        super.addPlatform(new VerticalMovingPlatform(Constants.WORLD_WIDTH/2 + 200, Constants.WORLD_HEIGHT - 30, 300, 10,-150));
        super.addPlatform(new VerticalMovingPlatform(Constants.WORLD_WIDTH/2 - 150, 0, 300, 10,200));
        timer = 0;
    }

    @Override
    public void show() {
        super.show();
        GaryCharacter.getGary().y = Constants.WORLD_HEIGHT;
        EnemyCharacter.getEnemy().y = Constants.WORLD_HEIGHT;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        if (timer >= 1){
            super.addPlatform(new VerticalMovingPlatform(Constants.WORLD_WIDTH/2 - 500, Constants.WORLD_HEIGHT - 30, 300, 10,-150));
            super.addPlatform(new VerticalMovingPlatform(Constants.WORLD_WIDTH/2 + 200, Constants.WORLD_HEIGHT - 30, 300, 10,-150));
            super.addPlatform(new VerticalMovingPlatform(Constants.WORLD_WIDTH/2 - 150, 0, 300, 10,200));
            timer = 0;
        }
        for (int i = 0; i < GameScreen.getPlatforms().size(); i++) {
            if(GameScreen.getPlatforms().get(i).getRectangle().y < 0)
                GameScreen.getPlatforms().remove(i);
            else if(GameScreen.getPlatforms().get(i).getRectangle().y > Constants.WORLD_HEIGHT)
                GameScreen.getPlatforms().remove(i);
        }
        timer += delta;
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
