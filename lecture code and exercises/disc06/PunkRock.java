public class PunkRock {
    private final Rock[] band;

    public PunkRock(Rock yRoad) {
        band = new Rock[]{yRoad};
    }

    public Rock[] myBand() {
        return band;
    }
}
// It is possible to access and modify the contents of PunkRock’s private array through
// its public myBand() method, so this class is mutable.
