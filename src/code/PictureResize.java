package code;

import javax.swing.*;
import java.awt.*;

public class PictureResize {

    public ImageIcon resizePic(String picPath, byte[] BLOBpic, int width, int height) {

        //ImageIcon myImage = new ImageIcon(picPath);
        ImageIcon myImage;

        if(picPath != null){
            myImage =  new ImageIcon(picPath);
        }else {
            myImage = new ImageIcon(BLOBpic);
        }
        Image img = myImage.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon myPicture = new ImageIcon(img);
        return myPicture;
    }
}
