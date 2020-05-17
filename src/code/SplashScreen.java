package code;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

public class SplashScreen extends JFrame{

    private JPanel contentPane;
    private JLabel lblLoading;
    private JProgressBar progressBar;
    private JLabel labelProgress;


    public static void main(String[] args) {

        SplashScreen frame = new SplashScreen();

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {

                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        LoginForm lf = new LoginForm();

        try {
            for(int i = 0; i<100; i++) {
                Thread.sleep(40);
                frame.progressBar.setValue(i);
                frame.labelProgress.setText(Integer.toString(i) + "%");
            }
        }catch(Exception e) {
            e.getMessage();
        }

        new SplashScreen().setVisible(false);
        lf.setVisible(true);
        frame.dispose();
    }

    public SplashScreen() {
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 716, 328);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 716, 328);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblV = new JLabel("v. 1.0");
        lblV.setBounds(682, 0, 34, 14);
        panel.add(lblV);
        lblV.setForeground(Color.BLACK);

        JLabel lblJustANormal = new JLabel("Just a normal app where you save your contacts");
        lblJustANormal.setFont(new Font("Sylfaen", Font.BOLD, 18));
        lblJustANormal.setBounds(10, 225, 426, 23);
        panel.add(lblJustANormal);

        JLabel lblContactApplication = new JLabel("Contact Application");
        lblContactApplication.setFont(new Font("Impact", Font.PLAIN, 45));
        lblContactApplication.setBounds(175, 0, 394, 137);
        panel.add(lblContactApplication);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(0, 246, 716, 82);
        panel_1.setBackground(Color.BLACK);
        panel.add(panel_1);
        panel_1.setLayout(null);

        lblLoading = new JLabel("Loading...");
        lblLoading.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblLoading.setForeground(Color.WHITE);
        lblLoading.setBounds(311, 11, 77, 22);
        panel_1.add(lblLoading);

        progressBar = new JProgressBar();
        progressBar.setForeground(Color.RED);
        progressBar.setBorderPainted(false);
        progressBar.setBounds(10, 44, 696, 14);
        panel_1.add(progressBar);

        labelProgress = new JLabel("0%");
        labelProgress.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelProgress.setForeground(Color.WHITE);
        labelProgress.setBounds(392, 15, 48, 14);
        panel_1.add(labelProgress);



        JLabel lblNewLabel = new JLabel("New label");
        URL imageUrl;
        try {
            imageUrl = new URL("https://4kwallpaper.wiki/wp-content/uploads/2019/07/215809.jpg");
            HttpURLConnection connection = (HttpURLConnection) imageUrl.openConnection();
            
            BufferedImage propertImage = ImageIO.read(connection.getInputStream());


        } catch (Exception ex) {

        }
    }
}
