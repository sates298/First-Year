package serverModel;

//unused class
class Leaf<T extends Comparable<T>> {
    private T index;
    private Branch<T> pointer;

    Leaf(T index, Branch<T> branch) {
        this.index = index;
        this.pointer = branch;
    }

    void setPointer(Branch<T> branch) {
        this.pointer = branch;
    }

    Branch<T> getPointer() {
        return pointer;
    }

    T getIndex() {
        return index;
    }

    void setIndex(T index) {
        this.index = index;
    }
}
