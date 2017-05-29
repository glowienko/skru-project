package project.skru_dane_po_warszawsku.network;

/**
 * Created by jaroslaw on 29.05.2017.
 */

interface RequestCallback<T> {
    void updateFromResponse(T s);
}
