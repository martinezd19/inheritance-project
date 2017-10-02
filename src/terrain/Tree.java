package terrain;

import exceptions.LocationOutOfBoundsException;

import java.io.IOException;
import java.net.URL;

public class Tree
        extends Terrain {

    public Tree(int x, int y)
            throws IOException, LocationOutOfBoundsException {
        super(x, y, MovementType.BLOCKED, TerrainType.TREE, 1, new URL("file:tree_2.png"), 3, 3);
    }

    @Override
    public void performInteractionAction() {
        System.out.println("It's a tree!");
    }

    @Override
    public boolean canMoveHere() {
        return false;
    }
}