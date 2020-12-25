public class LevelOrderTraversal {
    // Visit nodes on 0th level, then 1st level, then 2nd level, etc.

    public void levelOrder(Tree T, Action toDo) {
        for (int i = 0; i < T.height(); i += 1) {
            visitLevel(T, i, toDo);
        }
    }

    public void visitLevel(Tree T, int level, Action toDo) {
        if (T == null) {
            return;
        }
        if (level == 0) {
            toDo.visit(T.key);
        } else {
            visitLevel(T.left, level - 1, toDo);
            visitLevel(T.right, level - 1, toDo);
        }
    }
}

interface Action<Integer> {
    void visit(int key);
}
