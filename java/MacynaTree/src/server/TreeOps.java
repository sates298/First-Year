package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface TreeOps<T> extends Remote {

    public String insert(T t) throws RemoteException;

    public String remove(T t) throws RemoteException;

    public String search(T t) throws RemoteException;

    public String addOnStartup(List<T> args, int k) throws RemoteException;


}
