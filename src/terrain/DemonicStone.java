package terrain;

import exceptions.LocationOutOfBoundsException;

import java.io.File;
import java.io.IOException;

public class DemonicStone extends Terrain {

    private final String DIRECTORY = "assets/floor/demonic_stone";
    private final String SOUND_FILE = "assets/sound/stone.wav";

    public DemonicStone(int x, int y)
            throws IOException, LocationOutOfBoundsException {
        super(x, y, Terrain.MovementType.NORMAL, TerrainType.DEMONIC_STONE, 0, 1, 1);

        setTerrainImage(pickRandomImageInDir(DIRECTORY));
    }

    @Override
    public void performInteractionAction() {
        playSound(new File(SOUND_FILE));
    }

    @Override
    public boolean canMoveHere() {
        return true;
    }
}
