package com.anupam;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.SequenceInputStream;
import java.util.Scanner;

public class Utility {
  public static String buildCommand(String command) throws IOException {
    String[] arr = command.split(" ");
    ProcessBuilder pb = new ProcessBuilder(arr);
    return execute(pb);
  }
  
  private static String execute(ProcessBuilder pb) throws IOException {
    Process p = pb.start();
    OutputStream stdin = p.getOutputStream();
    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin));
    writer.write("\n");
    writer.flush();
    writer.close();
    SequenceInputStream sis = new SequenceInputStream(p.getInputStream(), p.getErrorStream());
    String out = "";
    Scanner scanner = (new Scanner(sis)).useDelimiter("\\A");
    out = scanner.hasNext() ? scanner.next() : "";
    scanner.close();
    return out;
  }
}
