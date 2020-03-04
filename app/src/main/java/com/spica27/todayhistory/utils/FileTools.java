package com.spica27.todayhistory.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class FileTools {
  private static final String SD_PATH = "/sdcard/dskqxt/pic/";
  private static final String IN_PATH = "/dskqxt/pic/";


  private static String generateFileName() {
    return UUID.randomUUID().toString();
  }

  public static String saveBitmap(Context context, Bitmap mBitmap) {
    String savePath;
    File filePic;
    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
      savePath = SD_PATH;
    } else {
      savePath = context.getApplicationContext().getFilesDir().getAbsolutePath() + IN_PATH;
    }
    try {
      filePic = new File(savePath + generateFileName() + ".jpg");
      if (!filePic.exists()) {
        filePic.getParentFile().mkdirs();
        filePic.createNewFile();
      }
      FileOutputStream fos = new FileOutputStream(filePic);
      mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
      fos.flush();
      fos.close();
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }

    return filePic.getAbsolutePath();
  }
}
