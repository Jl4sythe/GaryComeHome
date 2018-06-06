import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
    private Texture cross;
    private GaryGame game;
    private BitmapFont font;
    
    private Texture garyTexR;
    private Texture garyTexL;
    private static Rectangle gary;

    private int coolDown = 0;
    private double var = 1;
    public static  float time = 0;
    private int loss;
    private boolean onPlatform = false;
    private Platform currentPlatform;
    public boolean knockBack = false;
    private float timer = 0;

    public GaryCharacter(GaryGame game) {
        facingRight = true;
        loss = 0;

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
        font = new BitmapFont(Gdx.files.internal("assets/KarbyParty.fnt"));

        health = 3;
        shell = new Texture(Gdx.files.internal("assets/garyRight.png"));
        cross = new Texture(Gdx.files.internal("assets/X.png"));
    }

    public Rectangle getRectangle(){
        return gary;
    }

    public void update(SpriteBatch batch, float delta){

        checkHealth();
        updateHealth(batch);

        onPlatform = false;

        if(facingRight)
            batch.draw(garyTexR, gary.x, gary.y, gary.width, gary.height);
        else if(!facingRight)
            batch.draw(garyTexL, gary.x, gary.y, gary.width, gary.height);

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
            shoot(batch);

        if(Gdx.input.isKeyPressed(Input.Keys.A) && !knockBack) {
            velocity.x = -Constants.GARY_SPEED;
            facingRight = false;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.D) && !knockBack){
            velocity.x = Constants.GARY_SPEED;
            facingRight = true;
        }
        else if(!knockBack)
            velocity.x = 0;
        if(Gdx.input.isKeyJustPressed(Input.Keys.W) && velocity.y == 0){
            velocity.y = Constants.GARY_JUMP_SPEED;
        }
        if(gary.x < 0)
            velocity.x = 200;
        else if(gary.x + Constants.GARY_WIDTH > Constants.WORLD_WIDTH)
            velocity.x = -200;

        gary.x += velocity.x * (delta);
        gary.y += velocity.y * (delta);

        for(Platform p: GameScreen.getPlatforms()) {
            if (velocity.y <= 0 && gary.overlaps(p.getRectangle()) && !Gdx.input.isKeyPressed(Input.Keys.S)) {
                //gary.y -= velocity.y * (delta);
                onPlatform = true;
                currentPlatform = p;
            }
        }

//        if(onPlatform && gary.y < currentPlatform.getRectangle().y + currentPlatform.getRectangle().getHeight())
//            onPlatform = false;

        if(onPlatform)
            velocity.y = 0;

        if (gary.y > 0 && (!onPlatform)) {
            //gary.y += (0.5) * (Constants.GRAVITY) * (delta * delta);
            velocity.y += Constants.GRAVITY * (delta);
        } else
            velocity.y = 0;

        if (coolDown > 0) {
            coolDown--;
        }

        if(timer > 0){
            timer --;
        }

        if(knockBack && timer <= 0)
            knockBack = false;
    }

    public void checkHealth()
    {
        if(Gdx.input.isKeyJustPressed(Input.Keys.H))
            health--;
        for(int i = 0; i < GameScreen.lasers.size(); i++){
            if(gary.overlaps(GameScreen.lasers.get(i).getRectangle())) {
                health--;
                loss = 60;
                GameScreen.lasers.remove(i);
                i--;
                knockBack = true;
                if(EnemyCharacter.getEnemy().x < gary.x){
                    velocity.x = 500;
                    velocity.y += 300;
                    timer = 1f * 60;
                }
                else if(EnemyCharacter.getEnemy().x > gary.x){
                    velocity.x = -500;
                    velocity.y += 300;
                    timer = 1f * 60;
                }
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

        if(loss > 0)
        {
            //font.draw(batch, "-1", gary.x, gary.y +100);
            batch.draw(cross, gary.x + Constants.GARY_WIDTH/2 - 20, gary.y + Constants.GARY_HEIGHT + 20, Constants.GARY_WIDTH/2, Constants.GARY_HEIGHT/2);
            loss--;
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

        if (time >= Constants.POWER_TIME)
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

    public static Rectangle getGary(){
        return gary;
    }

}
