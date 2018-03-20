package at.xtools.pwawrapper

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import at.xtools.pwawrapper.ui.UIManager
import at.xtools.pwawrapper.webview.WebViewHelper
import android.view.ViewTreeObserver
import android.support.v4.widget.SwipeRefreshLayout
import at.xtools.pwawrapper.webview.SwipeRefreshLayoutHelper
import im.delight.android.webview.AdvancedWebView


class MainActivity : AppCompatActivity(), AdvancedWebView.Listener {
    override fun onPageStarted(url: String?, favicon: Bitmap?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPageFinished(url: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPageError(errorCode: Int, description: String?, failingUrl: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onExternalPageRequest(url: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDownloadRequested(url: String?, suggestedFilename: String?, mimeType: String?, contentLength: Long, contentDisposition: String?, userAgent: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    // Globals
    private lateinit var uiManager: UIManager
    private lateinit var webViewHelper: WebViewHelper
    private lateinit var swipeRefreshLayoutHelper: SwipeRefreshLayoutHelper

    private var intentHandled = false

    protected override fun onCreate(savedInstanceState: Bundle?) {
        // Setup Theme
        setTheme(R.style.AppTheme_NoActionBar)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setup Helpers
        uiManager = UIManager(this)
        webViewHelper = WebViewHelper(this, uiManager)
        swipeRefreshLayoutHelper = SwipeRefreshLayoutHelper(this, uiManager)

        // Setup App
        webViewHelper.setupWebView()
        uiManager.changeRecentAppsIcon()

        // Check for Intents
        try {
            val i = getIntent()
            val intentAction = i.getAction()
            // Handle URLs opened in Browser
            if (!intentHandled && intentAction != null && intentAction == Intent.ACTION_VIEW) {
                val intentUri = i.getData()
                var intentText = ""
                if (intentUri != null) {
                    intentText = intentUri.toString()
                }
                // Load up the URL specified in the Intent
                if (intentText != "") {
                    intentHandled = true
                    webViewHelper.loadIntentUrl(intentText)
                }
            } else {
                // Load up the Web App
                webViewHelper.loadHome()
            }
        } catch (e: Exception) {
            // Load up the Web App
            webViewHelper.loadHome()
        }

    }

    protected override fun onPause() {
        webViewHelper.onPause()
        swipeRefreshLayoutHelper.onPause()
        super.onPause()
    }

    protected override fun onResume() {
        webViewHelper.onResume()
        swipeRefreshLayoutHelper.onResume()
        // retrieve content from cache primarily if not connected
        webViewHelper.forceCacheIfOffline()

        super.onResume()
    }

    // Handle back-press in browser
    override fun onBackPressed() {
        if (!webViewHelper.goBack()) {
            super.onBackPressed()
        }
    }

}
