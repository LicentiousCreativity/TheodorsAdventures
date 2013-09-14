package de.licentiouscreativity.theodorsadventures.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Entity {

	protected final SpriteBatch batch;
	
	protected int moveSpeed, jumpSpeed;
	protected float posX, posY, dirX, dirY;
	protected boolean jumping, moveRight, moveLeft;
	protected final Rectangle bounceHead, bounceBody;
	
	//Walk Animation
	private static final int FRAME_COLS = 6;
	private static final int FRAME_ROWS = 5;
	private Animation walkAnimation;
	private Texture walkSheet;
	private final String walkSheetName;
	private TextureRegion[] walkFrames;
	
	protected TextureRegion currentFrame;
	private float stateTime;
	
	public Entity(final int posX, final int posY, final int moveSpeed, final int jumpSpeed, final Rectangle bounceHead, final Rectangle bounceBody, final String walkSheetName, final SpriteBatch batch) {
		this.posX = posX;
		this.posY = posY;
		this.moveSpeed = moveSpeed;
		this.jumpSpeed = jumpSpeed;
		this.bounceHead = bounceHead;
		this.bounceBody = bounceBody;
		this.walkSheetName = walkSheetName;
		this.batch = batch;
		
		jumping = false;
		moveRight = false;
		moveLeft = false;
		
		
		//TODO use animator
		currentFrame = new TextureRegion(new Texture(Gdx.files.internal(walkSheetName)));
		//animator(); 
	}
	
	public void update(final float delta) {
		posX += dirX * delta;
		posY += dirY * delta;
		
		if (jumping) {
			dirY += 10;
			
			//TODO set ground
			if (posY >= 200) {
				posY = 200;
				dirY = 0;
				jumping = false;
			}
		}
	}
	
	public void render() {
		
		/**
		//if the Entity is moving the walk animation is active.
		if (dirX != 0 || dirY != 0) {
			stateTime += Gdx.graphics.getDeltaTime();      
	        currentFrame = walkAnimation.getKeyFrame(stateTime, true);
		} else {
			stateTime = 0;
		}
		*/
		
		batch.draw(currentFrame, posX, posY);
	}
	
	private void animator() {
		walkSheet = new Texture(Gdx.files.internal(walkSheetName));
		TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / FRAME_COLS, walkSheet.getHeight() / FRAME_ROWS);
		walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
                for (int j = 0; j < FRAME_COLS; j++) {
                        walkFrames[index++] = tmp[i][j];
                }
        }
        walkAnimation = new Animation(0.025f, walkFrames);
        stateTime = 0f;
	}
	
	/**
	 * Entity's dirX is set to speedX
	 */
	public void moveRight() {
		dirX = moveSpeed;
		moveRight = true;
		moveLeft = false;
	}
	
	/**
	 * Entity's dirX is set to -(speedX)
	 */
	public void moveLeft() {
		dirX = -moveSpeed;
		moveLeft = true;
		moveRight = false;
	}
	
	/**
	 * Entity stops moving right or left
	 */
	public void stopMove() {
		dirX = 0;
		moveRight = false;
		moveLeft = false;
	}

	public void jump() {
		if (!jumping) {
			jumping = true;
			dirY = jumpSpeed;
		}
	}
	
	public boolean isMoving() {
		if (moveLeft == true || moveRight == true) {
			return true;
		}
		
		return false;
	}
	
	public boolean isMoveRight() {
		return moveRight;
	}

	public boolean isMoveLeft() {
		return moveLeft;
	}

	public void setMoveRight(boolean moveRight) {
		this.moveRight = moveRight;
	}

	public void setMoveLeft(boolean moveLeft) {
		this.moveLeft = moveLeft;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}
}
