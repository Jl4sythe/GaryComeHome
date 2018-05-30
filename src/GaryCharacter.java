import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class GaryCharacter {
    private Vector2 velocity;
    private ShapeRenderer renderer;
    static boolean facingRight;
    private int health;
    private Texture shell;
    private GaryGame game;
    
    private Texture garyTexR;
    private Texture garyTexL;
    private Rectangle gary;

    private int coolDown = 0;
    private double var = 1;
    private float time = 0;

    public GaryCharacter(GaryGame game) {
        facingRight = true;

        this.game = game;

        this.velocity = new Vector2(0,0);
        garyTexR = new Texture(Gdx.files.internal("assets/garyRight.png"));
        garyTexL = new Texture(Gdx.files.internal("assets/garyLeft.png"));

        gary = new Rectangle();
        gary.x = 0;
        gary.y = 0;
        gary.width = Constants.GARY_WIDTH;
        gary.height = Constants.GARY_HEIGHT;

        renderer = new ShapeRenderer();

        health = 3;
        shell = new Texture(Gdx.files.internal("assets/garyRight.png"));
    }

    public Rectangle getRectangle(){
        return gary;
    }

    public void update(SpriteBatch batch){

        checkHealth();
        updateHealth(batch);

        if(facingRight)
            batch.draw(garyTexR, gary.x, gary.y, gary.width, gary.height);
        else if(!facingRight)
            batch.draw(garyTexL, gary.x, gary.y, gary.width, gary.height);

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
            shoot(batch);

        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            velocity.x = -Constants.GARY_SPEED;
            facingRight = false;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.D)){
            velocity.x = Constants.GARY_SPEED;
            facingRight = true;
        }
        else
            velocity.x = 0;

        if(Gdx.input.isKeyJustPressed(Input.Keys.W) && velocity.y == 0){
            velocity.y = Constants.GARY_JUMP_SPEED;
        }

        if(gary.x < 0)
            velocity.x = 200;
        else if(gary.x + Constants.GARY_WIDTH > Constants.WORLD_WIDTH)
            velocity.x = -200;


        gary.x += velocity.x * (1.0/60);
        gary.y += velocity.y * (1.0/60);


        if(gary.y > 0){
            gary.y += (0.5)*(Constants.GRAVITY)*(1.0/360);
            velocity.y += Constants.GRAVITY * (1.0/60);
        }
        else
            velocity.y = 0;

        if(coolDown > 0){
            coolDown--;
        }


    }

    public void checkHealth()
    {
        if(Gdx.input.isKeyJustPressed(Input.Keys.H))
            health--;
        for(int i = 0; i < GameScreen.lasers.size(); i++){
            if(gary.overlaps(GameScreen.lasers.get(i).getRectangle())) {
                health--;
                GameScreen.lasers.remove(i);
                i--;
            }
        }
    }

    public void updateHealth(SpriteBatch batch)
    {

        if(health == 3)
        {
            batch.draw(shell, 10,Constants.WORLD_HEIGHT-Constants.SHELL_SIZE,Constants.SHELL_SIZE,Constants.SHELL_SIZE);
            batch.draw(shell, 70,Constants.WORLD_HEIGHT-Constants.SHELL_SIZE,Constants.SHELL_SIZE,Constants.SHELL_SIZE);
            batch.draw(shell, 130,Constants.WORLD_HEIGHT-Constants.SHELL_SIZE,Constants.SHELL_SIZE,Constants.SHELL_SIZE);
        }
        else if(health == 2)
        {
            batch.draw(shell, 10,Constants.WORLD_HEIGHT-Constants.SHELL_SIZE,Constants.SHELL_SIZE,Constants.SHELL_SIZE);
            batch.draw(shell, 70,Constants.WORLD_HEIGHT-Constants.SHELL_SIZE,Constants.SHELL_SIZE,Constants.SHELL_SIZE);
        }
        else if(health == 1)
        {
            batch.draw(shell, 10,Constants.WORLD_HEIGHT-Constants.SHELL_SIZE,Constants.SHELL_SIZE,Constants.SHELL_SIZE);
        }
        else if(health == 0)
        {
            game.setScreen(new GameOverScreen(game, false));

        }
    }

    public void  shoot(SpriteBatch batch){
        if(coolDown == 0) {
            if (facingRight) {
                GameScreen.lasers.add(new Laser(facingRight, gary.x + gary.width + 1, gary.y + gary.height - 15, batch));
                coolDown = (int)(60*var);
            }
            else {
                GameScreen.lasers.add(new Laser(facingRight, gary.x - Constants.LASER_WIDTH, gary.y + gary.height - 15, batch));
                coolDown = (int)(60*var);
            }
        }
    }

    public void dispose(){
        renderer.dispose();
    }

    public void updatePower(float delta) {

        if (PowerUps.garyCtr == 1)
        {
            Constants.GARY_JUMP_SPEED = Constants.FAST_JUMP_SPEED;
        }
        else if(PowerUps.garyCtr == 2)
        {
            var = .5;
        }
        else if(PowerUps.garyCtr == 3)
        {
            Constants.GARY_SPEED = Constants.CHANGED_SPEED;
        }
        else if(PowerUps.garyCtr == 4)
        {
            Constants.GARY_JUMP_SPEED = Constants.SLOW_JUMP_SPEED;
        }

        time += delta;

        if (time > Constants.POWER_TIME)
        {
            if(PowerUps.garyCtr == 1)
            {
                Constants.GARY_JUMP_SPEED = Constants.ORIGINAL_JUMP_SPEED;
            }
            else if(PowerUps.garyCtr == 2)
            {
                var = 1;
            }
            else if(PowerUps.garyCtr == 3)
            {
                Constants.GARY_SPEED = Constants.ORIGINAL_SPEED;
            }
            else if(PowerUps.garyCtr == 4)
            {
                Constants.GARY_JUMP_SPEED = Constants.ORIGINAL_JUMP_SPEED;
            }

            time = 0;
            PowerUps.garyCtr = 0;
        }

    }


}
