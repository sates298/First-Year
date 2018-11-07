package serverModel;

import java.util.ArrayList;
import java.util.List;

class Branch<T extends Comparable<T>> {


    T[] values;
    int t;
    Branch<T>[] C;
    int n;
    boolean leaf;
    Methods<T> m = new Methods<>();

    public Branch(int t, boolean leaf) {
        this.t = t;
        this.leaf = leaf;

        values = (T[]) new Comparable[2 * t - 1];
        C = new Branch[2 * t];
        n = 0;
    }

    //algorithm methods
     String print() {
        StringBuilder main = new StringBuilder("");
        m.print(this, main, "|--");
        return main.toString();
    }

    public void remove(T v) {
        int idx = m.findValue(v, this);

        if (idx < n && values[idx].compareTo(v) == 0) {

            if (leaf)
                m.removeFromLeaf(idx, this);
            else
                m.removeFromNonLeaf(idx, this);
        } else {

            if (leaf) {
                System.out.println("The value " + v + " is does not exist in the tree\n");
                return;
            }

            boolean flag = (idx == n);

            if (C[idx].n < t)
                m.fill(idx, this);

            if (flag && idx > n)
                C[idx - 1].remove(v);
            else
                C[idx].remove(v);
        }
    }

    Branch search(T v) {
        return m.search(v, this);
    }


    /**************************************************************************************************/
//old algorithm

  /*  //algorithm for even k> 2 and without removal
    private List<Leaf<T>> branch = new ArrayList<>();
    private int children;

    Branch(int n){
        children = n;
    }

    List getBranch(){
        return this.branch;
    }

    int getChildren(){
        return this.children;
    }

    void setChildren(int a){
        this.children = a;
    }
*/

}
