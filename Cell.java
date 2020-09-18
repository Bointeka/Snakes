/**
*  Representation of the objects within the game 
*  @author  Jeremy Okeyo  
*/
public class Cell {
        private int X;
        private int Y;
        private char direction; //Used to determine the orientation of the snake. u/d/l/r is up/down/left/right

        /*
        *  Constuctor for food cell
        */
        public Cell() {
                X = 0;
                Y = 0;
                direction = 'n';
        }

        /*
         *  Constuctor for snake cell
         *  @param X Prosition of snake in Y axis
            @param Y Position of snake in X axis
            @param direction current direction of cell
         */
        public Cell(int X, int Y, char direction) {
                this.X = game.outOfBounds(X);
                this.Y =        game.outOfBounds(Y);
                this.direction = direction;
        }

        public char getDir() {
                return direction;
        }
        public int getX(){
                return X;
        }
        public int getY(){
                return Y;
        }
        public void setDir(char direction) {
                this.direction = direction;
        }
        public void setLoc(int X, int Y){
                this.Y = Y;
                this.X = X;
        }

        /**
        * Moves cell depending on the direction of the cell
        * @param dir current direction
        * @return new position depending on the direction
        */
        public void move(char dir) {
                if (dir == 'l')
                        X -= 1;
                else if (dir == 'r')
                        X += 1;
                else if (dir == 'u')
                        Y -= 1;
                else if (dir == 'd')
                        Y += 1;
        }

        /**
        * Checks whether two cells overlap
        * @param cell another cell
        * @ return true, if they overlap, false if they don't
        */
        //@Override
        public boolean equals(Cell cell) {
                if (X == cell.getX() && Y == cell.getY())
                        return true;
                else
                        return false;
        }
}

