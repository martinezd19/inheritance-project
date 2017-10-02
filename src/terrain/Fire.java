package terrain;

import exceptions.LocationOutOfBoundsException;

import java.io.IOException;
import java.net.URL;

public class Fire
        extends Terrain {

    public Fire(int x, int y)
            throws IOException, LocationOutOfBoundsException {
        super(x, y, MovementType.SLOW, TerrainType.FIRE, 3, new URL("file:fire.png"), 2, 2);
    }

    @Override
    public void performInteractionAction() {
        System.out.println("It's a fire!");
    }

    @Override
    public boolean canMoveHere() {
        return false;
    }
}