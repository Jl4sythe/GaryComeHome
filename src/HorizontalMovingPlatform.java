import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HorizontalMovingPlatform extends Platform {
    private int velocity;
    public HorizontalMovingPlatform(float x, float y, float width, float height, int velocity){
        super(x,y,width,height);
        this.velocity = velocity;
    }

    public int getVelocity() {
        return velocity;
    }

    @Override
    public void update(SpriteBatch batch, float delta){
        super.updateX(velocity * delta);
        if(super.getRectangle().x + super.getRectangle().width >= Constants.WORLD_WIDTH){
            velocity = -velocity;
            super.getRectangle().x--;
        }
        else if(super.getRectangle().x <= 0){
            velocity = -velocity;
            super.getRectangle().x++;
        }
        super.update(batch,delta);

    }
}
