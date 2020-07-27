package it.iv.temperatura.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.RemoteViews
import it.iv.temperatura.R
import it.iv.temperatura.model.TemperatureData
import it.iv.temperatura.net.DataProvider
import it.iv.temperatura.net.DataProviderInterface
import java.util.logging.Logger


class Widget : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(
                context,
                appWidgetManager,
                appWidgetId
            )
        }
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)

        val appWidgetManager = AppWidgetManager.getInstance(context)

        val thisAppWidget = ComponentName(
            context!!.packageName,
            Widget::class.java.getName()
        )
        val appWidgetIds = appWidgetManager.getAppWidgetIds(thisAppWidget)
        onUpdate(context!!, appWidgetManager, appWidgetIds)

    }

    override fun onEnabled(context: Context) {
    }

    override fun onDisabled(context: Context) {
    }

    companion object {
        internal fun updateAppWidget(
            context: Context, appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {
            val views = RemoteViews(context.packageName,
                R.layout.widget_view
            )

            val intentSync = Intent(context, Widget::class.java)
            intentSync.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE

            val pendingSync = PendingIntent.getBroadcast(
                context,
                0,
                intentSync,
                PendingIntent.FLAG_UPDATE_CURRENT
            )

            views.setOnClickPendingIntent(R.id.root, pendingSync)

            DataProvider.runRequest(object: DataProviderInterface {
                override fun onSuccess(result: TemperatureData?) {

                    views.setTextViewText(
                        R.id.tvNTemp,
                        StringBuilder()
                            .append(result?.getInternal()?.getSensorData()?.temperature)
                            .append(" °"))
                    views.setTextViewText(
                        R.id.tvNHumidity,
                        StringBuilder()
                            .append(result?.getInternal()?.getSensorData()?.humidity)
                            .append(" %")
                    )
                    views.setTextViewText(
                        R.id.tvNPressure,
                        StringBuilder()
                            .append(result?.getInternal()?.getSensorData()?.pressure)
                            .append(" hpa")
                    )
                    views.setTextViewText(
                        R.id.tvNCO2,
                        StringBuilder()
                            .append(result?.getInternal()?.getSensorData()?.CO2)
                            .append(" ppm")
                    )

                    views.setTextViewText(
                        R.id.tvOWTemp,
                        StringBuilder()
                            .append(result?.getExternalUnito()?.temperature?.replace("C",""))
                    )
                    views.setTextViewText(
                        R.id.tvOWHumidity,
                        StringBuilder()
                            .append(result?.getExternalUnito()?.humidity)
                    )
                    views.setTextViewText(
                        R.id.tvOWPressure,
                        StringBuilder()
                            .append(result?.getExternalUnito()?.pressure)
                    )
                    views.setTextViewText(
                        R.id.tvOWWind,
                        StringBuilder()
                            .append(result?.getExternalUnito()?.wind)
                    )

                    appWidgetManager.updateAppWidget(appWidgetId, views)
                }
            })

        }
    }
}

