package algo_10973;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[] num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        num = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        if (permutation(n)) {
            for (int k : num) {
                bw.write(k + " ");
            }
        } else bw.write(-1 + "");

        bw.flush();
        bw.close();


    }

    static boolean permutation(int n) {
        int i = n - 1;
        while (i > 0 && num[i - 1] <= num[i]) i--;
        if (i <= 0) return false;
        int j = n - 1;
        while (num[j] >= num[i - 1]) j--;
        swap(j, i - 1);
        j = n - 1;
        while (i < j) {
            swap(i, j);
            i++;
            j--;
        }
        return true;
    }

    static void swap(int a, int b) {
        int temp = num[a];
        num[a] = num[b];
        num[b] = temp;
    }
}
