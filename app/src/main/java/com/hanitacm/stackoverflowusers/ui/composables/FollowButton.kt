package com.hanitacm.stackoverflowusers.ui.composables

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.hanitacm.stackoverflowusers.R
import com.hanitacm.stackoverflowusers.ui.theme.StackOverflowUsersTheme

@Composable
internal fun FollowButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(text = stringResource(id = R.string.follow_button_text))
    }
}


@Preview(showBackground = true)
@Composable
private fun FollowButtonPreview() {
    StackOverflowUsersTheme {
        FollowButton(onClick = {})
    }
}