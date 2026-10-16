package cacheward.cli;

import java.util.Arrays;

record Command(Name name, String[] args) {
  enum Name {
    EXIT(0, 0, ""),
    GET(1, 1, "<key>"),
    HELP(0, 0, ""),
    SET(2, 2, "<key> <value>");

    final int min;
    final int max;
    final String usage;

    Name(int min, int max, String usage) {
      this.min = min;
      this.max = max;
      this.usage = usage;
    }
  }

  static Command parse(String[] words) throws Exception {
    if (words.length == 0) {
      throw new Exception("missing command");
    }
    String word = words[0];
    Name name;
    try {
      name = Name.valueOf(word);
    } catch (IllegalArgumentException e) {
      throw new Exception("unknown command: " + word);
    }
    String[] args = Arrays.copyOfRange(words, 1, words.length);
    if (args.length < name.min || args.length > name.max) {
      throw new Exception("usage: " + name + " " + name.usage);
    }
    return new Command(name, args);
  }
}
