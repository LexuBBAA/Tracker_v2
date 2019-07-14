package com.tracker.trackerv2.configs

import java.io.Serializable

class SearchUserConfigBundle(
    val userId: String,
    var searchQuery: String? = null,
    var sortOrder: Config.SortOrder = Config.SortOrder.DEFAULT
): Serializable