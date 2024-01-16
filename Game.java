package snakegame;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private final int rows;
    private final int columns;
    private static Direction direction = Direction.RIGHT;
    private int snakeHeadRow;
    private int snakeHeadColumn;
    private int length = 1;
    private List<Tile> snakeTiles;
    public Tile[][] board;
    public static boolean hitWall = false;
    public static boolean hitSnake = false;

    public Game(int rows, int columns, int startRow, int startColumn) {
        this.rows = rows;
        this.columns = columns;
        this.board = new Tile[columns][rows];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new Tile(TileType.EMPTY);
            }
        }
        this.board[startColumn - 1][startRow - 1].addSnakeBlock();
        this.snakeTiles = new ArrayList<Tile>(10);
        //System.out.println(snakeTiles);
        this.snakeTiles.add(board[startColumn-1][startRow-1]);
        this.snakeHeadRow = startRow - 1;
        this.snakeHeadColumn = startColumn - 1;
    }

    public void updateSnake() {
        for (int i = 0; i < this.snakeTiles.size(); i++) {
            snakeTiles.get(i).updateSnakeTileNumber(this.length);
            if (snakeTiles.get(i).getTileNumber() == 0) {
                snakeTiles.remove(i);
                i--;
            }
        }

        this.move();
        this.checkCollision();

    }

    /**
     * Moves the snake one tile, with the direction specified by this.direction.
     */
    public void move() {
        switch (direction) {
            case UP:
                snakeTiles.add(0, board[snakeHeadColumn][snakeHeadRow - 1]);
                snakeHeadRow--;
                break;
            case DOWN:
                snakeTiles.add(0, board[snakeHeadColumn][snakeHeadRow + 1]);
                snakeHeadRow++;
                break;
            case RIGHT:
                snakeTiles.add(0, board[snakeHeadColumn + 1][snakeHeadRow]);
                snakeHeadColumn++;
                break;
            case LEFT:
                snakeTiles.add(0, board[snakeHeadColumn - 1][snakeHeadRow]);
                snakeHeadColumn--;
                break;
        }
    }

    /**
     * Checks if the snake has hit itself.
     */
    public void checkCollision() {
        switch (board[snakeHeadColumn][snakeHeadRow].getType()) {
            case EMPTY:
                board[snakeHeadColumn][snakeHeadRow].addSnakeBlock();
                break;
            case SNAKE:
                hitSnake = true;
                break;
            case FOOD:
                board[snakeHeadColumn][snakeHeadRow].addSnakeBlock();
                length += 3;
                spawnFood();
                break;
        }
    }

    /**
     * Spawns a piece of food randomly on the board.
     */
    public void spawnFood() {
        int foodRow = (int) (Math.random() * rows);
        int foodCol = (int) (Math.random() * columns);
        if (board[foodCol][foodRow].getType() == TileType.EMPTY) {
            board[foodCol][foodRow].addFood();
        } else {
            spawnFood();
        }
    }
    /**
     * Changes the direction that the snake is moving in.
     * @param d the direction to change to.
     */
    public static void changeDirection(Direction d) {
        direction = d;
    }
    /**
     * Gets the number of rows in the game.
     * @return the amount of rows.
     */
    public int getRows() {
        return this.rows;
    }
    public int getCols() {
        return this.columns;
    }
    public static Direction getDirection() {
        return direction;
    }
    public int getLength() {
        return length;
    }
}
