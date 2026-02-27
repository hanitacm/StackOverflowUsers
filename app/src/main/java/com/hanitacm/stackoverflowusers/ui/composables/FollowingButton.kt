package com.hanitacm.stackoverflowusers.ui.composables

import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.hanitacm.stackoverflowusers.R
import com.hanitacm.stackoverflowusers.ui.theme.StackOverflowUsersTheme

@Composable
internal fun FollowingButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(text = stringResource(id = R.string.following_button_text))
    }
}


@Preview(showBackground = true)
@Composable
private fun FollowingButtonPreview() {
    StackOverflowUsersTheme { FollowingButton(onClick = {}) }
}


