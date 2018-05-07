package ru.homework;

public class Main {
    static final int size = 10_000_000;
    static final int h = size / 2;
    static long timeMethod1;/// дельта времени 1 метода
    static long timeMethod2;/// дельта времени 2 метода
    public static void main(String[] args) {
	method1();
	method2();
    diffTime();/// рассчет разницы времени
    }
    public static void method1(){
        float[] arr = new float[size];

        for (int i =0; i<size;i++)arr[i]=1f;

        long a = System.currentTimeMillis();

        for (int i = 0; i < size; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) *
                    Math.cos(0.4f + i / 2));
        }

        System.out.print("Time first method: ");
        timeMethod1=System.currentTimeMillis() - a;
        System.out.println(timeMethod1);

    }

    public static void method2(){
        float[] arr = new float[size];
        float[] a1 = new float[h];
        float[] a2 = new float[h];

        for (int i =0; i<size;i++)arr[i]=1f;

        long a = System.currentTimeMillis();

        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        new Thread(() -> {
    for (int i = 0; i < h; i++) {
        a1[i] = (float)(a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) *
                Math.cos(0.4f + i / 2));
    }
}).start();

        new Thread(() -> {
            for (int i = 0; i < h; i++) {
                a2[i] = (float)(a2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) *
                        Math.cos(0.4f + i / 2));
            }
        }).start();

        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);

        System.out.print("Time second method: ");
        timeMethod2=System.currentTimeMillis() - a;
        System.out.println(timeMethod2);

    }
    public static void diffTime(){
        System.out.println("The second method is faster than the first method by " +(timeMethod1-timeMethod2)+" milliseconds");
    }

}
