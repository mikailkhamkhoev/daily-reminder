package com.example.dailyreminder

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.concurrent.TimeUnit

class ReminderViewModel(application: Application) : AndroidViewModel(application) {

    private val workManager = WorkManager.getInstance(application)
    private val workName = "DailyReminderPeriodicWork"

    private val _isReminderEnabled = MutableStateFlow(false)
    val isReminderEnabled: StateFlow<Boolean> = _isReminderEnabled.asStateFlow()

    fun toggleReminder() {
        if (_isReminderEnabled.value) {
            cancelDailyReminder()
        } else {
            scheduleDailyReminder()
        }
    }

    private fun scheduleDailyReminder() {

        val reminderRequest = PeriodicWorkRequestBuilder<ReminderWorker>(
            24, TimeUnit.HOURS
        ).build()

        workManager.enqueueUniquePeriodicWork(
            workName,
            ExistingPeriodicWorkPolicy.KEEP,
            reminderRequest
        )

        _isReminderEnabled.value = true
    }

    private fun cancelDailyReminder() {
        workManager.cancelUniqueWork(workName)
        _isReminderEnabled.value = false
    }
}
