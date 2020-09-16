public class TestPunkRock {
    public static void main(String[] args) {
        Rock newrock = new Rock(2);
        PunkRock newpunkrock = new PunkRock(newrock);
        Rock[] rockarray = newpunkrock.myBand();
        rockarray[0] = new Rock(3);
    }
}
