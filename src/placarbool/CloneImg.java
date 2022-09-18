package placarbool;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CloneImg {

    public static void saveImage(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            OutputStream os;
            try ( InputStream is = url.openStream()) {
                String nome = imageUrl.substring(imageUrl.lastIndexOf("/")+1, imageUrl.length());
                os = new FileOutputStream(nome);
                byte[] b = new byte[2048];
                int length;
                while ((length = is.read(b)) != -1) {
                    os.write(b, 0, length);
                }
                os.close();
            } catch (IOException ex) {
                Logger.getLogger(CloneImg.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(CloneImg.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
