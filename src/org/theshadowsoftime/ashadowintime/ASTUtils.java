package org.theshadowsoftime.ashadowintime;

import javax.swing.*;
import java.applet.AudioClip;
import java.net.URL;

/**
 *
 */
public class ASTUtils {
  public static void playSong (String song) {
    ClassLoader cl = ASTFrame.class.getClassLoader();
    URL url = cl.getResource("res/sounds/" + song);
    if (url != null) {
      AudioClip toPlay = JApplet.newAudioClip(url);
      toPlay.play();
    }
  }
}
