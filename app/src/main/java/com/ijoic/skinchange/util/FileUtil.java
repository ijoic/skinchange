package com.ijoic.skinchange.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import org.jetbrains.annotations.Contract;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 文件工具
 *
 * @author ijoic verstlim@126.com
 * @version 1.0
 */
public class FileUtil {

  private static final int BUFFER_SIZE = 1024;

  /**
   * 从Assets中复制到置顶位置
   *
   * @param context 上下文
   * @param assetPath Asset路径
   * @param targetDirectory 目标文件目录
   * @param targetFileName 目标文件名称
   */
  public static void copyFromAsset(@NonNull final Context context, @NonNull final String assetPath,
      @NonNull final String targetDirectory, @NonNull final String targetFileName) {

    new Thread() {
      @Override
      public void run() {
        InputStream is = readAssetInput(context, assetPath);
        OutputStream os = readFileOutput(targetDirectory, targetFileName);

        try {
          if (is != null && os != null) {
            copyFile(is, os);
          }

        } catch (IOException e) {
          e.printStackTrace();

        } finally {
          closeQuietly(is);
          closeQuietly(os);
        }
      }
    }.start();
  }

  private static void copyFile(@NonNull InputStream is, @NonNull OutputStream os) throws IOException {
    int bufferSize = BUFFER_SIZE;
    byte[] buffer = new byte[bufferSize];
    int readCount;

    while (true) {
      readCount = is.read(buffer, 0, bufferSize);

      if (readCount == -1) {
        break;
      }
      if (readCount > 0) {
        os.write(buffer, 0, readCount);
      }
    }
  }

  private static @Nullable InputStream readAssetInput(@NonNull Context context, @NonNull String assetPath) {
    try {
      return context.getAssets().open(assetPath);

    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  private static @Nullable OutputStream readFileOutput(@NonNull String targetDirectory, @NonNull String targetFileName) {
    if (!makeDirs(targetDirectory)) {
      Log.i("FileUtil", "make dir [" + targetDirectory + "] failed");
      return null;
    }
    String targetPath = targetDirectory + File.separator + targetFileName;
    File targetFile = new File(targetPath);

    if (!targetFile.exists()) {
      try {
        if (!targetFile.createNewFile()) {
          Log.i("FileUtil", "create file [" + targetPath + "] failed");
          return null;
        }

      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    try {
      return new FileOutputStream(targetFile);

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }

  private static boolean makeDirs(@NonNull String dir) {
    File dirFile = new File(dir);

    return makeDir(dirFile);
  }

  @Contract("null -> false")
  private static boolean makeDir(@Nullable File file) {
    if (file == null) {
      return false;
    }
    if (file.isDirectory()) {
      return true;
    }
    if (file.exists()) {
      if (!file.delete()) {
        Log.i("FileUtil", "delete file [" + file + "] failed");
        return false;
      }
    }
    return file.mkdirs();
  }

  private static void closeQuietly(@Nullable Closeable closeable) {
    if (closeable == null) {
      return;
    }

    try {
      closeable.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private FileUtil() {}

}
