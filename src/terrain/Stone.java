package terrain;

import exceptions.LocationOutOfBoundsException;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Stone extends Terrain {

    private final String IMAGE_FILE = "assets/floor/stone.png";

    public Stone(int x, int y)
            throws IOException, LocationOutOfBoundsException {
        super(x, y, Terrain.MovementType.NORMAL, TerrainType.COBBLE, 1, 1, 1);

        setTerrainImage(ImageIO.read(new File(IMAGE_FILE)));
    }

    @Override
    public void performInteractionAction() {
    }

    @Override
    public boolean canMoveHere() {
        return false;
    }
}
