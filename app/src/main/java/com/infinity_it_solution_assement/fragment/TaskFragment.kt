package com.infinity_it_solution_assement.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import org.alfonz.utility.Logcat
import java.util.*

abstract class TaskFragment() : Fragment() {
    private val mLock = Any()
    private var mReady = false
    private val mPendingCallbacks: MutableList<Runnable> = LinkedList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        synchronized(mLock) {
            mReady = true
            var pendingCallbacks = mPendingCallbacks.size
            while (pendingCallbacks-- > 0) {
                val runnable = mPendingCallbacks.removeAt(0)
                runNow(runnable)
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        synchronized(mLock) { mReady = false }
    }

    protected fun runTaskCallback(runnable: Runnable) {
        if (mReady) runNow(runnable) else addPending(runnable)
    }

//    	protected void executeTask(AsyncTask<Void, ?, ?> task) {
//    		 use AsyncTask.THREAD_POOL_EXECUTOR or AsyncTask.SERIAL_EXECUTOR
//    		task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//    	}
    private fun runNow(runnable: Runnable) {
        //Logcat.d("TaskFragment.runNow(): " + runnable.getClass().getEnclosingMethod());
        requireActivity().runOnUiThread(runnable)
    }

    private fun addPending(runnable: Runnable) {
        synchronized(mLock) {
            Logcat.d("TaskFragment.addPending(): " + runnable.javaClass.enclosingMethod)
            mPendingCallbacks.add(runnable)
        }
    }

    abstract fun onDownloadRequested(url: String?, suggestedFilename: String?, mimeType: String?, contentLength: Long, contentDisposition: String?, userAgent: String?)
    abstract fun onGeolocationPermissionsShowPrompt()
}