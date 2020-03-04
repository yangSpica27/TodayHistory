package com.spica27.todayhistory.model

data class Sentence(
    val created_at: String,
    val creator: String,
    val creator_uid: Int,
    val from: String,
    val from_who: Any,
    val hitokoto: String,
    val id: Int,
    val reviewer: Int,
    val type: String,
    val uuid: String
)