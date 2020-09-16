public class TestBadIntegerStack {
    public static void main(String[] args) {
        try {
            BadIntegerStack bis = new BadIntegerStack();
            bis.pop();
        } catch (NullPointerException e) {
            System.out.println("Success!");
        }

//        BadIntegerStack stack = new BadIntegerStack();
//        stack.push(1);
//        stack.top.prev = stack.top;
//        while (!stack.isEmpty()) {
//            stack.pop();
//        }
//        System.out.println("This print statement is unreachable!");
    }
}
