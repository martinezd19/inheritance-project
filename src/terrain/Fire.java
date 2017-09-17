package terrain;

import exceptions.LocationOutOfBoundsException;

import java.net.URL;
import java.io.IOException;

public class Fire
        extends TerrainAbstract {

    public Fire(int x, int y)
            throws IOException, LocationOutOfBoundsException {
        super(x, y, MovementType.SLOW, TerrainType.FIRE, new URL("file:fire.png"));
    }

    @Override
    public void performInteractionAction() {
        System.out.println("It's a fire!");
    }
}