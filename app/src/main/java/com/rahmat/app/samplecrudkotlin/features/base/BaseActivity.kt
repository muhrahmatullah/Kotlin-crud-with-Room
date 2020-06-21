package com.rahmat.app.samplecrudkotlin.features.base

import androidx.lifecycle.ViewModel
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import java.lang.reflect.ParameterizedType

/**
 * Created by muhrahmatullah on 26/09/18.
 */
abstract class BaseActivity<T : ViewDataBinding, V : ViewModel> : AppCompatActivity(){

    val NO_VIEW_MODEL_BINDING_VARIABLE = -1

    private lateinit var mViewModel: V
    private lateinit var mViewDataBinding: T

    abstract fun getViewModelBindingVariable() : Int

    @LayoutRes
    abstract fun getLayoutId() : Int

    fun getViewModel() : V = mViewModel

    fun getDataBinding() : T = mViewDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        performDataBinding()
//        provideViewModel()

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
    }

    private fun getViewModelClass(aClass: Class<*>): Class<V> {
        val type = aClass.genericSuperclass

        return if (type is ParameterizedType) {
            type.actualTypeArguments[1] as Class<V>
        } else {
            getViewModelClass(aClass.superclass as Class<*>)
        }
    }

}