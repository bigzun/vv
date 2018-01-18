package com.bigzun.sdk.utils;

import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by namnh40 on 10/7/2017.
 */

public class FileUtils {
    private static final String TAG = "FileUtils";

    public static synchronized File makeDirectory(String dir_path) {
        File dir = new File(dir_path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }

    public static File makeFile(File dir, String file_path) {
        File file = null;
        if (dir.isDirectory()) {
            file = new File(file_path);
            if (file != null && !file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    Log.e(TAG, e);
                } finally {
                }
            }
        }
        return file;
    }

    public static boolean writeFile(File file, byte[] file_content) {
        boolean result;
        FileOutputStream fos;
        if (file != null && file.exists() && file_content != null) {
            try {
                fos = new FileOutputStream(file);
                try {
                    fos.write(file_content);
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    Log.e(TAG, e);
                }
            } catch (FileNotFoundException e) {
                Log.e(TAG, e);
            } catch (Exception e) {
                Log.e(TAG, e);
            }
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public static void setupFontUI(final View view, final Typeface tf) {
        if (view instanceof TextView) {
            ((TextView) view).setTypeface(tf);
        }
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupFontUI(innerView, tf);
            }
        }
    }

    public static void saveFile(String content, String filename) {
//        try {
//            String path = Constants.STORAGE.ROOT_FOLDER;
//            File createFolder = new File(path);
//            if (!createFolder.exists())
//                createFolder.mkdirs();
//            String filePath = path + "/" + filename;
//            FileWriter fileWriter = new FileWriter(filePath);
//            BufferedWriter out = new BufferedWriter(fileWriter);
//            out.write(content);
//            out.close();
//            fileWriter.close();
//        } catch (IOException e) {
//            Log.e(TAG, e);
//        } catch (SecurityException e) {
//            Log.e(TAG, e);
//        } catch (Exception e) {
//            Log.e(TAG, e);
//        }
    }

    public static String getFileCache(String filename) {
//        String path = Constants.STORAGE.ROOT_FOLDER + "/" + filename;
//
//        String line;
        String result = "";
//        File f = new File(path);
//        if (!f.exists()) {
//            return result;
//        }
//        try {
//            FileReader file = new FileReader(path);
//            BufferedReader in = new BufferedReader(file);
//            while ((line = in.readLine()) != null) {
//                result = result + line;
//            }
//            in.close();
//            file.close();
//        } catch (IOException e) {
//            Log.e(TAG, e);
//        } catch (Exception e) {
//            Log.e(TAG, e);
//        }
//
        return result;
    }
}

