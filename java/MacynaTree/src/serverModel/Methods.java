package serverModel;


import javafx.scene.text.Text;

import java.util.List;

class Methods<T extends Comparable<T>> {

    Methods() {
    }

    void print(Branch<T> b, StringBuilder main, String tab) {

        T[] array = b.values;

        main.append(tab);

        for (int i = 0; i < b.n ; i++) {
            main.append(array[i]).append(" ");
        }

        main.append("\n");
        for (int i=0; i < b.n + 1;i++) {
            String tab2 = tab;
            if (b.C[i] != null) {
                tab2 = "   " + tab2;
                print(b.C[i], main, tab2);
            }
        }
    }

    Branch search(T v, Branch t) {
        int i = 0;
        while (i < t.n && less(t.values[i], v)) {
            i++;
        }

        if (i < t.n && eq(t.values[i], v)) {
            return t;
        }
        if (t.leaf) {
            return null;
        }
        return search(v, t.C[i]);
    }

    void insertNonFull(T v, Branch<T> t) {
        int i = t.n - 1;

        if (t.leaf) {
            while (i >= 0 && less(v, t.values[i])) {
                t.values[i + 1] = t.values[i];
                i--;
            }

            t.values[i + 1] = v;
            t.n++;
        } else
        {
            while (i >= 0 && less(v, t.values[i]))
                i--;

            if (t.C[i + 1].n == 2 * t.t - 1) {
                splitChild(i + 1, t.C[i + 1], t);

                if (less(t.values[i + 1], v))
                    i++;
            }
            insertNonFull(v, t.C[i + 1]);
        }
    }

    void splitChild(int i, Branch<T> y, Branch<T> t) {
        Branch<T> z = new Branch<>(y.t, y.leaf);
        z.n = t.t - 1;

        for (int j = 0; j < t.t - 1; j++)
            z.values[j] = y.values[j + t.t];

        if (!y.leaf) {
            for (int j = 0; j < t.t; j++)
                z.C[j] = y.C[j + t.t];
        }

        y.n = t.t - 1;

        for (int j = t.n; j >= i + 1; j--)
            t.C[j + 1] = t.C[j];

        t.C[i + 1] = z;

        for (int j = t.n - 1; j >= i; j--)
            t.values[j + 1] = t.values[j];

        t.values[i] = y.values[t.t - 1];


        t.n++;
    }

    int findValue(T v, Branch t) {
        int idx = 0;

        while (idx < t.n && less(t.values[idx], v)) {
            ++idx;
        }
        return idx;
    }

    void removeFromLeaf(int idx, Branch t) {
        for (int i = idx + 1; i < t.n; ++i)
            t.values[i - 1] = t.values[i];

        t.n--;
    }

    void removeFromNonLeaf(int idx, Branch<T> t) {
        T k = t.values[idx];

        if (t.C[idx].n >= t.t) {
            T pred = getPred(idx, t);
            t.values[idx] = pred;
            t.C[idx].remove(pred);
        }

        else if (t.C[idx + 1].n >= t.t) {
            T succ = getSucc(idx, t);
            t.values[idx] = succ;
            t.C[idx + 1].remove(succ);
        }

        else {
            merge(idx, t);
            t.C[idx].remove(k);
        }
    }

    void fill(int idx, Branch<T> t) {

        if (idx != 0 && t.C[idx - 1].n >= t.t)
            borrowFromPrev(idx, t);

        else if (idx != t.n && t.C[idx + 1].n >= t.t)
            borrowFromNext(idx, t);

        else {
            if (idx != t.n)
                merge(idx, t);
            else
                merge(idx - 1, t);
        }
    }

    private T getPred(int idx, Branch<T> t) {

        Branch<T> cur = t.C[idx];
        while (!cur.leaf)
            cur = cur.C[cur.n];

        return cur.values[cur.n - 1];

    }

    private T getSucc(int idx, Branch<T> t) {
        Branch<T> cur = t.C[idx + 1];
        while (!cur.leaf)
            cur = cur.C[0];

        return cur.values[0];
    }

    private void borrowFromPrev(int idx, Branch<T> t) {

        Branch<T> child = t.C[idx];
        Branch<T> sibling = t.C[idx - 1];

        for (int i = child.n - 1; i >= 0; --i)
            child.values[i + 1] = child.values[i];

        if (!child.leaf) {
            for (int i = child.n; i >= 0; --i)
                child.C[i + 1] = child.C[i];
        }

        child.values[0] = t.values[idx - 1];

        if (!t.leaf)
            child.C[0] = sibling.C[sibling.n];

        t.values[idx - 1] = sibling.values[sibling.n - 1];

        child.n += 1;
        sibling.n -= 1;

    }

    private void borrowFromNext(int idx, Branch<T> t) {
        Branch<T> child = t.C[idx];
        Branch<T> sibling = t.C[idx + 1];

        child.values[child.n] = t.values[idx];

        if (!child.leaf)
            child.C[child.n + 1] = sibling.C[0];

        t.values[idx] = sibling.values[0];

        for (int i = 1; i < sibling.n; ++i)
            sibling.values[i - 1] = sibling.values[i];

        if (!sibling.leaf) {
            for (int i = 1; i <= sibling.n; ++i)
                sibling.C[i - 1] = sibling.C[i];
        }

        child.n += 1;
        sibling.n -= 1;
    }

