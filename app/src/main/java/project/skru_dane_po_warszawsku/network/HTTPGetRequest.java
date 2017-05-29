package project.skru_dane_po_warszawsku.network;

/**
 * Created by jaroslaw on 29.05.2017.
 */

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by jaroslaw on 24.04.2017.
 */

public class HTTPGetRequest extends AsyncTask<String, Void, String> {

    public RequestCallback<String> mCallback;

    public HTTPGetRequest(RequestCallback<String> callback) {
        mCallback = callback;
    }

    @Override
    protected String doInBackground(String... params) {
        String result = null;

        if (!isCancelled() && params != null && params.length > 0) {
            String urlString = params[0];
            try {
                URL url = new URL(urlString);
                result = downloadUrl(url);
                if (result == null) {
                    throw new IOException("No response received.");
                }

            } catch (Exception e) {
                Log.e("NETWORK_EXCEPTION", e.getMessage());
            }
        }

        return result;
    }

    private String downloadUrl(URL url) throws IOException {
        InputStream stream = null;
        HttpsURLConnection connection = null;
        String result = "";
        try {
            connection = (HttpsURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();

            if (responseCode != HttpsURLConnection.HTTP_OK) {
                throw new IOException("HTTP error: " + responseCode);
            }
            stream = connection.getInputStream();
            if (stream != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader((stream)));
                String line;

                while ((line = reader.readLine()) != null) {
                    result += line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                stream.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            mCallback.updateFromResponse(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}