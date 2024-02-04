package com.example.chatsample.data.prefs

import com.chibatching.kotpref.KotprefModel

class UserPreferences : KotprefModel() {
    var loggedId by stringPref(default = "")
}