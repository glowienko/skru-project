package project.skru_dane_po_warszawsku.network;

import java.io.IOException;

/**
 * Created by jaroslaw on 29.05.2017.
 */

public interface RequestCallback<T> {
    void updateFromResponse(T response) throws IOException;
}
