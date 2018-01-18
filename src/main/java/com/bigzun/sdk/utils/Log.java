package com.bigzun.sdk.utils;

import com.bigzun.sdk.BuildConfig;

public class Log {
    private static final boolean ENABLE_LOG = BuildConfig.DEBUG;
    private static final String LOG_TAG = BuildConfig.LOG_TAG;
    private static final boolean IS_LOG_LINE = BuildConfig.IS_LOG_LINE;

    public static void i(String message) {
        i(LOG_TAG, message);
    }

    public static void d(String message) {
        d(LOG_TAG, message);
    }

    public static void e(String message) {
        e(LOG_TAG, message);
    }

    public static void i(String tag, String message) {
        if (ENABLE_LOG) {
            if (IS_LOG_LINE) {
                StackTraceElement[] stackTraceElement = Thread.currentThread().getStackTrace();
                int currentIndex = -1;
                for (int i = 0; i < stackTraceElement.length; i++) {
                    if (stackTraceElement[i].getMethodName().compareTo("i") == 0) {
                        currentIndex = i + 1;
                        break;
                    }
                }
                String fullClassName = stackTraceElement[currentIndex].getClassName();
                String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
                String methodName = stackTraceElement[currentIndex].getMethodName();
                String lineNumber = String.valueOf(stackTraceElement[currentIndex].getLineNumber());
                android.util.Log.i(tag, LOG_TAG + message + "\nat " + fullClassName + "." + methodName + "(" + className + ".java:" + lineNumber + ")");
            } else {
                android.util.Log.i(tag, LOG_TAG + message);
            }
        }
    }

    public static void i(String tag, String message, Throwable throwable) {
        if (ENABLE_LOG && throwable != null) {
            android.util.Log.i(tag, LOG_TAG + message, throwable);
        }
    }

    public static void e(String tag, String message) {
        if (ENABLE_LOG) {
            if (IS_LOG_LINE) {
                StackTraceElement[] stackTraceElement = Thread.currentThread().getStackTrace();
                int currentIndex = -1;
                for (int i = 0; i < stackTraceElement.length; i++) {
                    if (stackTraceElement[i].getMethodName().compareTo("e") == 0) {
                        currentIndex = i + 1;
                        break;
                    }
                }
                String fullClassName = stackTraceElement[currentIndex].getClassName();
                String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
                String methodName = stackTraceElement[currentIndex].getMethodName();
                String lineNumber = String.valueOf(stackTraceElement[currentIndex].getLineNumber());
                android.util.Log.e(tag, LOG_TAG + message + "\nat " + fullClassName + "." + methodName + "(" + className + ".java:" + lineNumber + ")");
            } else {
                android.util.Log.e(tag, LOG_TAG + message);
            }
        }
    }

    public static void e(String tag, Throwable e) {
        if (ENABLE_LOG && e != null) {
            android.util.Log.e(tag, LOG_TAG + e.getMessage(), e);
        }
    }

    public static void e(String tag, String message, Throwable e) {
        if (ENABLE_LOG && e != null) {
            android.util.Log.e(tag, LOG_TAG + message, e);
        }
    }

    public static void d(String tag, String message) {
        if (ENABLE_LOG) {
            if (IS_LOG_LINE) {
                StackTraceElement[] stackTraceElement = Thread.currentThread().getStackTrace();
                int currentIndex = -1;
                for (int i = 0; i < stackTraceElement.length; i++) {
                    if (stackTraceElement[i].getMethodName().compareTo("d") == 0) {
                        currentIndex = i + 1;
                        break;
                    }
                }
                String fullClassName = stackTraceElement[currentIndex].getClassName();
                String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
                String methodName = stackTraceElement[currentIndex].getMethodName();
                String lineNumber = String.valueOf(stackTraceElement[currentIndex].getLineNumber());
                android.util.Log.d(tag, LOG_TAG + message + "\nat " + fullClassName + "." + methodName + "(" + className + ".java:" + lineNumber + ")");
            } else {
                android.util.Log.d(tag, LOG_TAG + message);
            }
        }
    }

    public static void d(String tag, String message, Throwable throwable) {
        if (ENABLE_LOG && throwable != null) {
            android.util.Log.d(tag, LOG_TAG + message, throwable);
        }
    }

    public static void v(String tag, String message) {
        if (ENABLE_LOG) {
            if (IS_LOG_LINE) {
                StackTraceElement[] stackTraceElement = Thread.currentThread().getStackTrace();
                int currentIndex = -1;
                for (int i = 0; i < stackTraceElement.length; i++) {
                    if (stackTraceElement[i].getMethodName().compareTo("v") == 0) {
                        currentIndex = i + 1;
                        break;
                    }
                }
                String fullClassName = stackTraceElement[currentIndex].getClassName();
                String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
                String methodName = stackTraceElement[currentIndex].getMethodName();
                String lineNumber = String.valueOf(stackTraceElement[currentIndex].getLineNumber());
                android.util.Log.v(tag, LOG_TAG + message + "\nat " + fullClassName + "." + methodName + "(" + className + ".java:" + lineNumber + ")");
            } else {
                android.util.Log.v(tag, LOG_TAG + message);
            }
        }
    }

    public static void v(String tag, String message, Throwable throwable) {
        if (ENABLE_LOG && throwable != null) {
            android.util.Log.v(tag, LOG_TAG + message, throwable);
        }
    }

    public static void w(String tag, String message) {
        if (ENABLE_LOG) {
            if (IS_LOG_LINE) {
                StackTraceElement[] stackTraceElement = Thread.currentThread().getStackTrace();
                int currentIndex = -1;
                for (int i = 0; i < stackTraceElement.length; i++) {
                    if (stackTraceElement[i].getMethodName().compareTo("w") == 0) {
                        currentIndex = i + 1;
                        break;
                    }
                }
                String fullClassName = stackTraceElement[currentIndex].getClassName();
                String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
                String methodName = stackTraceElement[currentIndex].getMethodName();
                String lineNumber = String.valueOf(stackTraceElement[currentIndex].getLineNumber());
                android.util.Log.w(tag, LOG_TAG + message + "\nat " + fullClassName + "." + methodName + "(" + className + ".java:" + lineNumber + ")");
            } else {
                android.util.Log.w(tag, LOG_TAG + message);
            }
        }
    }

    public static void w(String tag, String message, Throwable throwable) {
        if (ENABLE_LOG && throwable != null) {
            android.util.Log.w(tag, LOG_TAG + message, throwable);
        }
    }
}