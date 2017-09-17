package terrain;

import exceptions.LocationOutOfBoundsException;

import java.net.URL;
import java.io.IOException;

public class Tree
        extends TerrainAbstract {

    public Tree(int x, int y)
            throws IOException, LocationOutOfBoundsException {
        super(x, y, MovementType.BLOCKED, TerrainType.TREE,
                /* Randomly picks one of two tree images */
                (Math.round(Math.random()) == 0 ? new URL("file:tree1.png") : new URL("file:tree2.png")));
    }

    @Override
    public void performInteractionAction() {
        System.out.println("It's a tree!");
    }
}