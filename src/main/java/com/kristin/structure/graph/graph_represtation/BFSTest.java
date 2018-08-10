package com.kristin.structure.graph.graph_represtation;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Kristin
 * @since 2018/8/10 16:46
 **/
public class BFSTest {
    public static void main(String[] args) {
        LinkedList<Character> lists = new LinkedList<Character>();
        lists.add('w');
        lists.add('r');
        LinkedList<Character> listw = new LinkedList<Character>();
        listw.add('s');
        listw.add('i');
        listw.add('x');
        LinkedList<Character> listr = new LinkedList<Character>();
        listr.add('s');
        listr.add('v');
        LinkedList<Character> listx = new LinkedList<Character>();
        listx.add('w');
        listx.add('i');
        listx.add('u');
        listx.add('y');
        LinkedList<Character> listv = new LinkedList<Character>();
        listv.add('r');
        LinkedList<Character> listi = new LinkedList<Character>();
        listi.add('u');
        listi.add('x');
        listi.add('w');
        LinkedList<Character> listu = new LinkedList<Character>();
        listu.add('i');
        listu.add('x');
        listu.add('y');
        LinkedList<Character> listy = new LinkedList<Character>();
        listy.add('u');
        listy.add('x');
        HashMap<Character, LinkedList<Character>> graph = new HashMap<Character, LinkedList<Character>>();
        graph.put('s', lists);
        graph.put('w', listw);
        graph.put('r', listr);
        graph.put('x', listx);
        graph.put('v', listv);
        graph.put('i', listi);
        graph.put('y', listy);
        graph.put('u', listu);
        HashMap<Character, Integer> dist = new HashMap<Character, Integer>();
        char start = 's';
        bfs(graph, dist, start);
    }
    private static void bfs(HashMap<Character, LinkedList<Character>> graph, HashMap<Character, Integer> dist,
                            char start) {
        Queue<Character> queue = new LinkedList<Character>();
        queue.add(start);
        dist.put(start, 0);
        int i = 0;
        while (!queue.isEmpty()) {
            char top = queue.poll();
            i++;
            System.out.println("The " + i + "th element:" + top + " Distance from s is " + dist.get(top));
            int d = dist.get(top) + 1;
            for (Character c : graph.get(top)) {
                if (!dist.containsKey(c)) {
                    dist.put(c, d);
                    queue.add(c);
                }
            }
        }
    }
}
