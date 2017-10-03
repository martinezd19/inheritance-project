package terrain;

import exceptions.LocationOutOfBoundsException;

import java.io.File;
import java.io.IOException;

public class Acid extends Terrain {

    private final String DIRECTORY = "assets/floor/acid";
    private final String SOUND_FILE = "assets/sound/acid.wav";

    public Acid(int x, int y)
            throws IOException, LocationOutOfBoundsException {
        super(x, y, Terrain.MovementType.NORMAL, TerrainType.ACID, 0, 1, 1);

        setTerrainImage(pickRandomImageInDir(DIRECTORY));
    }

    @Override
    public void performInteractionAction() {
        playSound(new File(SOUND_FILE));
    }

    @Override
    public boolean canMoveHere() {
        return false;
    }
}
