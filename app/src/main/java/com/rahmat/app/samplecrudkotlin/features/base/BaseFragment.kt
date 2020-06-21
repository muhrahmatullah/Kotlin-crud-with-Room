package com.rahmat.app.samplecrudkotlin.features.base

import androidx.lifecycle.ViewModel
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.AndroidSupportInjection

/**
 * Created by muhrahmatullah on 26/09/18.
 */
abstract class BaseFragment<T : ViewDataBinding, V : ViewModel> : Fragment() {

    private val NO_VIEW_MODEL_BINDING_VARIABLE = -1

    lateinit var mViewModel: V
    lateinit var mViewDataBinding: T

    abstract fun getViewModelBindingVariable() : Int

    @LayoutRes
    abstract fun getLayoutId() : Int

    fun getViewModel() : V = mViewModel

    fun getDataBinding() : T = mViewDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return mViewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (getViewModelBindingVariable() != NO_VIEW_MODEL_BINDING_VARIABLE) {
            mViewDataBinding.setVariable(getViewModelBindingVariable(), mViewModel)
            mViewDataBinding.executePendingBindings()
        }
    }


}