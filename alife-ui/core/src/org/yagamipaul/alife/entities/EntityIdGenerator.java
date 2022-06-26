package org.yagamipaul.alife.entities;
/** TODO: Modify this strategy. */
public class EntityIdGenerator {

  private static int count = 0;

  public static String createId() {
    return String.valueOf(EntityIdGenerator.count++);
  }
}
