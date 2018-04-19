#include <jni.h>
#include <string>

int numberAdd(int a,int b);

extern "C"
JNIEXPORT jint JNICALL
Java_com_international_shopping_natives_CppNative_intFromJNI(JNIEnv *env, jobject instance, jint a,
                                                             jint b) {

    int result = numberAdd(a,b);
    return result;
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_international_shopping_natives_CppNative_stringFromJNI(JNIEnv *env, jobject instance) {

    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

int numberAdd(int s ,int v){
    return s+v;
}