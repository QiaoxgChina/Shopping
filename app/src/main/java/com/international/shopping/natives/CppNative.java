package com.international.shopping.natives;

public class CppNative {
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public static native String stringFromJNI();


    public static native int intFromJNI(int a,int b);
}
