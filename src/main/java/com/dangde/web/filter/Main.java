package com.dangde.web.filter;

public class Main {
  public static void main(String[] args) {

    //    String s1 = new String("aa");
    //    String s2 = new String("aa");
    //
    //    System.out.println(s1 == s2);

    //    String str1 = new String("ewqr");
    //    System.out.println(str1.intern() == str1);
    //
    //    String str2 = new String("afwef");
    //    //    System.out.println(str2.intern() == str2);
    //    String str1 = "124";
    //    System.out.println(str1.intern() == str1);
    //
    //    String str2 = "gqew";
    //    System.out.println(str2.intern() == str2);

    String str1 = new String("SEU") + new String("Calvin");
    System.out.println(str1.intern() == str1);
    System.out.println(str1 == "SEUCalvin");
  }
}
