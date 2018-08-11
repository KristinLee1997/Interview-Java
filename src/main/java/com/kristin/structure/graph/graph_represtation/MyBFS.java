package com.kristin.structure.graph.graph_represtation;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

/**
 * @author Kristin
 * @since 2018/8/10 17:30
 **/
//  邻接表广搜
public class MyBFS {
    // 用于存储连接表
    public HashMap<Character, Vector<Character>> map = new HashMap<>();

    public static void main(String[] args) {
        MyBFS bfs = new MyBFS();
        bfs.init();
        bfs.bfs('s');
    }

    public void bfs(Character start) {
        // 将要读取的元素按顺序放到队列中
        Queue<Character> queue = new LinkedList<>();
        int distance = 0;
        queue.add(start);

        // 使用一个map记录结点与其到start结点的长度
        HashMap<Character, Integer> sortedMap = new HashMap<>();
        sortedMap.put(start, 0);
        while (!queue.isEmpty()) {
            Character top = queue.poll();
            System.out.println(top +"   "+sortedMap.get(top));
            int d = sortedMap.get(top) + 1;
            for (Character c : map.get(top)) {
                if (!sortedMap.containsKey(c)) {
                    queue.add(c);
                    sortedMap.put(c, d);
                }
            }
        }
    }

    public void init() {
        Vector vecs = new Vector();
        vecs.add('w');
        vecs.add('r');

        Vector vecw = new Vector();
        vecw.add('s');
        vecw.add('i');
        vecw.add('x');

        Vector vecr = new Vector();
        vecr.add('s');
        vecr.add('v');

        Vector vecx = new Vector();
        vecx.add('w');
        vecx.add('i');
        vecx.add('u');
        vecx.add('y');

        Vector vecv = new Vector();
        vecv.add('r');

        Vector veci = new Vector();
        veci.add('u');
        veci.add('x');
        veci.add('w');

        Vector vecu = new Vector();
        vecu.add('i');
        vecu.add('x');
        vecu.add('y');

        Vector vecy = new Vector();
        vecy.add('u');
        vecy.add('x');

        map.put('s', vecs);
        map.put('w', vecw);
        map.put('r', vecr);
        map.put('x', vecx);
        map.put('v', vecv);
        map.put('i', veci);
        map.put('u', vecu);
        map.put('y', vecy);
    }
}
