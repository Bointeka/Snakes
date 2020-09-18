//Jeremy Okeyo
//Used to setup the game window, when play is selected
import java.io.IOException;
import javafx.animation.Animation;
import javafx.util.Duration;
import javafx.event.EventHandler;
import javafx.animation.KeyFrame;
import java.util.Random;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.*;
import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
public class window extends Application{
	Random rand = new Random();
	public static final int winSize = 16;
	private Group board;
	private Group menu;
	private Scene mnu;
	private Scene game;
	private Canvas canvas;
	private GraphicsContext gc;
	public static final double size = 19.53;
	private game gme;
	private boolean grew = true;
	private ArrayList<Rectangle> options = new ArrayList<Rectangle>();
	/*
		window() {

		}

		window(Load file) {

		}
		*/
	public void menu() {
		menu = new Group();
		mnu = new Scene(menu, 312.5, 312.5, Color.BLACK);	
		for (int i = 0; i < 2; i++) {
			options.add(new Rectangle(50, 50 + i * 90, 100, 60));
			options.get(i).setFill(Color.WHITE);
			menu.getChildren().add(options.get(i));
		}
	}

	public void setWindow() {
		board = new Group();
		game = new Scene(board, 312.5, 312.5, Color.BLACK);
		canvas  = new Canvas(312.5, 312.5);

		gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.BLACK); //Change to Black after test
		board.getChildren().add(canvas);
	}
	public void popFood() {
		//Used to populate board with food
		int foodCol = rand.nextInt(winSize);
		int foodRow = rand.nextInt(winSize);
		gme.setFood(foodCol, foodRow);
	}
	private void setBoard() {
		gc.setFill(Color.BLACK);
		gc.fillRect(0,0,312.5, 312.5);
	}
	
	@Override
	public void start(Stage stage) throws IOException {
		menu();
//OnMousePressed
		if (options.get(0).isPressed()) {
			gme = new game();
			System.out.print("s");
			setWindow();
			popFood();
			stage.setScene(game);
		}
		else if (options.get(1).isPressed()) {
			Load load = new Load();
			gme = new game(load);
			setWindow();
			stage.setScene(game);
		}
		//Sets up Controls
	/*	sc.setOnKeyPressed((e) -> {
			try {
				gme.validKey(e);
			}
			catch (IOException i) {
				System.out.println("unable to open/ close files");
			}
		}
		);

		new AnimationTimer () {
			boolean gameOvr = false;
			long lastTick = 0;
			@Override
			public void handle (long now) {
				if (lastTick == 0) {
					lastTick = now;
					setBoard();
					gameOvr = gme.gameLoop(gc, sc);
					return;
				}
				if (now - lastTick > 100000000) {
					setBoard();
					lastTick = now;
					gameOvr = gme.gameLoop(gc, sc);
				}
				if (gameOvr == true)
					this.stop();
				/*if (gme.pause && !gameOvr)
					this.stop();
				else if (!gme.pause && !gameOvr)
					this.start(); 
			}
		}.start(); */
		stage.setTitle("Snakes");
		stage.setScene(mnu);
		stage.sizeToScene();
		stage.show();
	}
	public static void main(String[] args) {
		Application.launch(args);

	}

}
