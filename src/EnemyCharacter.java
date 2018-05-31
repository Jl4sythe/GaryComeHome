import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class EnemyCharacter {
    private Vector2 velocity;
    private ShapeRenderer renderer;
    private boolean facingRight;
    private int health;
    private Texture shell;
    private GaryGame game;

    private Texture garyTexR;
    private Texture garyTexL;
    private Rectangle enemy;

    private int coolDown = 0;
    private double var = 1;
    private float time = 0;

    public EnemyCharacter(GaryGame game) {
        facingRight = false;

        this.game = game;

        this.velocity = new Vector2(0,0);
        garyTexR = new Texture(Gdx.files.internal("assets/enemyRight.png"));
        garyTexL = new Texture(Gdx.files.internal("assets/enemyLeft.png"));

        enemy = new Rectangle();
        enemy.x = Constants.WORLD_WIDTH - Constants.GARY_WIDTH;
        enemy.y = 0;
        enemy.width = Constants.GARY_WIDTH;
        enemy.height = Constants.GARY_HEIGHT;

        renderer = new ShapeRenderer();

        health = 3;
        shell = new Texture(Gdx.files.internal("assets/enemyLeft.png"));

    }

    public Rectangle getRectangle()
    {
        return enemy;
    }

    public void update(SpriteBatch batch, float delta){

        checkHealth();
        updateHealth(batch);

        if(facingRight)
            batch.draw(garyTexR, enemy.x, enemy.y, enemy.width, enemy.height);
        else if(!facingRight)
            batch.draw(garyTexL, enemy.x, enemy.y, enemy.width, enemy.height);



        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            velocity.x = -Constants.ENEMY_SPEED;
            facingRight = false;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            velocity.x = Constants.ENEMY_SPEED;
            facingRight = true;
        }
        else
            velocity.x = 0;

        if(Gdx.input.isKeyJustPressed(Input.Keys.UP) && velocity.y == 0){
            velocity.y = Constants.ENEMY_JUMP_SPEED;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER))
            shoot(batch);

        if(enemy.x < 0)
            velocity.x = 200;
        else if(enemy.x + Constants.GARY_WIDTH > Constants.WORLD_WIDTH)
            velocity.x = -200;


        enemy.x += velocity.x * (delta);
        enemy.y += velocity.y * (delta);


        if(enemy.y > 0){
            enemy.y += (0.5)*(Constants.GRAVITY)*(delta * delta);
            velocity.y += Constants.GRAVITY * (delta);
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
            if(enemy.overlaps(GameScreen.lasers.get(i).getRectangle())) {
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
            batch.draw(shell, Constants.WORLD_WIDTH-50,Constants.WORLD_HEIGHT-Constants.SHELL_SIZE,Constants.SHELL_SIZE,Constants.SHELL_SIZE);
            batch.draw(shell, Constants.WORLD_WIDTH-110,Constants.WORLD_HEIGHT-Constants.SHELL_SIZE,Constants.SHELL_SIZE,Constants.SHELL_SIZE);
            batch.draw(shell, Constants.WORLD_WIDTH-170,Constants.WORLD_HEIGHT-Constants.SHELL_SIZE,Constants.SHELL_SIZE,Constants.SHELL_SIZE);
        }
        else if(health == 2)
        {
            batch.draw(shell, Constants.WORLD_WIDTH-50,Constants.WORLD_HEIGHT-Constants.SHELL_SIZE,Constants.SHELL_SIZE,Constants.SHELL_SIZE);
            batch.draw(shell, Constants.WORLD_WIDTH-110,Constants.WORLD_HEIGHT-Constants.SHELL_SIZE,Constants.SHELL_SIZE,Constants.SHELL_SIZE);
        }
        else if(health == 1)
        {
            batch.draw(shell, Constants.WORLD_WIDTH-50,Constants.WORLD_HEIGHT-Constants.SHELL_SIZE,Constants.SHELL_SIZE,Constants.SHELL_SIZE);
        }
        else if(health == 0)
        {
            game.setScreen(new GameOverScreen(game, true));

        }
    }

    public void  shoot(SpriteBatch batch){
        if(coolDown == 0) {
            if (facingRight) {
                GameScreen.lasers.add(new Laser(facingRight, enemy.x + enemy.width + 1, enemy.y + enemy.height - 15, batch));
                coolDown = (int)(60*var);
            }
            else {
                GameScreen.lasers.add(new Laser(facingRight, enemy.x - Constants.LASER_WIDTH, enemy.y + enemy.height - 15, batch));
                coolDown = (int)(60*var);
            }
        }
    }

    public void dispose(){
        renderer.dispose();
    }

    public void updatePower(float delta) {
        if (PowerUps.enemyCtr == 1)
        {
            Constants.ENEMY_JUMP_SPEED = Constants.FAST_JUMP_SPEED;
        }
        else if(PowerUps.enemyCtr == 2)
        {
            var = 0.25;
        }
        else if(PowerUps.enemyCtr == 3)
        {
            Constants.ENEMY_SPEED = Constants.CHANGED_SPEED;
        }
        else if(PowerUps.enemyCtr == 4)
        {
            Constants.ENEMY_JUMP_SPEED = Constants.SLOW_JUMP_SPEED;
        }

        time += delta;

        if (time > Constants.POWER_TIME)
        {
            if(PowerUps.enemyCtr == 1)
            {
                Constants.ENEMY_JUMP_SPEED = Constants.ORIGINAL_JUMP_SPEED;
            }
            else if(PowerUps.enemyCtr == 2)
            {
                var = 1;
            }
            else if(PowerUps.enemyCtr == 3)
            {
                Constants.ENEMY_SPEED = Constants.ORIGINAL_SPEED;
            }
            else if(PowerUps.enemyCtr == 4)
            {
                Constants.ENEMY_JUMP_SPEED = Constants.ORIGINAL_JUMP_SPEED;
            }

            time = 0;
            PowerUps.enemyCtr = 0;
        }

    }
}
