package zodaproductions.armathtutor;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Tab1Fragment extends Fragment {
    private static final String TAG = "Tab1Fragment";

    @Nullable
    //@Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1_fragment, container, false);
        return view;
    }


}
    public class WolframAlpha extends Activity {

        private static String AppId = "LG7KVL-QJV2T43KWX";
        public static String Response = " ";

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            new YourTask().execute();
        }


        WAQueryResult queryResult;

        private class WolframAlpha extends AsyncTask<WAQueryResult, Void, WAQueryResult> {


            protected void onPreExecute() {

            }

            @Override
            protected WAQueryResult doInBackground(WAQueryResult... urls) {
                String input = "who is the president";
                WAEngine engine = new WAEngine();
                engine.setAppID(appid);
                engine.addFormat("plaintext");

                // Create the query.
                WAQuery query = engine.createQuery();
                query.setInput(input);
                queryResult = null;
                try {
                    queryResult = engine.performQuery(query);
                } catch (WAException e) {
                    e.printStackTrace();
                }
                return queryResult;
            }

            @Override
            protected void onPostExecute(WAQueryResult response) {
                if (queryResult.isError()) {
                    System.out.println("Query error");
                    System.out.println("  error code: " + queryResult.getErrorCode());
                    System.out.println("  error message: " + queryResult.getErrorMessage());

                } else if (!queryResult.isSuccess()) {
                    System.out.println("Query was not understood; no results available.");

                } else {

                    // Got a result.
                    System.out.println("Successful query. Pods follow:\n");
                    for (WAPod pod : queryResult.getPods()) {
                        if (!pod.isError()) {
                            if (pod.getTitle().equals("Result")) {
                                System.out.println(pod.getTitle());
                                for (WASubpod subpod : pod.getSubpods()) {
                                    for (Object element : subpod.getContents()) {
                                        if (element instanceof WAPlainText) {
                                            System.out.println(((WAPlainText) element).getText());
                                            Toast.makeText(getApplicationContext(),((WAPlainText) element).getText(),Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

