package com.company;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);

	    System.out.println("Введите строку");

	    String s = sc.nextLine();

	    System.out.println(s);

	    Huffman h = new Huffman();

	    h.HuffmanCoding(s);

    }
}
