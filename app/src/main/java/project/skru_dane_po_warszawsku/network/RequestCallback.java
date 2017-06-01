package project.skru_dane_po_warszawsku.network;

import java.io.IOException;

public interface RequestCallback<T> {
    void updateFromResponse(T response) throws IOException;
}
