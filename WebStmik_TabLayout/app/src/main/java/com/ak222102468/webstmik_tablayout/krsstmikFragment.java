package com.ak222102468.webstmik_tablayout;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link krsstmikFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class krsstmikFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private WebView _webView;
    public krsstmikFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment krsstmikFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static krsstmikFragment newInstance(String param1, String param2) {
        krsstmikFragment fragment = new krsstmikFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_krsstmik, container, false);

        _webView = (WebView) view.findViewById(R.id.krsmoodleWebView);
        _webView.loadUrl("https://stmikpontianak.cloud/StmikKrs/login.tw");

        WebSettings settings = _webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);

        _webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                _webView.evaluateJavascript(
                        "javascript:(function() {" +
                                "document.getElementById('idText').value = '222102468';" +
                                "document.getElementsByName('passwordText')[0].value = '261004an';" +
                                "})()",
                        null
                );
            }
        });

        WebChromeClient webChromeClient = new WebChromeClient();
        _webView.setWebChromeClient(webChromeClient);

        return view;

        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_krsstmik, container, false);
    }
}