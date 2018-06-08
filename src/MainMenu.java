import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.ApplicationAdapter;
import sun.awt.image.GifImageDecoder;


public class MainMenu implements Screen {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private BitmapFont font;
    private Texture menuBackground;
    private ShapeRenderer renderer;
    private Texture button;
    private Texture button2;
    private Texture word; 
    private Vector2 pos;

    private GaryGame game;


    public MainMenu(GaryGame game) {
        this.game = game;

    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(Constants.WORLD_WIDTH,Constants.WORLD_HEIGHT,camera);
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("assets/KarbyParty.fnt"));
        renderer = new ShapeRenderer();
        button = new Texture(Gdx.files.internal("assets/Button.png"));
        button2 = new Texture(Gdx.files.internal("assets/ButtonState2.png"));
        word = new Texture(Gdx.files.internal("assets/play word.png"));
        menuBackground = new Texture(Gdx.files.internal("assets/menuBackground.jpg"));
        pos = new Vector2(0,0);
    }

    @Override
    public void render(float delta)
    {//delta = 1.0/60.0
        Gdx.gl.glClearColor(0,0,.2f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        batch.draw(menuBackground,0,0,Constants.WORLD_WIDTH,Constants.WORLD_HEIGHT);
        batch.draw(button,Constants.WORLD_WIDTH - 320,Constants.WORLD_HEIGHT - 180,300,100);
        batch.draw(button,Constants.WORLD_WIDTH - 320,Constants.WORLD_HEIGHT - 430,300,100);

        int x = Gdx.input.getX();
        int y = Gdx.input.getY();
        pos = viewport.unproject(new Vector2(x,y));
        if(pos.x >= Constants.WORLD_WIDTH - 320 && pos.x < Constants.WORLD_WIDTH - 20 && pos.y >= Constants.WORLD_HEIGHT - 180 && pos.y <= Constants.WORLD_HEIGHT - 80)
            batch.draw(button2,Constants.WORLD_WIDTH - 320,Constants.WORLD_HEIGHT - 180,300,100);
        if(pos.x >= Constants.WORLD_WIDTH - 320 && pos.x < Constants.WORLD_WIDTH - 20 && pos.y >= Constants.WORLD_HEIGHT - 430 && pos.y <= Constants.WORLD_HEIGHT - 330)
            batch.draw(button2,Constants.WORLD_WIDTH - 320,Constants.WORLD_HEIGHT - 430,300,100);

        GlyphLayout layout = new GlyphLayout(font, "PLAY");
        font.draw(batch, "PLAY", Constants.WORLD_WIDTH - layout.width/2 -170,Constants.WORLD_HEIGHT + layout.height/2 - 130);
        GlyphLayout layout2 = new GlyphLayout(font, "QUIT");
        font.draw(batch, "QUIT", Constants.WORLD_WIDTH - layout2.width/2 -170,Constants.WORLD_HEIGHT + layout2.height/2 - 380);
        font.draw(batch, "GARY SMASH BROS.", 100, 100);

        batch.end();

        if(Gdx.input.justTouched())
        {
            x = Gdx.input.getX();
            y = Gdx.input.getY();

            pos = viewport.unproject(new Vector2(x,y));
            if(pos.x >= Constants.WORLD_WIDTH - 320 && pos.x < Constants.WORLD_WIDTH - 20 && pos.y >= Constants.WORLD_HEIGHT - 180 && pos.y <= Constants.WORLD_HEIGHT - 80)
                game.setScreen(new SelectionScreen(game));
            if(pos.x >= Constants.WORLD_WIDTH - 320 && pos.x < Constants.WORLD_WIDTH - 20 && pos.y >= Constants.WORLD_HEIGHT - 430 && pos.y <= Constants.WORLD_HEIGHT - 330)
                System.exit(0);
        }
    }

    @Override
    public void resize(int width, int height)
    {
        viewport.update(width, height, true);
    }


    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose()
    {
        renderer.dispose();
        batch.dispose();
        font.dispose();

    }
}

//        [Verse 1]
//        Gary, now I know I was wrong
//        I messed up and now you're gone
//        Gary, I'm sorry I neglected you
//        Oh, I never expected you to run away
//        And leave me feeling this empty
//        Your meow right now would sound like music to me
//        Please come home 'cause I miss you, Gary
//
//        [Chorus]
//        Gary, come home
//        Gary, come home
//        Gary, come home
//
//        [Verse 2]
//        Gary, can't you see I was blind?
//        I'll do anything to change your mind
//        More than a pet, you're my best friend
//        Too cool to forget, come back 'cause we are family
//        And forgive me for making you wanna roam
//        And now, my heart is beating like the saddest metronome
//        Somewhere I hope you're reading my latest three-word poem
//
//        [Chorus]
//        Gary, come home
//        Gary, come home
//        Gary, come home
//        Gary, come home
//        Gary, come home
//        Gary, come home
//        Gary, come home
//        Gary, come home
//        Gary, come home
//        Gary, come home
//        Gary, won't you come home?