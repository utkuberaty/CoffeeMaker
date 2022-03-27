package com.utku.base.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.utku.base.util.TAG

/**
 * Abstract base fragment for all fragments, all fragment extends [BaseFragment]
 * abstract viewModel forces all fragments to use viewModel
 * */

abstract class BaseFragment<VB : ViewBinding>(val bindingFactory: (LayoutInflater) -> VB): Fragment() {

    abstract val viewModel: ViewModel

    protected val viewBinding: VB by lazy { bindingFactory(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG,"onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i(TAG,"onCreateView")
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG,"onViewCreated")
    }
}