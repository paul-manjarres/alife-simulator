package org.yagamipaul.alife.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class VectorUtils {

  public static Vector2 to2D(Vector3 v3){
    return new Vector2(v3.x, v3.y);
  }

}
