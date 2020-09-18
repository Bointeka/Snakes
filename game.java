//Jeremy Okeyo
//Keeps track of snake on
import java.io.IOException;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.Scene;
import java.util.Random;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;

public class game {
	private Random rand = new Random();
	private ArrayList<Cell> snake = new ArrayList<Cell>();
	private int level;
	private int length;
	private int y = 2;
	private int x = 12;
	private Cell food;
	private int winSize = window.winSize;
	private double cellSize = window.size;
	private boolean gameOver = false;
	public boolean pause = false;

	//Creates a new game
	public game() {
		food = new Cell();
		setFood();
		length = 3;
		for (int i = 0; i < length; i++) {
			snake.add(new Cell(x + i, y,'l'));
		}
	}

	//Used to create game from a load file
	public game(Load file){
		length = file.length;
		level = file.level;
		food = file.food;
		for (int i = 0; i < length; i++) {
			snake.add(file.snake.get(i));
		}
	}
		
	//Used to set new food location
	public void setFood () {
		food.setLoc(rand.nextInt(winSize),rand.nextInt(winSize));
	}
	//Used to set food location from load file
	public void setFood (int foodCol, int foodRow) {
		food.setLoc(foodCol, foodRow);
	}

	public int getLength() {
		return length;
	}

	private boolean grow() {
		Cell last = snake.get(length - 1);
		switch (last.getDir()) {
			case 'u':
				snake.add(new Cell(last.getX() + 1,last.getY(),
							last.getDir()));
				break;
			case 'd':
				snake.add(new Cell(last.getX(),last.getY() - 1,
							last.getDir()));
				break;
			case 'l':
				snake.add(new Cell(last.getX() + 1,last.getY(),
							last.getDir()));
				break;
			case 'r':
				snake.add(new Cell(last.getX() - 1,last.getY(),
							last.getDir()));
				break;
		}
		snake.get(length).setLoc(outOfBounds(snake.get(length).getX()),
				outOfBounds(snake.get(length).getY()));
		length += 1;
		return true;
	}

	public boolean validKey(KeyEvent e) throws IOException{
		if (e.getCode() == KeyCode.ESCAPE) {
			if (!pause)
				pause = true;
			else
				pause = false;
		}
		if (e.getCode() == KeyCode.SPACE) {
			Load file = new Load(level, length, food, snake);
			gameOver = true;
		}
		switch (snake.get(0).getDir()) {
			case 'u':
			case 'd':
				if (e.getCode() == KeyCode.LEFT){
					snake.get(0).setDir('l');
				}
				if (e.getCode() == KeyCode.RIGHT){
					snake.get(0).setDir('r');
				}
				break;
			case 'l':
			case 'r':
				if (e.getCode() == KeyCode.DOWN){
					snake.get(0).setDir('d');
				}
				if (e.getCode() == KeyCode.UP){
					snake.get(0).setDir('u');
				}
				break;
		}
		return true;
	}

	public boolean gameLoop(GraphicsContext gc, Scene sc) {
		gc.setFill(Color.RED);
		printToScreen_F(gc, food);

		if (food.equals(snake.get(0))) {
			setFood();
			grow();
		}
		gc.setFill(Color.WHITE);
		for (int i = length - 1; i > 0; i--) {
			snake.get(i).setLoc(snake.get(i - 1).getX(), snake.get(i - 1).getY());
			printToScreen_S(gc, snake.get(i));
		}
		slither();
		snake.get(0).setLoc(outOfBounds(snake.get(0).getX()), outOfBounds(snake.get(0).getY()));
		gc.setFill(Color.RED);
		printToScreen_S(gc, snake.get(0));
		for (int i = 1; i < length; i++) {
			if (snake.get(0).equals(snake.get(i))) {
				int score = length * 100;
				gameOver = true;
				gc.fillRect(140, 140, 100, 75);
				gc.setFill(Color.WHITE);
				gc.fillText("Game Over!", 152, 152, 50);
				gc.fillText("Score:", 172, 180, 30);
				gc.fillText(String.valueOf(score), 220, 180, 30);
				return gameOver;
			}
		}

		return gameOver;
	}

	//Checks whether snake is out of bounds
	public static int outOfBounds(int X) {
		if (X == -1)
			return 15;
		else if (X == 16)
			return 0;
		else
			return X;
	}

	//Prints the snake Cell to screen;
	private void printToScreen_S(GraphicsContext gc, Cell cell) {
		gc.fillRect(cell.getX() * cellSize + 1, cell.getY() * cellSize + 1, cellSize - 2, cellSize - 2);
	}

	//Prints the food Cell to screen;
	private void printToScreen_F(GraphicsContext gc, Cell food) {
		gc.fillOval(food.getX() * cellSize, food.getY() * cellSize, cellSize, cellSize);
	}

	//Moves head depending on the button pressed
	private void slither() {
		snake.get(0).move(snake.get(0).getDir());
	}
}

