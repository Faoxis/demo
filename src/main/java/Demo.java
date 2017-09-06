public class Demo {
    public static void main(String[] args) {
        int N = 100000;

        for (int i = 0; i < N; ++i) {
            new Thread(() -> {
                A a = new A();
                System.out.println(a.v);
            }).start();
        }
    }

    static class A {
        static int i = 0;
        final int v;

        A() {
            v = i++;
        }

    }
}
