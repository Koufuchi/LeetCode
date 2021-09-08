/*
   Graph - 圖

   圖的表示通常使用鄰接矩陣和鄰接表，鄰接矩陣易實現但是對於稀疏矩陣會浪費較多空間，
   鄰接表使用鏈表的方式存儲資訊但是對於圖搜索時間複雜度較高。

   @ 鄰接矩陣 (Adjacency Matrix):

   設頂點個數爲 V, 那麼鄰接矩陣可以使用 V × V 的二維陣列來表示。 
   g[i][j] 表示頂點 i 和頂點 j 的關係，對於無向圖(undirected graph)可以使用 0 / 1 表示是否有連接，
   對於帶有權重的圖則需要使用 INF 來區分。有重邊時保存邊數或者權值最大/小的邊即可。

        ex.            
            int[][] g = new int[V][V];

   @

   鄰接表 (Adjacency List):

   鄰接表通過表示從頂點i出發到其他所有可能能到的邊。

    (1) 有向圖:

        class DirectedGraphNode {
            int label;
            ArrayList<DirectedGraphNode> neighbors;
            DirectedGraphNode(int x) {
                label = x;
                neighbors = new ArrayList<DirectedGraphNode>();
            }
        }

    (2) 無向圖同上，只不過在建圖時雙向同時加:

        class UndirectedGraphNode {
            int label;
            ArrayList<UndirectedGraphNode> neighbors;
            UndirectedGraphNode(int x) {
                this.label = x;
                this.neighbors = new ArrayList<UndirectedGraphNode>();
            }
        }
*/