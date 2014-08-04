package org.theshadowsoftime.ashadowintime;


/**
 *
 */
public class RunnableOverlapLoop implements Runnable {
  public void run() {
    while(true) {
      try {
        Thread.sleep((60*2) * 1000);
      } catch (InterruptedException e) {
      }
      ASTUtils.playSong("mainmenu.wav");
    }
  }
}
