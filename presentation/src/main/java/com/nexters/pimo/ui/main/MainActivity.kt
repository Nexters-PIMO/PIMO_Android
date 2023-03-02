package com.nexters.pimo.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.nexters.pimo.domain.model.Post
import com.nexters.pimo.ui.base.BaseActivity
import com.nexters.pimo.ui.friend.FriendActivity
import com.nexters.pimo.ui.theme.FimoTheme
import com.nexters.pimo.ui.upload.UploadActivity
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Integer.min

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FimoTheme {
                MainScreen(
                    viewModel = viewModel,
                    startUploadActivity = ::startUploadActivity,
                    startFriendActivity = ::startFriendActivity,
                    sharePost = ::sharePost
                )
            }
        }
    }

    private fun startUploadActivity() {
        UploadActivity.startActivity(this)
    }

    private fun startFriendActivity() {
        FriendActivity.startActivity(this)
    }

    private fun sharePost(post: Post) {
        val shareText =
            "[FIMO]\n${
                post.textImages[min(
                    1,
                    post.textImages.size - 1
                )].text
            }\n\n${post.writer.nickname}님의 ${post.writer.archiveName} 아카이브를 구경해보세요\nhttps://fimo.page.link/u/${post.writer.nickname}"

        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }
}
