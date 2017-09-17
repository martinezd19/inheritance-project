package terrain;

import exceptions.LocationOutOfBoundsException;

import java.net.URL;
import java.io.IOException;

public class Water
        extends TerrainAbstract {

    public Water(int x, int y)
            throws IOException, LocationOutOfBoundsException {
        super(x, y, MovementType.SLOW, TerrainType.WATER, new URL("file:grass.png"));
    }

    @Override
    public void performInteractionAction() {
        System.out.println("It's water!");
    }
}