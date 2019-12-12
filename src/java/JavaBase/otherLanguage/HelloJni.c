#include "HelloJni.h"
#include <stdio.h>
#include <jni.h>
/*
 * 这是displayHelloJni的Java本地实现 cl HelloJni.c -Fehello.dll -MD -LD
 */
JNIEXPORT void JNICALL Java_HelloJni_displayHelloJni(JNIEnv *env, jobject this) {
  jfieldID fldid;
  jint n, nn;

  (void)printf("Hello from a Native Method\n");

  if (this == NULL) {
    fprintf(stderr, "'this.' pointer is null\n");
    return;
  }

  if ((fldid = (*env)->GetFieldID(env,
    (*env)->GetObjectClass(env, this), "myNumber", "I")) == NULL) {
      fprintf(stderr, "GetFieldID failed");
      return;
    }

  n = (*env)->GetIntField(env, this, fldid); /* 检索myNumber */
  printf("\"myNumber\" value is %d\n", n);

  (*env)->SetIntField(env, this, fldid, ++n); /* 增加它！ */
  nn = (*env)->GetIntField(env, this, fldid);

  printf("\"myNumber\" value now %d\n", nn); /* 确保*/
  return;
}