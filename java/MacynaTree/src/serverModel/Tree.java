package serverModel;

import java.util.List;

public class Tree<T extends Comparable<T>> {

    private static Tree instance;
    private Branch<T> root;
    private int k;
    private HelpTree<T> help;

    public static synchronized Tree getInstance() {

        if (instance == null) {
            instance = new Tree();
        }
        return instance;
    }

    private Tree() {
    }

    public int getK(){
        return this.k;
    }

    public String insertArgumentsOnStartup(List<T> arguments, int k) {

        if(k%2 != 0) {
            this.k = (k + 1) / 2;
        }else{
            this.k = k/2;
        }
        this.root = null;
        this.help = new HelpTree<>(instance);
        for(T arg : arguments){
            help.put(arg);
        }

        instance.root = help.getRoot();

        return draw(instance);
    }


    public String insert(T index) {


        help.put(index);

        instance.root = help.getRoot();

        return draw(instance);

    }

    public String search(T what) {


        return (help.search(what))? "present" : "not present";
    }

    public String remove(T what) {


        help.remove(what);

        instance.root = help.getRoot();

        return draw(instance);
    }

    private String draw(Tree t) {


        if(t.root != null){
            return t.root.print();
        }else{
            return "Tree is empty!";
        }


    }




}