package terrain;

import exceptions.LocationOutOfBoundsException;

import java.net.URL;
import java.io.IOException;

public class Stone
        extends TerrainAbstract {

    public Stone(int x, int y)
            throws IOException, LocationOutOfBoundsException {
        super(x, y, MovementType.BLOCKED, TerrainType.STONE, new URL("file:stone.png"));
    }

    @Override
    public void performInteractionAction() {
        System.out.println("It's stone!");
    }
}