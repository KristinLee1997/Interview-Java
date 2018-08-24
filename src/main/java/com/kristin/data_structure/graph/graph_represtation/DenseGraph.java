package com.kristin.data_structure.graph.graph_represtation;

/**
 * @author Kristin
 * @since 2018/8/10 9:42
 **/
// 稠密图-邻接矩阵
public class DenseGraph {
    private int n;  // 结点数
    private int m;  // 边数
    private boolean isDirected; // 是否为有向图
    private boolean[][] g;  // 图的具体数据

    public DenseGraph(int n, boolean isDirected) {
        assert n >= 0;
        this.n = n;
        this.m = 0; // 初始时邻接矩阵中没有边
        this.isDirected = isDirected;
        // g[i][j]代表结点i与结点j之间是否存在边
        // boolean 默认false
        this.g = new boolean[n][n];
    }

    private boolean hasEndge(int v, int w) {
        assert v >= 0 && v < n;
        assert w >= 0 && w < n;
        return g[v][w];
    }

    public void addEdge(int v, int w) {
        assert v >= 0 && v < n;
        assert w >= 0 && w < n;
        if (hasEndge(v, m)) {
            return;
        }
        g[v][w] = true;
        if (!isDirected) {
            g[w][v] = true;
        }
        this.m++;
    }
}
