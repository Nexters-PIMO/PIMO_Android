package com.nexters.pimo.ui.model

import com.nexters.pimo.domain.model.Post
import com.nexters.pimo.domain.model.TextImage
import com.nexters.pimo.domain.model.User
import java.time.LocalDateTime

object Dummy {
    val date = LocalDateTime.now()

    val textImage1 = TextImage(
        id = 0,
        text = "상처받는 것에 익숙해진 요즘\n그럼에도 피해가고 싶은 상처",
        imageUrl = "http://onejob.co.kr/data/editor/2006/3690976490_TLFbcIK5_d86420f2c0a91d7c3ae791147116c589948af941.png"
    )

    val textImage2 = TextImage(
        id = 1,
        text = "오늘 바람 좋지\n나는 너가 좋은데",
        imageUrl =
        "http://onejob.co.kr/data/editor/2006/3690976490_kg35XbTl_d86420f2c0a91d7c3ae791147116c589948af941.png"
    )

    val textImage3 = TextImage(
        id = 2,
        text = "그대가 말을 할 때 정적이 흘렀다\n내게로 오는 말이 아니었지만\n나 혼자 설레었다\n착각에 빠졌다",
        imageUrl =
        "http://onejob.co.kr/data/editor/2006/3690976490_DR8jkEma_d86420f2c0a91d7c3ae791147116c589948af941.png"
    )

    val textImage4 = TextImage(
        id = 3,
        text = "화려한 배경에 목매는 것보다는\n싱거운 배경 앞에서도\n빛나는 자신이 되기를",
        imageUrl =
        "http://onejob.co.kr/data/editor/2006/3690976490_nwEkPxMe_e93beebbdd4b4c8ed84d43c27496f2f59d05e690.png"
    )

    val textImage5 = TextImage(
        id = 4,
        text = "오늘은 비가 온대요\n내일은 바람이 불고\n모레는 아마 태풍이 오겠죠",
        imageUrl =
        "http://onejob.co.kr/data/editor/2006/3690976490_wLnAxt9r_e93beebbdd4b4c8ed84d43c27496f2f59d05e690.png"
    )

    val textImage6 = TextImage(
        id = 5,
        text = "억지로 어울리지 마라\n몸에 맞지도 않는 옷을 입고\n이 밤 춤을 추지도 마라",
        imageUrl =
        "http://onejob.co.kr/data/editor/2006/3690976490_lpY3wRCe_e93beebbdd4b4c8ed84d43c27496f2f59d05e690.png"
    )

    val textImage7 = TextImage(
        id = 6,
        text = "어제 새겨진 상처들을\n어루 만져주는 새벽이 되기를",
        imageUrl =
        "http://onejob.co.kr/data/editor/2006/3690976490_0cjkXdwV_e93beebbdd4b4c8ed84d43c27496f2f59d05e690.png"
    )

    val textImage8 = TextImage(
        id = 7,
        text = "오늘 느낀점\n잘해주면 호구된다",
        imageUrl =
        "http://onejob.co.kr/data/editor/2006/3690976490_KST5Cyz7_ac61678aa39c15d639de5b44fba3313befc8baf0.png"
    )

    val textImage9 = TextImage(
        id = 8,
        text = "그대의 프롤로그는 최악이더라도\n에필로그는 부디 행복한 문장으로 쓰여지기를",
        imageUrl =
        "http://onejob.co.kr/data/editor/2006/3690976490_ILr9JZdt_ac61678aa39c15d639de5b44fba3313befc8baf0.png"
    )

    val tempPost = Post(
        id = 0, writer = User(
            id = 0,
            nickname = "yjyoon",
            archiveName = "밤에 쓰는 편지",
            profileImageUrl = "https://avatars.githubusercontent.com/u/72238126?v=4",
            posts = emptyList()
        ), postedTime = date, textImages = listOf(
            textImage2, textImage7, textImage9
        ), clapCount = 414, isClapped = true
    )

