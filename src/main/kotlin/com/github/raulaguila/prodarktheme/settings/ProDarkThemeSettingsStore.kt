package com.github.raulaguila.prodarktheme.settings

import com.github.raulaguila.prodarktheme.ProDarkTheme
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil
import org.jetbrains.annotations.Nullable

@State(name = "ProDarkThemeSettings", storages = [(Storage("prodark_theme.xml"))])
class ProDarkThemeSettingsStore: PersistentStateComponent<ProDarkThemeSettingsStore> {

    companion object {
        val instance: ProDarkThemeSettingsStore
            get() = ApplicationManager.getApplication().getService(ProDarkThemeSettingsStore::class.java)
    }
    var isProDarkEnabled = true
    var alwaysApply = false
    var showNotificationOnUpdate = true
    var version = "unknown"
    var themeName = ProDarkTheme.UNKNOWN

    @Nullable
    override fun getState() = this

    override fun loadState(state: ProDarkThemeSettingsStore) {
        XmlSerializerUtil.copyBean(state, this)
    }
}