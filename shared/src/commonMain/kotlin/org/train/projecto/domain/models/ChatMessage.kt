package org.train.projecto.domain.models

data class ChatMessage(
    val id: String,
    val text: String,
    val sender: String,
    val sentAt: Long

)