    val user1 = User(
        id = 0,
        nickname = "yjyoon",
        archiveName = "밤에 쓰는 편지",
        profileImageUrl = "https://avatars.githubusercontent.com/u/72238126?v=4",
        posts = List(4) { tempPost }
    )

    val user2 = User(
        id = 1,
        nickname = "okstring",
        archiveName = "Okhyeon Kim",
        profileImageUrl = "https://avatars.githubusercontent.com/u/62657991?v=4",
        posts = List(12) { tempPost }
    )

    val user3 = User(
        id = 2,
        nickname = "0inn",
        archiveName = "Youngin Kim",
        profileImageUrl = "https://avatars.githubusercontent.com/u/74968390?v=4",
        posts = List(7) { tempPost }
    )

    val user4 = User(
        id = 3,
        nickname = "yanghojoon",
        archiveName = "앵커리어",
        profileImageUrl = "https://avatars.githubusercontent.com/u/90880660?v=4",
        posts = List(32) { tempPost }
    )

    val user5 = User(
        id = 4,
        nickname = "harry7408",
        archiveName = "JongHyuk",
        profileImageUrl = "https://avatars.githubusercontent.com/u/84065395?v=4",
        posts = List(23) { tempPost }
    )

    val user6 = User(
        id = 5,
        nickname = "mikekks",
        archiveName = "Mingyu Song",
        profileImageUrl = "https://avatars.githubusercontent.com/u/100754581?v=4",
        posts = List(6) { tempPost }
    )

    val user7 = User(
        id = 6,
        nickname = "merryberry01",
        archiveName = "Youngjun",
        profileImageUrl = "https://avatars.githubusercontent.com/u/55453184?v=4",
        posts = List(15) { tempPost }
    )

    val user8 = User(
        id = 7,
        nickname = "DonghaeSuh",
        archiveName = "동해",
        profileImageUrl = "https://avatars.githubusercontent.com/u/82081872?v=4",
        posts = List(68) { tempPost }
    )

    val user9 = User(
        id = 8,
        nickname = "chorom_ham",
        archiveName = "초롱",
        profileImageUrl = "https://avatars.githubusercontent.com/u/52379950?v=4",
        posts = List(53) { tempPost }
    )

    val user10 = User(
        id = 9,
        nickname = "hanbikan",
        archiveName = "Hanbit Kang",
        profileImageUrl = "https://avatars.githubusercontent.com/u/58168528?v=4",
        posts = List(24) { tempPost }
    )

    val user11 = User(
        id = 10,
        nickname = "KY00KIM",
        archiveName = "Kyumin-Kim",
        profileImageUrl = "https://avatars.githubusercontent.com/u/55953815?v=4",
        posts = List(5) { tempPost }
    )

    val user12 = User(
        id = 11,
        nickname = "Jaewoook",
        archiveName = "Jaewook Ahn",
        profileImageUrl = "https://avatars.githubusercontent.com/u/5039744?v=4",
        posts = List(85) { tempPost }
    )

    val user13 = User(
        id = 12,
        nickname = "ekfvnddl99",
        archiveName = "Kim Hyeonji",
        profileImageUrl = "https://avatars.githubusercontent.com/u/48302738?v=4",
        posts = List(453) { tempPost }
    )

    val user14 = User(
        id = 13,
        nickname = "earthssu",
        archiveName = "Shinhan Card",
        profileImageUrl = "https://avatars.githubusercontent.com/u/39795055?v=4",
        posts = List(22) { tempPost }
    )

    val user15 = User(
        id = 14,
        nickname = "com8564",
        archiveName = "KyungYoon Ham",
        profileImageUrl = "https://avatars.githubusercontent.com/u/57666450?v=4",
        posts = List(87) { tempPost }
    )

