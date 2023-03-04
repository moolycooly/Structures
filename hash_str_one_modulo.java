package competitive;

import java.io.*;
import java.util.Locale;
import java.util.StringTokenizer;

public class Competitive implements Runnable {

    private final static int MAX_STACK_SIZE = 128;
    private final static int STRING_MAX_SIZE = 100001;

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
    private void solve()   {
        Hash.initializingPow();
    }
    static class Hash {
        static long[] power = new long[STRING_MAX_SIZE];
        long[] h = new long[STRING_MAX_SIZE];

        static final long p = 271;
        static final long mod = 1000000007;

        int idx;

        public static void initializingPow() {
            power[0] = 1;
            for(int i = 1; i < STRING_MAX_SIZE; i++)
                power[i] = power[i-1]*p%mod;
        }
        public void add(int c) {
            if(idx==0)
                h[idx++] = c;
            else {
                h[idx] = (h[idx-1]*p%mod+c)%mod;
                idx++;
            }
        }
        public long get_hash(int l, int r) {
            if (l == 0)
                return h[r];
            return (h[r] - h[l - 1] * power[r - l + 1] % mod + mod) % mod;
        }

    }

}
