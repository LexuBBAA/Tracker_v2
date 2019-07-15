package com.lexu.tracking.models

import com.lexu.models.Priority
import com.lexu.models.Status
import com.lexu.models.Type
import java.sql.Date

data class TeamTask(val type: Type, val status: Status, val title: String, val id: String? = null, val priority: Priority)