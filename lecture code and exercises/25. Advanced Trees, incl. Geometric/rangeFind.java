class rangeFind implements Action<String> {
    private Label min, max;
    public Set<Label> inRange;
    public rangeFind(Label min, Label max) {
        this.min = min; this.max = max;
        inRange = new HashSet<Label>();
    }

    void action(Tree<Label> T) {
        if (T.label ≼ max && T.label ≽ min) {
            inRange.add(T.label);
        }
    }
}
