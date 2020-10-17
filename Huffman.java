package com.company;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class Huffman {
    private String str;

    public String getStr(){
        return str;
    }

    public void setStr(String s){
        str = s;
    }

    //Узел дерева
    class Node implements Comparable <Node>{
        int sum;

        //Код для каждого узла
        String code;

        void BuildCode(String code) {
            this.code = code;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(sum, o.sum);
        }
    }

    //Внутренний узел дерева
    class InternalNode extends Node{
        Node left;
        Node right;

        @Override
        void BuildCode(String code){
            super.BuildCode(code);
            left.BuildCode(code + "0");
            right.BuildCode(code + "1");
        }

        public InternalNode(Node left, Node right){
            this.left = left;
            this.right = right;

            sum = left.sum + right.sum;
        }
    }

    class LeafNode extends Node{
        char symbol;

        public LeafNode(char symbol, int count){
            this.symbol = symbol;
            sum = count;
        }

        @Override
        void BuildCode(String code){
            super.BuildCode(code);
        }
    }

    public void HuffmanCoding(String s){
        str = s;

        //Подсчет вхождений каждого символа в строку
        Map<Character, Integer> count = new HashMap<>();

        for(int i = 0; i < str.length(); i++){
            char c = s.charAt(i);

            if(count.containsKey(c)){
                count.put(c, count.get(c)+1);
            }
            else {
                count.put(c, 1);
            }
        }

        Map <Character, Node> charNodes = new HashMap<>();

        //Реализуем приоритетную очередь
        PriorityQueue <Node> priorityQueue = new PriorityQueue<>();

        for(Map.Entry<Character, Integer> entry: count.entrySet()){
            LeafNode node = new LeafNode(entry.getKey(), entry.getValue());
            charNodes.put(entry.getKey(), node);
            priorityQueue.add(node);
        }

        int sum = 0;

        while(priorityQueue.size() > 1){
            Node first = priorityQueue.poll();
            Node second = priorityQueue.poll();
            InternalNode node = new InternalNode(first, second);
            sum += node.sum;
            priorityQueue.add(node);
        }
        if(count.size() == 1) {
            sum += str.length();
        }
        
        Node root = priorityQueue.poll();

        if(count.size() == 1){
            root.code = "0";
        }
        else{
            root.BuildCode("");
        }
        root.BuildCode("");

        String encodedString = "";

        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            encodedString += charNodes.get(c).code;
        }

        System.out.println(encodedString);
    }
}
