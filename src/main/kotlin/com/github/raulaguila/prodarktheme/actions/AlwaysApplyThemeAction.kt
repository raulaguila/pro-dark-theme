package com.github.raulaguila.prodarktheme.actions

import com.github.raulaguila.prodarktheme.ProDarkTheme
import com.github.raulaguila.prodarktheme.ProDarkThemeManager
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.DumbAwareAction

class AlwaysApplyThemeAction(
    text: String = "Set as Default",
    private val proDarkTheme: String = ProDarkTheme.DARK
) : DumbAwareAction(text) {
    override fun actionPerformed(e: AnActionEvent) {
        ProDarkThemeManager.getInstance().switchToProDarkTheme(true, proDarkTheme)
    }
}