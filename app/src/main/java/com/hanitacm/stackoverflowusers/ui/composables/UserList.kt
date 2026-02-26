package com.hanitacm.stackoverflowusers.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.fromHtml
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
    ) {
        items(items = users, key = { user -> user.id }) { user ->
            UserRow(
                name = user.name,
                reputation = user.reputation,
                thumbnail = user.profileImage,
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
            ImageUrl(
                url = thumbnail, modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )
        }
        Column {
            Text(
                text = AnnotatedString.fromHtml(name),
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
                User(id = 1, "Hanita", 10, profileImage = ""),
                User(id = 2, "Hanita2", 10, profileImage = ""),
                User(id = 3, "Hanita3", 10, profileImage = ""),
                User(id = 4, "Hanita4", 10, profileImage = "")
            )
        )
    }
}