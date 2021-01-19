package com.example.stkovrfloquestions.services

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import android.widget.RemoteViewsService.RemoteViewsFactory
import com.example.stkovrfloquestions.R
import com.example.stkovrfloquestions.appwidgets.StkOvrFloAppWidgetProvider
import com.example.stkovrfloquestions.models.Item
import com.example.stkovrfloquestions.repositories.QuestionRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.flow.collect


class StackWidgetService : RemoteViewsService() {

    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory {
        return StackRemoteViewsFactory(this.applicationContext, intent)
    }

    internal class StackRemoteViewsFactory(context: Context, intent: Intent): RemoteViewsFactory {

        /**
         * Property Variables
         */

        private var mWidgetItems: MutableList<Item> = ArrayList()
        private val mContext: Context = context
        private val mAppWidgetId: Int = intent.getIntExtra(
            AppWidgetManager.EXTRA_APPWIDGET_ID,
            AppWidgetManager.INVALID_APPWIDGET_ID
        )

        /**
         * RemoteViewFactory Methods
         */

        @InternalCoroutinesApi
        override fun onCreate() {
            // In onCreate() you setup any connections / cursors to your data source. Heavy lifting,
            // for example downloading or creating content etc, should be deferred to onDataSetChanged()
            // or getViewAt(). Taking more than 20 seconds in this call will result in an ANR.
//            for (i in 0 until mCount) {
//                mWidgetItems.add("$i!")
//            }
            // We sleep for 3 seconds here to show how the empty view appears in the interim.
            // The empty view is set in the StackWidgetProvider and should be a sibling of the
            // collection view.
//            try {
//                Thread.sleep(3000)
//            } catch (e: InterruptedException) {
//                e.printStackTrace()
//            }
        }

        override fun onDestroy() {
            mWidgetItems.clear()
        }

        override fun getCount(): Int {
            return mWidgetItems.size
        }

        override fun getViewAt(position: Int): RemoteViews {
            val rv = RemoteViews(mContext.packageName, R.layout.widget_item)

            rv.setTextViewText(R.id.widget_textView_name, mWidgetItems[position].owner.display_name)
            rv.setTextViewText(R.id.widget_textView_question, mWidgetItems[position].title)

            // Next, we set a fill-intent which will be used to fill-in the pending intent template
            // which is set on the collection view in StackWidgetProvider.
            val extras = Bundle()
            extras.putString(StkOvrFloAppWidgetProvider.EXTRA_ITEM, mWidgetItems[position].link)
            val fillInIntent = Intent()
            fillInIntent.putExtras(extras)
            rv.setOnClickFillInIntent(R.id.widget_item, fillInIntent)
            // You can do heaving lifting in here, synchronously. For example, if you need to
            // process an image, fetch something from the network, etc., it is ok to do it here,
            // synchronously. A loading view will show up in lieu of the actual contents in the
            // interim.
//            try {
//                println("Loading view $position")
//                Thread.sleep(500)
//            } catch (e: InterruptedException) {
//                e.printStackTrace()
//            }
            // Return the remote views object.
            return rv
        }

        override fun getLoadingView(): RemoteViews? {
            // You can create a custom loading view (for instance when getViewAt() is slow.) If you
            // return null here, you will get the default loading view.
            return null
        }

        override fun getViewTypeCount(): Int {
            return 1
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun hasStableIds(): Boolean {
            return false
        }

        @InternalCoroutinesApi
        override fun onDataSetChanged() {
            // This is triggered when you call AppWidgetManager notifyAppWidgetViewDataChanged
            // on the collection view corresponding to this factory. You can do heaving lifting in
            // here, synchronously. For example, if you need to process an image, fetch something
            // from the network, etc., it is ok to do it here, synchronously. The widget will remain
            // in its current state while work is being done here, so you don't need to worry about
            // locking up the widget.

            runBlocking {
                QuestionRepository.getQuestions(1).collect { items ->
                    if (!items.isNullOrEmpty()) {
                        mWidgetItems = items.toMutableList()
                    }
                }
            }
        }

    }
}