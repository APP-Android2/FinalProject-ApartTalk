package kr.co.lion.application.finalproject_aparttalk.ui.login

sealed class NavigationLoginEvent {
    data object NavigationToMain: NavigationLoginEvent()
    data object NavigationToSignUp: NavigationLoginEvent()
    data object NavigationToLogin: NavigationLoginEvent()
}