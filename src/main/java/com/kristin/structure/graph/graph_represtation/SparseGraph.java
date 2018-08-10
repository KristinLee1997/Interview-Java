package com.kristin.structure.graph.graph_represtation;

import java.util.Vector;

/**
 * @author Kristin
 * @since 2018/8/10 9:57
 **/
// 稀疏图--邻接表
public class SparseGraph {
    private int n;  // 结点数
    private int m;  // 边数
    private boolean isDirected; // 是否为有向图
    private Vector<Integer>[] g;    // 邻接表的具体数据

    public SparseGraph(int n, boolean isDirected) {
        assert n >= 0;
        this.n = n;
        this.m = 0; //  初始时邻接表中无边
        this.isDirected = isDirected;
        this.g = (Vector<Integer>[]) new Vector[n];
        for (int i = 0; i < n; i++) {
            g[i] = new Vector<Integer>();
        }
    }

    private boolean hasEdge(int v, int w) {
        assert v >= 0 && v < n;
        assert w >= 0 && w < n;
        for (int i = 0; i < g[v].size(); i++) {
            if (g[v].elementAt(i) == w) {
                return true;
            }
        }
        return false;
    }

    public void addEdge(int v, int w) {
        assert v >= 0 && v < n;
        assert w >= 0 && w < n;
        if (hasEdge(v, w)) {
            return;
        }
        g[v].add(w);
        if (!isDirected) {
            g[w].add(v);
        }
        this.m++;
    }
}
