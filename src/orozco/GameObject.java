package orozco;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.Icon;

//GENERAL GAME OBJECT
public abstract class GameObject implements KeyListener {
	// EACH GAME OBJECT HAS AN X,Y LOCATION, VELOCITY, AND A DIRECTION

	private int x;
	private int y;
	private int velocity;
	private int direction;

	// EACH GAME OBJECT CAN HAVE A COLLECTION OF IMAGES
	protected List<Icon> imageList;
	protected int currentImage;

	// KEEP TRACK OF WHO THE USER IS CONTROLLING
	protected boolean userControlling;

	// KEEP TRACK OF MOVEMENT (WITH IMAGE)
	protected int previousDirection;
	protected int previousImage;

	// WHEN CREAING OBJECT, NOW WILL BE IN CONTROL
	public GameObject(int x, int y, boolean userControlling) {
		this.x = x;
		this.y = y;
		velocity = 0;
		currentImage = 0;

		// USER CONTROLLING?
		this.userControlling = userControlling;
	}

	public void draw(Component c, Graphics g) {
		imageList.get(currentImage).paintIcon(c, g, x, y); // Draw the current image
	}

	// SETTERS AND GETTERS

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}

	public int getVelocity() {
		return velocity;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {

		// MAKING SURE OBJECT IS NOT BEING CONTROLLED
		if (!userControlling && direction != Direction.NONE) {
			previousDirection = this.direction;
			previousImage = this.currentImage;
		}

		// EVERY OBJECT FREEZES WITH ELSE {}
		this.direction = direction;
	}

	public Icon getCurrentImage() {
		return imageList.get(currentImage);
	}

	// NEW METHODS NOT INCLUDED WITH TRISH CODE -------------

	public void setUserControlling(boolean userControlling) {

		// USER IS IN CONTROL
		if (userControlling) {
			previousDirection = direction;
			previousImage = currentImage;
			setDirection(Direction.NONE);
		}

		// IF THEY ARE NOT IN CONTROL
		else {
			setDirection(previousDirection);
			currentImage = previousImage;
		}

		this.userControlling = userControlling;
	}

	public boolean getUserControlling() {
		return userControlling;
	}

	public void keyReleased(KeyEvent e) {

		// USER IS IN CONTROL
		if (userControlling) {
			if (e.getKeyCode() != KeyEvent.VK_TAB) {
				setDirection(Direction.NONE); // Stop moving when the key is released
			}
		}
	}

	// ABSTRACT METHODS
	public abstract void move(Canvas c);

	public abstract void setImage();

}
