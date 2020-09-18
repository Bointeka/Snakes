import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.util.Scanner;
import java.io.PrintWriter;
import java.util.ArrayList;
public class Load {
	private File file = new File("." + File.pathSeparator + "load.txt");
	public int level;
	public int length;
	public ArrayList<Cell> snake;
	public Cell food = new Cell();

	Load() throws IOException{
		read();
	}

	Load(int level, int length, Cell food, ArrayList<Cell> snake) throws IOException {
		this.level = level;
		this.length = length;
		this.snake = snake;
		this.food = food;
		write();
	}

	//Reads game from txt fil
	private void read () throws IOException{
		FileInputStream ifs = null;
		Scanner input = null; 
		try {
			ifs = new FileInputStream(file);
			input = new Scanner(ifs);
			level = input.nextInt();
			length = input.nextInt();
			food.setLoc(input.nextInt(), input.nextInt());
			for (int i = 0; i < length; i++) {
				snake.add(new Cell(input.nextInt(), input.nextInt(),
							input.next().charAt(0)));
			}
		}
		catch (IOException e) {
			System.out.println("Unable to read file");
		}
		finally{
			input.close();
			ifs.close();
		}
	}

	//Writes game to txt file
	private void write() throws IOException{
		FileOutputStream ofs = null; 
		PrintWriter print = null;
		try {
			ofs = new FileOutputStream(file);
			print = new PrintWriter(ofs);
			print.printf("%d %d %d %d ", level, length, food.getX(), food.getY()); 
			for (int i = 0; i < length; i++) {
				print.printf("%d %d %c", snake.get(i).getX(), snake.get(i).getY(), snake.get(i).getDir());
			}
			print.flush();	
		}
		catch (IOException e) {
			System.out.println("Unable to read file");
		}
		finally {
			print.close();
			ofs.close();
		}
	}
}
