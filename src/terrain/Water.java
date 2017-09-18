package terrain;

import exceptions.LocationOutOfBoundsException;

import java.io.IOException;
import java.net.URL;

public class Water
        extends Terrain {

    public Water(int x, int y)
            throws IOException, LocationOutOfBoundsException {
        super(x, y, MovementType.SLOW, TerrainType.WATER, 0, new URL("file:water.png"));
    }

    @Override
    public void performInteractionAction() {
        System.out.println("It's water!");
    }
}