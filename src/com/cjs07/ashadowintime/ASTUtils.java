package com.cjs07.ashadowintime;

import java.net.URL;
import java.applet.AudioClip;
import javax.swing.JApplet;

public class ASTUtils {
  public void playSong (String song) {
    ClassLoader cl = ASTFrame.clss.getClassLoader();
    URL url = cl.getResource("res/sounds/" + song);
    if (url != null) {
      AudioClip toPlay = JApplet.newAudioClip(url);
      toPlay.play
    }
  }
}
