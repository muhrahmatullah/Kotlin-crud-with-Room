package com.rahmat.app.samplecrudkotlin.features.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import java.lang.reflect.ParameterizedType
import javax.inject.Inject

/**
 * Created by muhrahmatullah on 26/09/18.
 */
abstract class BaseActivity<T : ViewDataBinding, V : ViewModel> : AppCompatActivity(), HasSupportFragmentInjector {

    val NO_VIEW_MODEL_BINDING_VARIABLE = -1

    private lateinit var mViewModel: V
    private lateinit var mViewDataBinding: T

    @Inject
    lateinit var mViewModelFactory : ViewModelProvider.Factory

    abstract fun getViewModelBindingVariable() : Int

    @LayoutRes
    abstract fun getLayoutId() : Int

    fun getViewModel() : V = mViewModel

    fun getDataBinding() : T = mViewDataBinding

    @Inject
    lateinit var mFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        performDataBinding()
        provideViewModel()

    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return mFragmentInjector
    }

    private fun performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        if (getViewModelBindingVariable() != NO_VIEW_MODEL_BINDING_VARIABLE) {
            setViewModelBindingVariable()
        }
    }

    private fun setViewModelBindingVariable() {
        mViewDataBinding.setVariable(getViewModelBindingVariable(), mViewModel)
        mViewDataBinding.executePendingBindings()
    }

    private fun provideViewModel() {
        val clazz = getViewModelClass(javaClass)
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(clazz)
    }

    private fun getViewModelClass(aClass: Class<*>): Class<V> {
        val type = aClass.genericSuperclass

        return if (type is ParameterizedType) {
            type.actualTypeArguments[1] as Class<V>
        } else {
            getViewModelClass(aClass.superclass)
        }
    }

}