    private void merge(int idx, Branch<T> t) {
        Branch<T> child = t.C[idx];
        Branch<T> sibling = t.C[idx + 1];

        child.values[t.t - 1] = t.values[idx];

        for (int i = 0; i < sibling.n; ++i)
            child.values[i + t.t] = sibling.values[i];

        if (!child.leaf) {
            for (int i = 0; i <= sibling.n; ++i)
                child.C[i + t.t] = sibling.C[i];
        }

        for (int i = idx + 1; i < t.n; ++i)
            t.values[i - 1] = t.values[i];

        for (int i = idx + 2; i <= t.n; ++i)
            t.C[i - 1] = t.C[i];

        child.n += sibling.n + 1;
        t.n--;
    }


    /******************************************************************************************************************/

    //methods uses in both algorithm
    private boolean less(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) < 0;
    }

    private boolean eq(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) == 0;
    }

    /******************************************************************************************************************/
//old algorithm
/*

    private int k;

    Methods(int k) {
        this.k = k;
    }

    //unused old method to print out a tree
   */
/* void print(Branch<T> branch, Text text, String tab) {

        List<Leaf<T>> list = branch.getBranch();
        text.setText(text.getText() + tab);

        for (int i = 0; i < list.size() && i < k; i++) {
            text.setText(text.getText() + list.get(i).getIndex() + " ");
        }

        text.setText(text.getText() + '\n');

        for (Leaf<T> l : list) {
            String tab2 = tab;
            if (l.getPointer() != null) {
                tab2 = "   " + tab2;
                print(l.getPointer(), text, tab2);
            }

        }
    }*//*


    void research(Branch<T> b, T what, Text text) {
        List<Leaf<T>> list = b.getBranch();
        for (Leaf<T> l : list) {
            if (text.getText().equals("")) {
                if (l.getIndex().equals(what)) {
                    text.setText(what + " is here!");
                }
            } else {
                break;
            }
        }
        for (Leaf<T> l : list) {
            if (!text.getText().equals("")) {
                break;
            } else if (l.getPointer() != null) {
                research(l.getPointer(), what, text);
            }
        }
    }

    Branch<T> insert(Branch<T> b, T ind, int h) {
        int j = 0;
        Leaf<T> l = new Leaf<>(ind, null);

        if (h == 0) {
            while (j < b.getChildren()) {
                if (ind.compareTo(((Leaf<T>) b.getBranch().get(j)).getIndex()) < 0) {
                    break;
                }
                j++;
            }
        } else {

            */
/*for (j = 0; j < b.getChildren(); j++) {
                if ((j+1 == b.getChildren()) || less(ind, b.getBr()[j+1].getIndex())) {
                    Branch<T> u = insert(b.getBr()[j++].getPointer(),ind, h-1);
                    if (u == null) return null;
                    l.setIndex(u.getBr()[0].getIndex());
                    l.setPointer(u);
                    break;
                }
            }*//*



            while (j < b.getChildren()) {
                if (j + 1 == b.getChildren() || ind.compareTo(((Leaf<T>) b.getBranch().get(j + 1)).getIndex()) < 0) {
                    Branch<T> temp = insert(((Leaf<T>) b.getBranch().get(j++)).getPointer(), ind, h - 1);
                    if (temp == null) {
                        return null;
                    }
                    l.setIndex(((Leaf<T>) temp.getBranch().get(0)).getIndex());
                    l.setPointer(temp);
                    break;
                }
                j++;
            }
        }




      */
/*  for (int i = b.getChildren(); i > j; i--)
            b.getBr()[i] = b.getBr()[i-1];
        b.getBr()[j] = l;*//*



        if (j < b.getBranch().size()) {
            b.getBranch().add(j, l);
        } else {
            b.getBranch().add(l);
        }
        b.setChildren(b.getChildren() + 1);
        if (b.getChildren() <= k) return null;
        return split(b);
    }

    private Branch<T> split(Branch<T> b) {
        Branch<T> temp = new Branch<>(k / 2);
        temp.setChildren(k / 2);
       */
/* for(int j=0; j < k/2; j++){
            temp.getBr()[j] = b.getBr()[k/2 + j];
        }*//*



        temp.getBranch().addAll(b.getBranch().subList(k / 2 + 1, b.getBranch().size()));
        b.getBranch().removeAll(b.getBranch().subList(k / 2 + 1, b.getBranch().size()));
        b.setChildren(b.getChildren() - k / 2);
        return temp;
    }

    void delete(Branch<T> b, T what, int h) {
    }


    String draw(Branch<T> b, int h, String s) {
        StringBuilder sb = new StringBuilder();
        List<Leaf<T>> list = b.getBranch();


        if (h == 0) {
            for (int j = 0; j < b.getChildren(); j++) {
                sb.append(s + list.get(j).getIndex() + "\n");
            }

        } else {
            for (int j = 0; j < b.getChildren(); j++) {
                if (j > 0) {
                    sb.append(s + "(" + list.get(j).getIndex() + ")\n");
                }
                sb.append(draw(list.get(j).getPointer(), h - 1, s + "          "));
            }
        }
        return sb.toString();
    }
*/


}
