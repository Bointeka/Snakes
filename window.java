//Jeremy Okeyo
//Used to setup the game window, when play is selected

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;
public class window extends Application{
	Random rand = new Random();
	public static final int winSize = 16;
	private Group board;
	private Scene game;
	private Canvas canvas;
	private GraphicsContext gc;
	public static final double size = 19.53;
	private game gme;

	//Sets up the game window
	public void setWindow() {
		board = new Group();
		game = new Scene(board, 312.5, 312.5, Color.BLACK);
		canvas  = new Canvas(312.5, 312.5);

		gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.BLACK); //Change to Black after test
		board.getChildren().add(canvas);
	}

	//Adds the food to the specific cell
	public void popFood() {
		//Used to populate board with food
		int foodCol = rand.nextInt(winSize);
		int foodRow = rand.nextInt(winSize);
		gme.setFood(foodCol, foodRow);
	}

	//Sets the board to its default starting point
	private void setBoard() {
		gc.setFill(Color.BLACK);
		gc.fillRect(0,0,312.5, 312.5);
	}

	//Initializes the controls
	private void initializeControls() {
		//Sets up Controls
		game.setOnKeyPressed((e) -> {
				gme.validKey(e);
			}
		);
		new AnimationTimer() {
			boolean gameOvr = false;
			long lastTick = 0;
			@Override
			public void handle (long now) {
				if (lastTick == 0) {
					lastTick = now;
					setBoard();
					gameOvr = gme.gameLoop(gc);
					return;
				}
				if (now - lastTick > 100000000) {
					setBoard();
					lastTick = now;
					gameOvr = gme.gameLoop(gc);
				}
				if (gameOvr) {
					this.stop();
				}
			}
		}.start();
	}

	@Override
	public void start(Stage stage) throws IOException {
		//OnMousePressed
		gme = new game();
		setWindow();
		popFood();
		stage.setScene(game);
		initializeControls();

		stage.setTitle("Snakes");
		stage.setScene(game);
		stage.sizeToScene();
		stage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
