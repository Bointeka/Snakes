//Jeremy Okeyo
//Runs snakes
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import java.util.Random;
import javafx.application.Application;
import java.util.ArrayList;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Ellipse;
import javafx.scene.canvas.*;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.stage.Stage;

public class snake extends Application{
	private final double size = 20;
	private final double radius = 10;
	private ArrayList<Rectangle> snake;
	private Ellipse food = new Ellipse(radius, radius);
	private Group root;
	private Scene scene;
	private Canvas canvas;
	private GraphicsContext gc;
	private int score;
	private int length; // Length of snake
	private int direction;

	//Field has a 25 * 25 layout
	private void setWindow() {
		root = new Root();
		canvas = new canvas(500, 500);
		scene = new Scene(root, 500, 500, Color.GRAY);
		gc = canvas.getGraphicsContext();
		setSnake();
		setFood();
		addToCanvas();
	}

	private void addToCanvas() {
		for (int i = 0; i < length; i++) {
			canvas.getChildren().add(snake.get(i));
		}
		canvas.getChildren().add(food);

	}
	//Sets the snake to intial start
	private void setSnake() {
		direction = 1;
		snake.add(new Rectangle (size, size, Color.RED));
		snake.get(1).setX(300);
		snake.get(1).setY(400);
		for (int i = 2; i <= 3; i++) {
			snake.add(new Rectangle(size,size, Color.GREEN));
			snake.get(i).setX(300 + size* (i - 1));
			snake.get(i).setY(400 + size * (i - 1));
		}
	}

	//Spawns food onto board
	private void setFood() {
		Random rand = new Random();
		int X = rand.nextInt(25);
		int Y = rand.nextInt(25);
		if (X % 2 == 0)
			X += 1;
		if (Y % 2 == 0)
			Y += 1;
		if (!isOccupied(X, Y)) {
			food.setCenterX((double)X * radius);
			food.setCenterY((double)Y * radius);
		}
		else
			setFood();
	}

	//Checks whether food is spawned inside snake
	private boolean isOccupied(int X, int Y) {
		for (int i = 0; i < length; i++) {
			if ((X * radius) == snake.get(i).getX() + 10 &&
					(Y * radius) == snake.get(i).getY() + 10) {
				return true;
					}
		}
		return false;
	}

	//Checks whether snake head intersect with food
	private boolean isOccupied() {
		if (snake.get(0).getX() == (food.getCenterX() - radius) &&
				snake.get(0).getY() == (food.getCenterY() - radius))
			return true;
		return false;
	}

	private void grow() {
		snake.add(new Rectangle(size, size, Color.GREEN));
		switch(direction) {
			case(1):  //Direction up
				snake.get(length).setY(snake.get(length - 1).getY() + 20);
			case(-1): // Direction down
				snake.get(length).setY(snake.get(length - 1).getY() - 20);
			case(2): // Direction to the left
				snake.get(length).setX(snake.get(length - 1).getX() + 20);
			case(-2): // Direction to the right
				snake.get(length).setX(snake.get(length - 1).getX() - 20);
		}
	}
	private int getLength() {
		return length;
	}

	private int getScore() {
		return score;
	}

	@Override
	public void start(Stage stage) {
		setWindow();
		stage.setTitle("Snakes");
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}

