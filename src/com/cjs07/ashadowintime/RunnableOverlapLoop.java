public class RunnableOverlapLoop impelemnts Runnable {
  public void run() {
    while(true) {
      try {
        Thread.sleep((60*2) * 1000);
      } catch (InterruptedException e) {
      }
      ASTUtil.playSong("mainmenu.wav");
    }
  }
}
