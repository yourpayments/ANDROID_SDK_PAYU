package com.payu.payusdk.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class LUPurchaseView extends WebView {

	private LUPurchaseView LUPurchaseView;

	public LUPurchaseView(Context context) {
		super(context);
		init();
	}

	public LUPurchaseView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		LUPurchaseView = this;

		getSettings().setJavaScriptEnabled(true);
		getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
		getSettings().setDomStorageEnabled(true);

		setWebViewClient(new WebViewClient() {

			@Override
			public void onPageFinished(WebView view, String url) {
				LUPurchaseView.onPageFinished(view, url);
				super.onPageFinished(view, url);
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				LUPurchaseView.onPageStarted(view, url, favicon);
				super.onPageStarted(view, url, favicon);
			}
		});

		setWebChromeClient(new WebChromeClient() {

			@Override
			public boolean onConsoleMessage(ConsoleMessage cm) {
				Log.d(LUPurchaseView.class.getSimpleName(), "ConsoleMessage: " + cm.message());
				return true;
			}
		});
	}

	public void onPageFinished(WebView view, String url) {
	}

	public void onPageStarted(WebView view, String url, Bitmap favicon) {
	}
}