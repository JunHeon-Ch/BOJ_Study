package algo_14888;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    // + - * /
    static int n;
    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;
    static int[] num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        num = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        int[] op = new int[4];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 4; i++) {
            op[i] = Integer.parseInt(st.nextToken());
        }
        dfs(op[0], op[1], op[2], op[3], 1, num[0]);

        bw.write(max + "\n");
        bw.write(min + "\n");
        bw.close();

    }

    static void dfs(int op1, int op2, int op3, int op4, int cnt, int sum) {
        if (cnt == n) {
            max = Math.max(max, sum);
            min = Math.min(min, sum);
        }
        if (op1 > 0) {
            dfs(op1 - 1, op2, op3, op4, cnt + 1, sum + num[cnt]);
        }
        if (op2 > 0) {
            dfs(op1, op2 - 1, op3, op4, cnt + 1, sum - num[cnt]);
        }
        if (op3 > 0) {
            dfs(op1, op2, op3 - 1, op4, cnt + 1, sum * num[cnt]);
        }
        if (op4 > 0) {
            dfs(op1, op2, op3, op4 - 1, cnt + 1, sum / num[cnt]);
        }
    }

}
