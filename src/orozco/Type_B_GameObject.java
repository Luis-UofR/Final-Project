package orozco;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class Type_B_GameObject extends GameObject {

	// Constructor initializes position, direction, velocity, and image list
	public Type_B_GameObject(int x, int y, boolean userControlling) {
		super(x, y, userControlling); // Call the parent constructor
		setDirection(Direction.DOWN); // Start by moving DOWN
		setVelocity(5); // Set the speed of movement

		// Add images for animation (one for each direction)
		imageList = new LinkedList<>();
		imageList.add(new ImageIcon("images/Type_B_Up.png")); // Image for moving UP
		imageList.add(new ImageIcon("images/Type_B_Down.png")); // Image for moving DOWN
		imageList.add(new ImageIcon("images/Type_B_Left.png")); // Image for moving LEFT
		imageList.add(new ImageIcon("images/Type_B_Right.png")); // Image for moving RIGHT
	}

	@Override
	public void move(Canvas c) {
		// Get canvas dimensions and icon size for boundary checks
		Icon icon = getCurrentImage();
		int iconWidth = icon.getIconWidth();
		int iconHeight = icon.getIconHeight();
		int canvasWidth = (int) c.getSize().getWidth();
		int canvasHeight = (int) c.getSize().getHeight();

		// Move and update direction based on boundary collisions
		switch (getDirection()) {
		case Direction.DOWN:
			setY(getY() + getVelocity()); // Move down
			if (getY() + iconHeight > canvasHeight) { // Hit bottom boundary
				setY(canvasHeight - iconHeight); // Stop at bottom
				setDirection(Direction.RIGHT); // Change direction to RIGHT
			}
			break;

		case Direction.RIGHT:
			setX(getX() + getVelocity()); // Move right
			if (getX() + iconWidth > canvasWidth) { // Hit right boundary
				setX(canvasWidth - iconWidth); // Stop at right
				setDirection(Direction.UP); // Change direction to UP
			}
			break;

		case Direction.UP:
			setY(getY() - getVelocity()); // Move up
			if (getY() < 0) { // Hit top boundary
				setY(0); // Stop at top
				setDirection(Direction.LEFT); // Change direction to LEFT
			}
			break;

		case Direction.LEFT:
			setX(getX() - getVelocity()); // Move left
			if (getX() < 0) { // Hit left boundary
				setX(0); // Stop at left
				setDirection(Direction.DOWN); // Change direction to DOWN
			}
			break;

		default:
			break;
		}
	}

	@Override
	public void setImage() {
		// Update the current image based on the direction of movement
		switch (getDirection()) {
		case Direction.UP:
			currentImage = 0;
			break;
		case Direction.DOWN:
			currentImage = 1;
			break;
		case Direction.LEFT:
			currentImage = 2;
			break;
		case Direction.RIGHT:
			currentImage = 3;
			break;
		default:
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (userControlling) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				setDirection(Direction.UP);
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				setDirection(Direction.DOWN);
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				setDirection(Direction.RIGHT);
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				setDirection(Direction.LEFT);
			}
		}

	}
}
