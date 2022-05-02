package com.example.mylexicon.ui.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.widget.RemoteViews
import com.bumptech.glide.Glide
import com.example.core.ui.model.AppState
import com.example.mylexicon.R
import com.example.mylexicon.interactor.IDBInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AppWidget: AppWidgetProvider(), KoinComponent {

    private val interactor: IDBInteractor<AppState> by inject()
    private val scope = CoroutineScope(Dispatchers.IO)

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        if (intent.action != LEXICON_ACTION) return
        val remoteViews = RemoteViews(context.packageName, R.layout.widget_word_of_day)
        getRandomWordFromBD(context, remoteViews)
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)

        appWidgetIds.forEach {
            val remoteViews = RemoteViews(context.packageName, R.layout.widget_word_of_day)
            val pendingIntent = Intent(context, AppWidget::class.java).let { intent ->
                intent.action = LEXICON_ACTION
                PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
            }

            remoteViews.apply {
                setOnClickPendingIntent(R.id.widget_container, pendingIntent)
                getRandomWordFromBD(context, this, appWidgetManager)
            }
        }
    }


    private fun getRandomWordFromBD(
        context: Context,
        remoteViews: RemoteViews,
        appWidgetManager: AppWidgetManager = AppWidgetManager.getInstance(context)
    ) {
        val componentName = ComponentName(context, AppWidget::class.java)
        remoteViews.apply {
            scope.launch {
                val state = interactor.loadData()
                if (state is AppState.Success) {
                    state.data?.let { data ->
                        val word = data[(data.indices).random()]
                        setTextViewText(R.id.widget_header, word.word)
                        setTextViewText(R.id.widget_transcription, "[${word.transcription}]")
                        setTextViewText(R.id.widget_description, word.translation)
                        word.imageUrl?.let { url ->
                            setImageViewBitmap(R.id.widget_image, getBitmapFromUrl(url, context))
                        }
                        appWidgetManager.updateAppWidget(componentName, this@apply)
                    }
                }
            }
        }
    }

    private fun getBitmapFromUrl(imageUrl: String, context: Context) : Bitmap {
        return Glide.with(context).asBitmap().load("https:$imageUrl").submit().get()
    }

    companion object {
        private const val LEXICON_ACTION = "lexicon_action"
    }
}