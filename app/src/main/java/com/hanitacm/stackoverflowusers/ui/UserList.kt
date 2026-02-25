package com.hanitacm.stackoverflowusers.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hanitacm.stackoverflowusers.ui.model.User
import com.hanitacm.stackoverflowusers.ui.theme.StackOverflowUsersTheme

@Composable
internal fun UserList(users: List<User>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
    ) {
        items(users) { user ->
            UserRow(
                name = user.name,
                reputation = user.reputation,
                thumbnail = "",
            )
        }
    }
}

@Composable
private fun UserRow(
    name: String,
    reputation: Int,
    thumbnail: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column {
            Text(
                text = name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(text = reputation.toString())

        }
    }
}


@Preview(showBackground = true)
@Composable
fun UserListPreview() {
    StackOverflowUsersTheme {
        UserList(
            listOf(
                User("Hanita", 10),
                User("Hanita2", 10),
                User("Hanita3", 10),
                User("Hanita4", 10)
            )
        )
    }
}