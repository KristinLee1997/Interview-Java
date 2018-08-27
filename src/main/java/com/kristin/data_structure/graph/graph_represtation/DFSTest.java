package com.kristin.data_structure.graph.graph_represtation;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author Kristin
 * @since 2018/8/10 16:46
 **/
public class DFSTest {
    private static int count = 0;

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
        HashMap<Character, Boolean> visited = new HashMap<Character, Boolean>();
        dfs(graph, visited);
    }

    private static void dfs(HashMap<Character, LinkedList<Character>> graph, HashMap<Character, Boolean> visited) {
        visit(graph, visited, 's');
    }

    private static void visit(HashMap<Character, LinkedList<Character>> graph, HashMap<Character, Boolean> visited,
                              char start) {
        if (!visited.containsKey(start)) {
            count++;
            System.out.println("The time into element " + start + ":" + count);
            visited.put(start, true);
            for (char c : graph.get(start)) {
                if (!visited.containsKey(c)) {
                    visit(graph, visited, c);
                }
            }
            count++;
            System.out.println("The time out element " + start + ":" + count);
        }
    }
}
