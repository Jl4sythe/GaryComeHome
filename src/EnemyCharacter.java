import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
    private Texture cross;
    private GaryGame game;
    private BitmapFont font;

    private Texture garyTexR;
    private Texture garyTexL;
    private Texture patTexR;
    private Texture patTexL;
    private static Rectangle enemy;

    private int coolDown = 0;
    private double var = 1;
    public static float time = 0;
    private int loss;
    public boolean onPlatform;
    public boolean knockBack = false;
    private float timer = 0;

    public EnemyCharacter(GaryGame game) {
        facingRight = false;
        loss = 0;

        this.game = game;

        this.velocity = new Vector2(0, 0);
        garyTexR = new Texture(Gdx.files.internal("assets/enemyRight.png"));
        garyTexL = new Texture(Gdx.files.internal("assets/enemyLeft.png"));
        patTexR = new Texture(Gdx.files.internal("assets/patrickRight.png"));
        patTexL = new Texture(Gdx.files.internal("assets/patrickRight.png"));

        enemy = new Rectangle();
        enemy.x = Constants.WORLD_WIDTH - Constants.GARY_WIDTH;
        enemy.y = 0;
        enemy.width = Constants.GARY_WIDTH;
        enemy.height = Constants.GARY_HEIGHT;

        renderer = new ShapeRenderer();
        font = new BitmapFont(Gdx.files.internal("assets/KarbyParty.fnt"));

        health = 3;
        shell = new Texture(Gdx.files.internal("assets/enemyLeft.png"));
        cross = new Texture(Gdx.files.internal("assets/X.png"));

    }

    public void update(SpriteBatch batch, float delta) {

        checkHealth();
        updateHealth(batch);

        onPlatform = false;

        if (Gdx.input.isKeyPressed(Input.Keys.P)) {
            if (facingRight)
                batch.draw(patTexR, enemy.x, enemy.y, enemy.width, enemy.height);
            else if (!facingRight)
                batch.draw(patTexL, enemy.x, enemy.y, enemy.width, enemy.height);
        } else {
            if (facingRight)
                batch.draw(garyTexR, enemy.x, enemy.y, enemy.width, enemy.height);
            else if (!facingRight)
                batch.draw(garyTexL, enemy.x, enemy.y, enemy.width, enemy.height);
        }


        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && !knockBack) {
            velocity.x = -Constants.ENEMY_SPEED;
            facingRight = false;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !knockBack) {
            velocity.x = Constants.ENEMY_SPEED;
            facingRight = true;
        } else if(!knockBack)
            velocity.x = 0;

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && velocity.y == 0) {
            velocity.y = Constants.ENEMY_JUMP_SPEED;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER))
            shoot(batch);

        if (enemy.x < 0)
            velocity.x = 200;
        else if (enemy.x + Constants.GARY_WIDTH > Constants.WORLD_WIDTH)
            velocity.x = -200;


        enemy.x += velocity.x * (delta);
        enemy.y += velocity.y * (delta);

        for(Platform p: GameScreen.getPlatforms()) {
            if (velocity.y <= 0 && enemy.overlaps(p.getRectangle()) && !Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                //gary.y -= velocity.y * (delta);
                velocity.y = 0;
                onPlatform = true;
            }
        }

        if (enemy.y > 0 && !onPlatform) {
//            enemy.y += (0.5) * (Constants.ENEMY_GRAVITY) * (delta * delta);
            velocity.y += Constants.ENEMY_GRAVITY * (delta);
        } else
            velocity.y = 0;

        if (coolDown > 0) {
            coolDown--;
        }

        if(timer > 0){
            timer --;
        }

        if(knockBack && timer == 0)
            knockBack = false;


    }

    public void checkHealth() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.H))
            health--;
        if(Gdx.input.isKeyJustPressed(Input.Keys.G))
            health++;

        for (int i = 0; i < GameScreen.lasers.size(); i++) {
            if (enemy.overlaps(GameScreen.lasers.get(i).getRectangle())) {
                health--;
                loss = 60;
                GameScreen.lasers.remove(i);
                i--;
                knockBack = true;
                if(GaryCharacter.getGary().x < enemy.x){
                    velocity.x = 500;
                    velocity.y += 300;
                    timer = 1f * 60;
                }
                else if(GaryCharacter.getGary().x > enemy.x){
                    velocity.x = -500;
                    velocity.y += 300;
                    timer = 1f * 60;
                }
            }
        }
    }

    public void updateHealth(SpriteBatch batch) {

        if (health == 3) {
            batch.draw(shell, Constants.WORLD_WIDTH - 50, Constants.WORLD_HEIGHT - Constants.SHELL_SIZE, Constants.SHELL_SIZE, Constants.SHELL_SIZE);
            batch.draw(shell, Constants.WORLD_WIDTH - 110, Constants.WORLD_HEIGHT - Constants.SHELL_SIZE, Constants.SHELL_SIZE, Constants.SHELL_SIZE);
            batch.draw(shell, Constants.WORLD_WIDTH - 170, Constants.WORLD_HEIGHT - Constants.SHELL_SIZE, Constants.SHELL_SIZE, Constants.SHELL_SIZE);
        } else if (health == 2) {
            batch.draw(shell, Constants.WORLD_WIDTH - 50, Constants.WORLD_HEIGHT - Constants.SHELL_SIZE, Constants.SHELL_SIZE, Constants.SHELL_SIZE);
            batch.draw(shell, Constants.WORLD_WIDTH - 110, Constants.WORLD_HEIGHT - Constants.SHELL_SIZE, Constants.SHELL_SIZE, Constants.SHELL_SIZE);
        } else if (health == 1) {
            batch.draw(shell, Constants.WORLD_WIDTH - 50, Constants.WORLD_HEIGHT - Constants.SHELL_SIZE, Constants.SHELL_SIZE, Constants.SHELL_SIZE);
        } else if (health == 0) {
            game.setScreen(new GameOverScreen(game, true));

        }

        if (loss > 0) {
            //font.draw(batch, "-1", enemy.x, enemy.y +100);
            batch.draw(cross, enemy.x + Constants.GARY_WIDTH / 2 - 20, enemy.y + Constants.GARY_HEIGHT + 20, Constants.GARY_WIDTH / 2, Constants.GARY_HEIGHT / 2);
            loss--;
        }
    }

    public void shoot(SpriteBatch batch) {
        if (coolDown == 0) {
            if (facingRight) {
                GameScreen.lasers.add(new Laser(facingRight, enemy.x + enemy.width + 1, enemy.y + enemy.height - 15, batch));
                coolDown = (int) (60 * var);
            } else {
                GameScreen.lasers.add(new Laser(facingRight, enemy.x - Constants.LASER_WIDTH, enemy.y + enemy.height - 15, batch));
                coolDown = (int) (60 * var);
            }
        }
    }

    public void dispose() {
        renderer.dispose();
    }

    public void updatePower(float delta) {
        if (PowerUps.enemyCtr >= 1 && PowerUps.enemyCtr <= 20) {
            Constants.ENEMY_JUMP_SPEED = Constants.FAST_JUMP_SPEED;
        } else if (PowerUps.enemyCtr >= 21 && PowerUps.enemyCtr <= 40) {
            var = 0.25;
        } else if (PowerUps.enemyCtr >= 41 && PowerUps.enemyCtr <= 60) {
            Constants.ENEMY_SPEED = Constants.CHANGED_SPEED;
        } else if (PowerUps.enemyCtr >= 61 && PowerUps.enemyCtr <= 80) {
            Constants.ENEMY_JUMP_SPEED = Constants.SLOW_JUMP_SPEED;
        } else if (PowerUps.enemyCtr == 81) {
            Constants.ENEMY_GRAVITY = Constants.CHANGED_GRAVITY;
        }

        time += delta;

        if (time >= Constants.POWER_TIME) {
            if (PowerUps.enemyCtr >= 1 && PowerUps.enemyCtr <= 20) {
                Constants.ENEMY_JUMP_SPEED = Constants.ORIGINAL_JUMP_SPEED;
            } else if (PowerUps.enemyCtr >= 21 && PowerUps.enemyCtr <= 40) {
                var = 1;
            } else if (PowerUps.enemyCtr >= 51 && PowerUps.enemyCtr <= 60) {
                Constants.ENEMY_SPEED = Constants.ORIGINAL_SPEED;
            } else if (PowerUps.enemyCtr >= 61 && PowerUps.enemyCtr <= 80) {
                Constants.ENEMY_JUMP_SPEED = Constants.ORIGINAL_JUMP_SPEED;
            } else if (PowerUps.enemyCtr == 81) {
                Constants.ENEMY_GRAVITY = Constants.ORIGINAL_GRAVITY;
            }

            time = 0;
            PowerUps.enemyCtr = 0;
        }

    }

    public Rectangle getRectangle() {
        return enemy;
    }

    public static Rectangle getEnemy() {
        return enemy;
    }
}
