package terrain;

import exceptions.LocationOutOfBoundsException;

import java.io.IOException;
import java.net.URL;

public class Stone
        extends Terrain {

    public Stone(int x, int y)
            throws IOException, LocationOutOfBoundsException {
        super(x, y, MovementType.BLOCKED, TerrainType.STONE, 1, new URL("file:stone.png"));
    }

    @Override
    public void performInteractionAction() {
        System.out.println("It's stone!");
    }
}