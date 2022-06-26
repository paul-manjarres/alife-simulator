package org.yagamipaul.alife;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM
// argument
public class DesktopLauncher {

  private static final int WINDOW_WIDTH = 1280;
  private static final int WINDOW_HEIGHT = 720;
  private static final String WINDOW_TITLE = "Alife Simulator";

  public static void main(String[] arg) {
    Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
    config.setForegroundFPS(60);
    config.setTitle(WINDOW_TITLE);
    config.setWindowedMode(WINDOW_WIDTH, WINDOW_HEIGHT);
    new Lwjgl3Application(new MyGdxGame(), config);
  }
}
