package com.example.stkovrfloquestions.appwidgets

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.RemoteViews
import android.widget.Toast
import com.example.stkovrfloquestions.R
import com.example.stkovrfloquestions.activities.MainActivity
import com.example.stkovrfloquestions.services.StackWidgetService


class StkOvrFloAppWidgetProvider : AppWidgetProvider() {

    companion object {
        const val TOAST_ACTION = "com.example.android.stackwidget.TOAST_ACTION"
        const val EXTRA_ITEM = "com.example.android.stackwidget.EXTRA_ITEM"
    }

    /**
     * AppWidgetProvider Methods
     */

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        appWidgetIds.forEach { appWidgetId ->
            val intent = Intent(context, StackWidgetService::class.java).apply {
                putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
                data = Uri.parse(toUri(Intent.URI_INTENT_SCHEME))
            }

            val rv = RemoteViews(context.packageName, R.layout.stk_ovr_flo_appwidget).apply {
                setRemoteAdapter(R.id.stack_view, intent)
                setEmptyView(R.id.stack_view, R.id.empty_view)
            }

            // Here we setup the a pending intent template. Individuals items of a collection
            // cannot setup their own pending intents, instead, the collection as a whole can
            // setup a pending intent template, and the individual items can set a fillInIntent
            // to create unique before on an item to item basis.
            val toastIntent = Intent(context, StkOvrFloAppWidgetProvider::class.java).apply {
                action = TOAST_ACTION
                putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
            }

            val toastPendingIntent = PendingIntent.getBroadcast(
                context, 0, toastIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )

            rv.setPendingIntentTemplate(R.id.stack_view, toastPendingIntent)

            appWidgetManager.updateAppWidget(appWidgetId, rv)
        }

        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    override fun onReceive(context: Context?, intent: Intent) {
        val mgr = AppWidgetManager.getInstance(context)
        if (intent.action == TOAST_ACTION) {
            val appWidgetId = intent.getIntExtra(
                AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID
            )
            val viewURL = intent.getStringExtra(EXTRA_ITEM)
            Toast.makeText(context, "Touched view $viewURL", Toast.LENGTH_SHORT).show()
            val webViewIntent = Intent(context, MainActivity::class.java).apply {
                putExtra(EXTRA_ITEM, viewURL)
            }
            context?.startActivity(webViewIntent)
        }

        super.onReceive(context, intent)
    }
}