    val user16 = User(
        id = 15,
        nickname = "cheeppang",
        archiveName = "HeebbangDuck",
        profileImageUrl = "https://avatars.githubusercontent.com/u/97587597?v=4",
        posts = List(41) { tempPost }
    )

    val user17 = User(
        id = 16,
        nickname = "915dbfl",
        archiveName = "youl",
        profileImageUrl = "https://avatars.githubusercontent.com/u/64644738?v=4",
        posts = List(98) { tempPost }
    )

    val user18 = User(
        id = 17,
        nickname = "jooy2",
        archiveName = "Jooy",
        profileImageUrl = "https://avatars.githubusercontent.com/u/48266008?v=4",
        posts = List(75) { tempPost }
    )

    val user19 = User(
        id = 18,
        nickname = "stargatex",
        archiveName = "Lahiru J",
        profileImageUrl = "https://avatars.githubusercontent.com/u/14094458?v=4",
        posts = List(72) { tempPost }
    )

    val user20 = User(
        id = 19,
        nickname = "bebe0612",
        archiveName = "James Lee",
        profileImageUrl = "https://avatars.githubusercontent.com/u/72788825?v=4",
        posts = List(11) { tempPost }
    )

    val user21 = User(
        id = 20,
        nickname = "oyunseong",
        archiveName = "oyunseong",
        profileImageUrl = "https://avatars.githubusercontent.com/u/42116216?v=4",
        posts = List(42) { tempPost }
    )

    val user22 = User(
        id = 20,
        nickname = "wlrma0108",
        archiveName = "kim hojoong",
        profileImageUrl = "https://avatars.githubusercontent.com/u/82634705?v=4",
        posts = List(68) { tempPost }
    )

    val user23 = User(
        id = 21,
        nickname = "gun-bro98",
        archiveName = "JS Developer",
        profileImageUrl = "https://avatars.githubusercontent.com/u/64679046?v=4",
        posts = List(93) { tempPost }
    )

    val user24 = User(
        id = 22,
        nickname = "jumining",
        archiveName = "멋주밍",
        profileImageUrl = "https://avatars.githubusercontent.com/u/76610340?v=4",
        posts = List(1) { tempPost }
    )

    val dummyPosts = listOf(
        Post(
            id = 0, writer = user1, postedTime = date, textImages = listOf(
                textImage2, textImage7, textImage9
            ), clapCount = 414, isClapped = true
        ),
        Post(
            id = 0, writer = user2, postedTime = date.minusMinutes(3), textImages = listOf(
                textImage6, textImage3, textImage1
            ), clapCount = 243, isClapped = false
        ),
        Post(
            id = 0, writer = user3, postedTime = date.minusMinutes(8), textImages = listOf(
                textImage4, textImage8
            ), clapCount = 528, isClapped = true
        ),
        Post(
            id = 0, writer = user4, postedTime = date.minusMinutes(42), textImages = listOf(
                textImage5
            ), clapCount = 423, isClapped = false
        ),
    )

    val dummyUser = User(
        id = 0,
        nickname = "윤여준",
        archiveName = "밤에 쓰는 편지",
        profileImageUrl = "https://avatars.githubusercontent.com/u/72238126?v=4",
        posts = listOf(
            Post(
                id = 0, writer = user1, postedTime = date, textImages = listOf(
                    textImage2, textImage7, textImage9
                ), clapCount = 414, isClapped = true
            ),
            Post(
                id = 0, writer = user1, postedTime = date.minusMinutes(3), textImages = listOf(
                    textImage6, textImage3, textImage1
                ), clapCount = 243, isClapped = false
            ),
            Post(
                id = 0, writer = user1, postedTime = date.minusMinutes(8), textImages = listOf(
                    textImage4, textImage8
                ), clapCount = 528, isClapped = true
            ),
            Post(
                id = 0, writer = user1, postedTime = date.minusMinutes(42), textImages = listOf(
                    textImage5
                ), clapCount = 423, isClapped = false
            ),
        )
    )
}
