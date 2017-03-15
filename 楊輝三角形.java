/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * 考題 : 不用遞迴，只用一個一維陣列，列印出楊輝三角形。
 */

/**
 *用一半陣列、省下一半記憶體，提昇效率
 *
 * @author clark
 */
import java.util.Scanner;

public class 楊輝三角形 {

    public static int levLen(int m) {
        int levLen = m / 2 + 1;
        return levLen;
    }

    public static int aryLen(int m) {
        int len1;
        if (m % 2 == 0) {   //雙數求總和:上底加下底、乘以高除以2, 再乘以2倍。
            len1 = (int) Math.pow((m / 2 + 1), 2) - 1;
        } else {    //單數求總和:上底加下底、乘以高除以2, 再乘以2倍、再減去1個上底。
            len1 = (int) Math.pow((m / 2 + 1), 2) - 1 + (m / 2 + 1);
        }
        return len1;
    }

    public static void calValue(int[] y, int m) {
        for (int i = 1, j = -1; i <= m; i++, j++) {
//cal第1層開始每層第1個元素值、完成第1層。
            y[aryLen(i) - levLen(i)] = 1;
//cal第2層開始每層第2個元素值、完成第2、3層。
            if (i > 1) {
                //應是、慢速 y[aryLen(i) - levLen(i) + 1] = j + 1;
                y[aryLen(i - 1) + 1] = j + 1;
            }
        }
//cal第4層開始之所有第3個元素值、完成第4、5層。
        for (int i = 4, j = 2; i <= m; i++, j++) {
            //應是、慢速 y[aryLen(i) - levLen(i) + 2] = y[aryLen(i - 1) - levLen(i - 1) + 2] + j;
            y[aryLen(i - 1) + 2] = y[aryLen(i - 2) + 2] + j;
        }
//cal第6層開始之第4個元素開始所有元素值
        for (int i = 6; i <= m; i++) {
            for (int j = aryLen(i - 1) + 3, k = 2; j < aryLen(i); j++, k++) {
                y[j] = y[aryLen(i - 2) + k] + y[aryLen(i - 2) + k + 1];
            }
            if (i % 2 == 0) {
                y[aryLen(i) - 1] = y[aryLen(i) - 2];
            }
        }
    }

    public static void show(int[] y, int m) {
        for (int i = 1, k = m; i <= m; i++, k--) {
            for (int j = 1; j < k; j++) {
                System.out.print("  ");
            }

            for (int j = aryLen(i) - levLen(i); j < aryLen(i); j++) {
                System.out.print(y[j] + "  ");
            }

            if (i % 2 == 0) {
                for (int j = aryLen(i) - 3; j >= aryLen(i) - levLen(i); j--) {
                    System.out.print(y[j] + "  ");
                }
            } else {
                for (int j = aryLen(i) - 2; j >= aryLen(i) - levLen(i); j--) {
                    System.out.print(y[j] + "  ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("楊輝三角形共 M 層之架構圖 :");
        System.out.print("請輸入層數 M = ");
        int m = sc.nextInt();
        if (m < 1) {
            return;
        }

        int[] aryY = new int[aryLen(m)];

        calValue(aryY, m);

        show(aryY, m);
    }
}
