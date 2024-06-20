package com.github.raulaguila.prodarktheme


import com.github.raulaguila.prodarktheme.settings.ProDarkThemeSettingsStore
import com.intellij.ide.plugins.IdeaPluginDescriptor
import com.intellij.ide.plugins.PluginManagerCore
import com.intellij.ide.ui.LafManager
import com.intellij.openapi.extensions.PluginId
import com.intellij.util.containers.ContainerUtil

object ProDarkTheme {
    const val UNKNOWN = "UNKNOWN"
    const val DARK = "Pro Dark Theme"
}

class ProDarkThemeManager {
    companion object {
        const val pluginId = "com.github.raulaguila.prodarktheme"
        private var myObject: ProDarkThemeManager? = null
        fun getInstance(): ProDarkThemeManager {
            if (myObject == null) {
                myObject = ProDarkThemeManager()
            }
            return myObject!!
        }
    }

    private fun getPlugin(): IdeaPluginDescriptor? = PluginManagerCore.getPlugin(PluginId.getId(pluginId))
    fun isProDarkThemeReady(): Boolean {
        try {
            if (getPlugin()?.isEnabled != null) {
                val ProDarkTheme =
                    LafManager.getInstance().installedLookAndFeels.firstOrNull { it.toString().contains(ProDarkTheme.DARK) }
                return ProDarkTheme != null
            }
            return false
        } catch (e: Exception) {
            return false
        }
    }

    fun switchToProDarkTheme(always: Boolean = false, selectedProDarkTheme: String = ProDarkTheme.DARK) {
        try {
            if (isProDarkThemeReady()) {
                val convertedSelectedProDarkTheme = convertOldToNewTheme(selectedProDarkTheme)

                val ProDarkTheme = LafManager.getInstance().installedLookAndFeels.firstOrNull {
                    it.toString().contains(convertedSelectedProDarkTheme)
                }
                ContainerUtil.find(LafManager.getInstance().installedLookAndFeels) {
                    it.name === convertedSelectedProDarkTheme
                }
                if (ProDarkTheme != null) {
                    LafManager.getInstance().currentLookAndFeel = ProDarkTheme
                }
                if (always) {
                    val settings = ProDarkThemeSettingsStore.instance
                    settings.alwaysApply = true
                    settings.themeName = selectedProDarkTheme
                }
            }
        } catch (e: Exception) {
            throw (Error("Unable to select the default theme $selectedProDarkTheme", e))
        }
    }

    fun isProDarkThemeSelected(): Boolean {
        val theme = LafManager.getInstance().currentLookAndFeel
        if (theme != null) {
            return theme.toString().contains(ProDarkTheme.DARK)
        }
        return false
    }


    private fun convertOldToNewTheme(theme: String): String {
        return when (theme) {
            "DARK" -> "Pro Dark Theme"
            else -> theme
        }
    }
}
