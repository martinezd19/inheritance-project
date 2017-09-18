package terrain;

import exceptions.LocationOutOfBoundsException;

import java.io.IOException;
import java.net.URL;

public class Tree
        extends Terrain {

    public Tree(int x, int y)
            throws IOException, LocationOutOfBoundsException {
        super(x, y, MovementType.BLOCKED, TerrainType.TREE, 1,
                /* Randomly picks one of two tree images */
                (Math.round(Math.random()) == 0 ? new URL("file:tree_1.png") : new URL("file:tree_2.png")));
    }

    @Override
    public void performInteractionAction() {
        System.out.println("It's a tree!");
    }
}