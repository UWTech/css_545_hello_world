package com.example.css_545_hello_world.MediaManagement;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;

import com.example.css_545_hello_world.App;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MediaManager {
    /**
     * demonstration methods that save and load an image respectively
     * Code references:
     * https://stackoverflow.com/questions/3879992/how-to-get-bitmap-from-an-uri
     * https://stackoverflow.com/questions/15662258/how-to-save-a-bitmap-on-internal-storage
     * https://stackoverflow.com/questions/33065071/download-images-and-save-it?noredirect=1&lq=1
     */

    static String filename = "I_am_Batman.jpg";
    static String directoryName = "batman_images";
    static String path = "/data/user/0/com.example.css_545_hello_world/app_batman_images";

    static public boolean saveMedia(Context context) throws IOException {
        // saves an image to later be loaded by the load media button
        // Batman bitmap https://mwwalk.files.wordpress.com/2014/07/bman.jpg

        // retrieve Batman logo as Bitmap
        Bitmap batmanBitmap = getBatmanBitmapFromWeb();
        // write Bitmap to local storage
        return saveBitmapToLocal(batmanBitmap, context);
    }

    private static boolean saveBitmapToLocal(Bitmap bitmap, Context context) throws IOException {
       // ContextWrapper cw = new ContextWrapper(context);

        // create the directory if it does not exist
        File dir = context.getDir(directoryName, context.MODE_PRIVATE);
        File path = new File(dir, filename);

        try {
            // create a link to the file descriptor for writing data out
            FileOutputStream outputStream = new FileOutputStream(path);
            // compress the data to be stored on disk as appropriate
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            outputStream.close();
            System.out.println("absolutepath " + dir.getAbsolutePath());
            return true;
        } catch (Exception e) {
            System.out.println("failed to write image to disk");
            throw e;
        }
    }

    private static Bitmap getBatmanBitmapFromWeb() throws IOException {
        // allow for blocking call to load image since this is a demo app
        // avoids complexity of async while getting familiar with file system
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            // build the URL
            URL url = new URL("https://mwwalk.files.wordpress.com/2014/07/bman.jpg");
            //retrieve the Bitmnap
            return BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            throw e;
        }
    }

    static public Bitmap loadMedia() {
        //loads the default image saved by the saveMedia if it exists
        //else raises and exception
        try {
            File localFile = new File(path, filename);
            Bitmap image = BitmapFactory.decodeStream(new FileInputStream(localFile));
            return image;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
