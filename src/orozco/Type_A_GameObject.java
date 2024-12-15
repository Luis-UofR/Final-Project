package orozco;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class Type_A_GameObject extends GameObject {

	// Constructor initializes position, movement direction, velocity, and image
	// list
	public Type_A_GameObject(int x, int y, boolean userControlling) {
		super(x, y, userControlling); // Call the parent constructor
		setDirection(Direction.UP); // Initial direction is UP
		setVelocity(5); // Set the speed of movement

		// Add images for animation (one for moving up, one for moving down)
		imageList = new LinkedList<>();
		imageList.add(new ImageIcon("images/Type_A_Up.png")); // Image for moving UP
		imageList.add(new ImageIcon("images/Type_A_Down.png")); // Image for moving DOWN
	}

	@Override
	public void move(Canvas c) {
		// Get canvas height and icon height to handle boundary conditions
		Icon icon = getCurrentImage();
		int iconHeight = icon.getIconHeight();
		int canvasHeight = (int) c.getSize().getHeight();

		// Handle vertical movement based on direction
		if (getDirection() == Direction.UP) {
			setY(getY() - getVelocity()); // Move upwards
			if (getY() < 0) { // Check for boundary
				setY(0); // Stop at the top boundary
				setDirection(Direction.DOWN); // Change direction to DOWN
			}
		} else if (getDirection() == Direction.DOWN) {
			setY(getY() + getVelocity()); // Move downwards
			if (getY() + iconHeight > canvasHeight) { // Check for bottom boundary
				setY(canvasHeight - iconHeight); // Stop at the bottom boundary
				setDirection(Direction.UP); // Change direction to UP
			}
		}
	}

	// SPECIFY THE IMAGE TO DISPLAY
	// USED FOR ANIMATION
	public void setImage() {
		switch (getDirection()) {
		case Direction.UP:
			currentImage = 0;
			break;
		case Direction.DOWN:
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
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				setDirection(Direction.UP);
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				setDirection(Direction.DOWN);
			}

		}
	}
}