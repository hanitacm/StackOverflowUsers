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
import androidx.compose.material3.Button
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
import com.hanitacm.stackoverflowusers.ui.model.ui.UserUi
import com.hanitacm.stackoverflowusers.ui.theme.StackOverflowUsersTheme

@Composable
internal fun UserList(
    users: List<UserUi>,
    modifier: Modifier = Modifier,
    onFollowClick: (Int) -> Unit = {},
    onUnfollowClick: (Int) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        items(items = users, key = { user -> user.id }) { user ->
            UserRow(
                name = user.name,
                reputation = user.reputation,
                thumbnail = user.profileImage,
                isFollowee = user.isFollowee,
                onButtonClick = {
                    if (user.isFollowee) {
                        onUnfollowClick(user.id)
                    } else {
                        onFollowClick(user.id)
                    }
                }

            )
        }
    }
}

@Composable
private fun UserRow(
    name: String,
    reputation: String,
    thumbnail: String,
    isFollowee: Boolean,
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ImageUrl(
            url = thumbnail, modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = AnnotatedString.fromHtml(name),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(text = reputation)

        }
        if (isFollowee) {
            Button(onClick = { onButtonClick() }) {
                Text(text = "Following")
            }
        } else {
            Button(onClick = { onButtonClick() }) {
                Text(text = "Follow")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun UserListPreview() {
    StackOverflowUsersTheme {
        UserList(
            listOf(
                UserUi(id = 1, "Hanita", "10", profileImage = "", isFollowee = true),
                UserUi(id = 2, "Hanita2", "104444", profileImage = "", isFollowee = false),
                UserUi(id = 3, "Hanita3", "1032434", profileImage = "", isFollowee = true),
                UserUi(id = 4, "Hanita4", "10244", profileImage = "", isFollowee = false)
            ),
            onFollowClick = {},
            onUnfollowClick = {}
        )
    }
}
