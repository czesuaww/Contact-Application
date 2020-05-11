package code;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class PictureSearch {

    PictureResize pr = new PictureResize();

    public String browseImage(JLabel lbl) {

        String path = null;
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));

        //file extension
        FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("*.Images","jpg","png","gif");
        file.addChoosableFileFilter(fileFilter);

        int fileState = file.showSaveDialog(null);

        // if the user select a file

        if(fileState == JFileChooser.APPROVE_OPTION) {
            File selectedFile = file.getSelectedFile();
            path = selectedFile.getAbsolutePath();
            //imagePth = path;
            //display the image in the jLabel

            lbl.setIcon(pr.resizePic(path, null, lbl.getWidth(), lbl.getHeight()));
            //lblPictureAdd.setIcon(new ImageIcon(path));
        }
        //if user cancel
        else if(fileState == JFileChooser.CANCEL_OPTION) {
            System.out.println("No image selected!");
        }

        return path;
    }
}
