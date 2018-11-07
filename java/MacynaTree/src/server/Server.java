package server;

import serverModel.Tree;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class Server<T> extends UnicastRemoteObject implements TreeOps, Serializable {


    public Server() throws RemoteException {
    }


    public static void main(String[] args) throws RemoteException, MalformedURLException {
        Server server = new Server();

        Naming.rebind("server", server);
    }

    @Override
    public String insert(Object elementToInsert) {
        return Tree.getInstance().insert((Comparable) elementToInsert);
    }

    @Override
    public String remove(Object elementToRemove) {
        return Tree.getInstance().remove((Comparable) elementToRemove);
    }

    @Override
    public String search(Object elementToSearchFor) {
        return Tree.getInstance().search((Comparable) elementToSearchFor);
    }

    @Override
    public String addOnStartup(List args, int k) {
        return Tree.getInstance().insertArgumentsOnStartup(args, k);
    }


}
