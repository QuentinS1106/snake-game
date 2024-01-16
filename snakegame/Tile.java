package snakegame;

// Represents one tile of the game board
public class Tile {
    private TileType type;
    private int snakeTileNumber;

    public Tile (TileType type) {
        this.type = type;
        this.snakeTileNumber = 0;
    }

    public void addFood() {
        this.type = TileType.FOOD;
    }

    public void addSnakeBlock() {
        this.type = TileType.SNAKE;
        this.snakeTileNumber = 1;
    }

    public TileType getType() {
        return this.type;
    }
    
    public int getTileNumber() {
        return this.snakeTileNumber;
    }

    public void updateSnakeTileNumber(int len) {
        if (this.snakeTileNumber < len) {
            this.snakeTileNumber++;
        } else {
            this.snakeTileNumber = 0;
            this.type = TileType.EMPTY;
        }
    }

}