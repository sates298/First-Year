package serverModel;

public class HelpTree<T extends Comparable<T>>
{
    private Branch<T> root;
    private int t;
    private Methods<T> method = new Methods<>();

    public HelpTree(Tree t){
        root = null;
        this.t = t.getK();
    }

    Branch<T> getRoot(){
        return root;
    }
   /*
    String print(){
        if(!(root == null)){
            return root.print();
        }else{
            return "Tree is empty!";
        }
    }
*/
    boolean search(T v){

        return root.search(v) != null;
    }

    void put(T v){

        if (root == null)
        {

            root = new Branch<>(t, true);
            root.values[0] = v;  // Insert key
            root.n = 1;
        }
        else
        {
            if (root.n == 2*t-1)
            {
                Branch<T> s= new Branch<>(t, false);

                s.C[0] = root;


                method.splitChild(0, root,s);


                int i = 0;
                if (s.values[0].compareTo(v) < 0 )
                    i++;
                method.insertNonFull(v, s.C[i]);

                root = s;
            }
            else
                method.insertNonFull(v,root);
        }
    }

    void remove(T v){
        if (root == null)
        {
            System.out.println("The tree is empty\n");
            return;
        }

        root.remove(v);

        if (root.n==0)
        {
            if (root.leaf)
                root = null;
            else
                root = root.C[0];
        }
    }

    /*******************************************************************************************************************/


    ///old algorithm

/*

    private Branch<T> root;
    private int k;
    private int height;
    private Methods<T> method;


    public Tree(List<T> arguments, int k) {
        root = new Branch<>(0);
        this.k = k;
        method = new Methods<>(k);
        for (T arg : arguments) {
            insert(arg);
        }
    }

    public void print(Text text) {
        text.setText(method.draw(root, height, "") + "\n");
        //method.print(root, text, "->");
    }

    public void insert(T index) {
        Branch<T> b = method.insert(root, index, height);
        if (b == null) {
            return;
        }

        Branch<T> temp = new Branch<>(2);
        */
/*temp.getBr()[0] = new Leaf(root.getBr()[0].getIndex(),  root);
        temp.getBr()[1] = new Leaf(b.getBr()[0].getIndex(), b);
*//*

        temp.getBranch().add(new Leaf<>(((Leaf<T>) root.getBranch().get(0)).getIndex(), root));
        temp.getBranch().add(new Leaf<>(((Leaf<T>) b.getBranch().get(0)).getIndex(), b));
        root = temp;
        height++;
    }

    public void search(T what, Text text){
        method.research(root, what, text);
        if(text.getText().equals("")){
            text.setText(what + " is not here");
        }
    }

    public void delete(T what){
        method.delete(root, what, height);
    }
*/

}
