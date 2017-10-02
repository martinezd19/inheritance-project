package terrain;

import exceptions.LocationOutOfBoundsException;

import java.io.IOException;
import java.net.URL;

public class Grass
        extends Terrain {

    public Grass(int x, int y)
            throws IOException, LocationOutOfBoundsException {
        super(x, y, MovementType.NORMAL, TerrainType.GRASS, 0, new URL("file:grass.png"), 1, 1);
    }

    @Override
    public void performInteractionAction() {
        System.out.println("It's grass!");
    }

    @Override
    public boolean canMoveHere() {
        return true;
    }
}