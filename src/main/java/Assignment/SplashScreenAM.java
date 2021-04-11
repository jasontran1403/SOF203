/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment;

import java.awt.event.ActionListener;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.Timer;

/**
 *
 * @author Jason
 */
public class SplashScreenAM extends JWindow {
    private static Login lg;
    private static JProgressBar progressBar = new JProgressBar();
    private static SplashScreenAM splashScreen;
    private static int count = 1,PROGBAR_MAX=8;
    private static Timer progressBarTimer;
    LoadingScreen ls = new LoadingScreen();
    ActionListener al = new ActionListener() {

        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            progressBar.setValue(count);
            System.out.println(count);
            if (PROGBAR_MAX == count) {
                ls.dispose();
                progressBarTimer.stop();
                createAndShowFrame();
            }
            count++;

        }
        
    };

    public SplashScreenAM() {
        createSplash();
    }

    private void createSplash() {
        
        ls.show();
        
        startProgressBar();
    }

    private void startProgressBar() {
        progressBarTimer = new Timer(1000, al);
        progressBarTimer.start();
        
        
    }

    private void createAndShowFrame() {
        AdminManager am = new AdminManager();
        am.show();
        count = 0;
    }

    public static void main(String[] args) {
        splashScreen = new SplashScreenAM();
    }
}