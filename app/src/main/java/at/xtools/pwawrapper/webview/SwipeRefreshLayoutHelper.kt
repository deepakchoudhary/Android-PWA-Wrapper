package at.xtools.pwawrapper.webview

import android.app.Activity
import android.support.v4.widget.SwipeRefreshLayout
import android.view.ViewTreeObserver
import android.webkit.WebView
import at.xtools.pwawrapper.R
import at.xtools.pwawrapper.ui.UIManager

/**
 * Created by deepak on 21/3/18.
 */
class SwipeRefreshLayoutHelper(// Instance variables
        private val activity: Activity, private val uiManager: UIManager) {
    private var mySwipeRefreshLayout: SwipeRefreshLayout
    private var mOnScrollChangedListener: ViewTreeObserver.OnScrollChangedListener? = null
    private val webView: WebView

    init {
        this.webView = activity.findViewById<WebView>(R.id.webView)
        mySwipeRefreshLayout = activity.findViewById<SwipeRefreshLayout>(R.id.swipeContainer)
        mySwipeRefreshLayout!!.setOnRefreshListener(
                SwipeRefreshLayout.OnRefreshListener {
                    this.webView.clearCache(true);
                    this.webView.reload()
                }
        )
    }
    fun onPause() {
        mySwipeRefreshLayout.getViewTreeObserver().removeOnScrollChangedListener(mOnScrollChangedListener);
    }

    fun onResume() {
        mOnScrollChangedListener = ViewTreeObserver.OnScrollChangedListener {
            if (webView.getScrollY() === 0)
                mySwipeRefreshLayout!!.setEnabled(true)
            else
                mySwipeRefreshLayout!!.setEnabled(false)
        }
        mySwipeRefreshLayout!!.getViewTreeObserver().addOnScrollChangedListener(mOnScrollChangedListener)
    }
}