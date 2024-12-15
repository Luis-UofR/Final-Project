package orozco;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class Type_C_GameObject extends GameObject {

	// Constructor initializes position, movement direction, velocity, and image
	// list
	public Type_C_GameObject(int x, int y, boolean userControlling) {
		super(x, y, userControlling); // Call the parent constructor
		setDirection(Direction.RIGHT); // Initial direction is RIGHT
		setVelocity(5); // Set the speed of movement

		// Add images for animation (one for each horizontal direction)
		imageList = new LinkedList<>();
		imageList.add(new ImageIcon("images/Type_C_Left.png")); // Image for moving LEFT
		imageList.add(new ImageIcon("images/Type_C_Right.png")); // Image for moving RIGHT
	}

	@Override
	public void move(Canvas c) {
		// Get canvas width and icon width for bouncing logic
		Icon icon = getCurrentImage();
		int iconWidth = icon.getIconWidth();
		int canvasWidth = (int) c.getSize().getWidth();

		// Handle horizontal movement and bouncing back and forth
		if (getDirection() == Direction.RIGHT) {
			setX(getX() + getVelocity()); // Move right
			if (getX() + iconWidth > canvasWidth) { // Check if the object hits the right boundary
				setX(canvasWidth - iconWidth); // Stop at the right boundary
				setDirection(Direction.LEFT); // Bounce to the left
			}
		} else if (getDirection() == Direction.LEFT) {
			setX(getX() - getVelocity()); // Move left
			if (getX() < 0) { // Check if the object hits the left boundary
				setX(0); // Stop at the left boundary
				setDirection(Direction.RIGHT); // Bounce to the right
			}
		}
	}

	// SPECIFY THE IMAGE TO DISPLAY
	// USED FOR ANIMATION
	public void setImage() {
		switch (getDirection()) {
		case Direction.LEFT:
			currentImage = 0;
			break;
		case Direction.RIGHT:
			currentImage = 1;
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
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				setDirection(Direction.RIGHT);
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				setDirection(Direction.LEFT);
			}
		}

	}
}
