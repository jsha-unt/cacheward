package cacheward.cli;

public final class Main {
  public static void main(String[] args) {
    Command command;
    try {
      command = Command.parse(args);
    } catch (Exception e) {
      System.err.println(e.getMessage());
      System.exit(1);
    }
  }
}
