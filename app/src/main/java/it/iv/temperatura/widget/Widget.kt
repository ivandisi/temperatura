package it.iv.temperatura.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import it.iv.temperatura.R
import it.iv.temperatura.model.TemperatureData
import it.iv.temperatura.net.DataProvider
import it.iv.temperatura.net.DataProviderInterface
import java.lang.StringBuilder


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

    override fun onEnabled(context: Context) {
    }

    override fun onDisabled(context: Context) {
    }

    companion object {
        internal fun updateAppWidget(
            context: Context, appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {
            DataProvider.runRequest(object: DataProviderInterface {
                override fun onSuccess(result: TemperatureData?) {

                    val views = RemoteViews(context.packageName,
                        R.layout.widget_view
                    )
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
                            .append(result?.getExternal()?.getSensorData()?.temp)
                            .append(" °"))
                    views.setTextViewText(
                        R.id.tvOWHumidity,
                        StringBuilder()
                            .append(result?.getExternal()?.getSensorData()?.humidity)
                            .append(" %")
                    )
                    views.setTextViewText(
                        R.id.tvOWPressure,
                        StringBuilder()
                            .append(result?.getExternal()?.getSensorData()?.pressure)
                            .append(" hpa")
                    )
                    views.setTextViewText(
                        R.id.tvOWWind,
                        StringBuilder()
                            .append(result?.getExternal()?.getSensorDataWind()?.speed)
                            .append(" m/s")
                    )

                    appWidgetManager.updateAppWidget(appWidgetId, views)
                }
            })

        }
    }
}

