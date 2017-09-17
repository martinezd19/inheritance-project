package terrain;

import exceptions.LocationOutOfBoundsException;

import java.net.URL;
import java.io.IOException;

public class Grass
        extends TerrainAbstract {

    public Grass(int x, int y)
            throws IOException, LocationOutOfBoundsException {
        super(x, y, MovementType.NORMAL, TerrainType.GRASS, new URL("file:grass.png"));
    }

    @Override
    public void performInteractionAction() {
        System.out.println("It's grass!");
    }
}