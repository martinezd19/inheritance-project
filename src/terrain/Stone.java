package terrain;

import exceptions.LocationOutOfBoundsException;

import java.io.IOException;
import java.net.URL;

public class Stone
        extends Terrain {

    public Stone(int x, int y)
            throws IOException, LocationOutOfBoundsException {
        super(x, y, MovementType.BLOCKED, TerrainType.STONE, 1, new URL("file:stone.png"), 1, 1);
    }

    @Override
    public void performInteractionAction() {
        System.out.println("It's stone!");
    }

    @Override
    public boolean canMoveHere() {
        return false;
    }
}