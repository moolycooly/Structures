
import java.io.*;
import java.util.Locale;
import java.util.Random;
import java.util.StringTokenizer;

public class Competitive implements Runnable {
    private final static int MAX_INT = 2000000000;
    private final static int MOD = 1000000000;
    private final static int MAX_STACK_SIZE = 128;
    private void solve()   {

    }

    //////////////////////////////////////////////////////////////////

    long sqrtLong(long x) {
        long root = (long)Math.sqrt(x);
        while (root * root > x) --root;
        while ((root + 1) * (root + 1) <= x) ++root;
        return root;
    }

    //////////////////////////////////////////////////////////////////

    private boolean yesNo(boolean yes) {
        return yesNo(yes, "YES", "NO");
    }

    private boolean yesNo(boolean yes, String yesString, String noString) {
        out.println(yes ? yesString : noString);
        return yes;
    }

    //////////////////////////////////////////////////////////////////

    private long readLong() {
        return Long.parseLong(readString());
    }

    private int[] readIntArray(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; ++i) a[i] = readInt();
        return a;
    }

    private int readInt() {
        return Integer.parseInt(readString());
    }

    private String readString() {
        while (!tok.hasMoreTokens()) {
            String nextLine = readLine();
            if (null == nextLine) return null;
            tok = new StringTokenizer(nextLine);
        }

        return tok.nextToken();
    }

    private String readLine() {
        try {
            return in.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //////////////////////////////////////////////////////////////////

    private BufferedReader in;
    private StringTokenizer tok;
    private PrintWriter out;

    private void initFileIO(String inputFileName, String outputFileName) throws FileNotFoundException {
        in = new BufferedReader(new FileReader(inputFileName));
        out = new PrintWriter(outputFileName);
    }

    private void initConsoleIO() throws IOException {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    private void initIO() throws IOException {
        Locale.setDefault(Locale.US);

        String fileName = "";

        if (!fileName.isEmpty()) {
            initFileIO(fileName + ".in", fileName + ".out");
        } else {
            if (new File("input.txt").exists()) {
                initFileIO("input.txt", "output.txt");
            } else {
                initConsoleIO();
            }
        }

        tok = new StringTokenizer("");
    }

    //////////////////////////////////////////////////////////////////

    public void run() {
        try {
            long timeStart = System.currentTimeMillis();

            initIO();
            solve();
            out.close();

//            long timeEnd = System.currentTimeMillis();
//            System.err.println("Time(ms) = " + (timeEnd - timeStart));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        new Thread(null, new Competitive(), "", MAX_STACK_SIZE * (1L << 20)).start();
    }
    ////
    static Random random = new Random();

    static class Treap {
        public int x;
        public int y;
        public Treap Left;
        public Treap Right;

        private Treap(int x, int y, Treap Left, Treap Right) {
            this.x = x;
            this.y = y;
            this.Left = Left;
            this.Right = Right;
        }

        private Treap(int x, int y) {
            this.x=x;
            this.y=y;
        }

        public static Treap merge(Treap L, Treap R) {
            if (L == null) return R;
            if (R == null) return L;
            if (L.y > R.y) {
                Treap T = merge(L.Right, R);
                return new Treap(L.x, L.y, L.Left, T);
            } else {
                Treap T = merge(L, R.Left);
                return new Treap(R.x, R.y, T, R.Right);
            }
        }
        public Treap[] split(int x, Treap L, Treap R) {
            Treap newTree = null;
            if (this.x <= x) {

                if(this.Right != null) {
                    var arr = this.Right.split(x, null, R);
                    newTree = arr[0];
                    R = arr[1];
                }
                L = new Treap(this.x, this.y, this.Left, newTree);
                return new Treap[]{L,R};

            }
            else {
                if(this.Left != null){
                    var arr = this.Left.split(x, L, null);
                    newTree = arr[1];
                    L =arr[0];
                }
                R = new Treap(this.x, this.y, newTree, this.Right);
                return new Treap[]{L,R};
            }
        }
        public static Treap add(int x, Treap t)  {
            if(t==null) return new Treap(x, random.nextInt());
            Treap l = null, r =null;
            var treap = t.split(x,l,r);
            Treap m = new Treap(x, random.nextInt());
            return merge(merge(treap[0],m),treap[1]);
        }
        
    }

}

