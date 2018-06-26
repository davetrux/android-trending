package com.truxall.domain.interactor

import com.truxall.domain.executor.PostExecutionThread
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers

abstract class CompletableUseCase<in Params> constructor(
        private val postExecutionThread: PostExecutionThread) {

    private val disposables = CompositeDisposable()

    protected abstract fun buildCompletableUseCase(params: Params? = null): Completable

    open fun excecute(observer: DisposableCompletableObserver, params: Params? = null) {
        val completable = this.buildCompletableUseCase(params).subscribeOn(Schedulers.io()).observeOn(postExecutionThread.scheduler)
        addDisposable(completable.subscribeWith(observer))
    }

    fun dispose() {
        disposables.dispose()
    }
    
    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}