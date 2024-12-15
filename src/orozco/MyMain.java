package orozco;

public class MyMain {

	public static void main(String[] args) {

		// TASK 1: CREATE A CANVAS FOR ANIMATION
		Canvas canvas = new Canvas();
		canvas.requestFocus();

		// TASK 2: ADD A USER GAME OBJECT OF TYPE D
		Type_D_GameObject user = new Type_D_GameObject(100, 100, true);
		user.setVelocity(10);
		canvas.addGameObject(user);
		canvas.addKeyListener(user);
		
		// TASK 3: Add the rest of the objects
		Type_A_GameObject typeA = new Type_A_GameObject(200, 200, false);
		typeA.setVelocity(10);
		canvas.addGameObject(typeA);
		canvas.addKeyListener(typeA);

		Type_B_GameObject typeB = new Type_B_GameObject(300, 300, false);
		typeB.setVelocity(10);
		canvas.addGameObject(typeB);
		canvas.addKeyListener(typeB);

		Type_C_GameObject typeC = new Type_C_GameObject(300, 300, false);
		typeC.setVelocity(10);
		canvas.addGameObject(typeC);
		canvas.addKeyListener(typeC);

	}

}
