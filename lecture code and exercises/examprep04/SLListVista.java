import java.util.NoSuchElementException;

public class SLListVista extends SLList {

    @Override
    public int indexOf(int x) {
        int y = super.indexOf(x);
        if (y == -1) {
            throw new NoSuchElementException();
        }
        return y;

    }
}
