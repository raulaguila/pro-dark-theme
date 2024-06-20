package com.github.raulaguila.prodarktheme.actions

import com.github.raulaguila.prodarktheme.icons.ProDarkIcons
import com.intellij.ide.BrowserUtil
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.DumbAwareAction

class StarGithubRepoAction: DumbAwareAction("Star Repo", "", ProDarkIcons.GitHubStar) {
    override fun actionPerformed(e: AnActionEvent) {
        BrowserUtil.open("https://github.com/raulaguila/pro-dark-theme")
    }
}