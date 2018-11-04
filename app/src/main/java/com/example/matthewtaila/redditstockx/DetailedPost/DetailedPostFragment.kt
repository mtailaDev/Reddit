package com.example.matthewtaila.redditstockx.DetailedPost

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.matthewtaila.redditstockx.MainActivityViewModel
import com.example.matthewtaila.redditstockx.R
import com.example.matthewtaila.redditstockx.databinding.FragmentDetailedPostBinding
import kotlinx.android.synthetic.main.fragment_detailed_post.*

class DetailedPostFragment : Fragment() {

    private lateinit var mBinding: FragmentDetailedPostBinding
    private lateinit var mainActivityViewModel: MainActivityViewModel

    companion object {
        @JvmStatic
        fun newInstance(): DetailedPostFragment {
            return DetailedPostFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityViewModel = ViewModelProviders.of(activity!!)[MainActivityViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showWebView()
    }

    override fun onResume() {
        super.onResume()
        mainActivityViewModel.inactiveOrdering()
    }

    private fun showWebView() {
        detailedFrag_wv_url.settings.javaScriptEnabled = true
        detailedFrag_wv_url.settings.domStorageEnabled = true
        detailedFrag_wv_url.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest): Boolean {
                return false
            }
        }
        detailedFrag_wv_url.loadUrl(mainActivityViewModel.selectedURL.value)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detailed_post, container, false)
        return mBinding.root
    }

}