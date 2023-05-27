package com.cngal.app.data.home

import com.cngal.app.R
import com.cngal.app.model.square.CommunityModel

val CommunityData: Set<CommunityModel> =
    setOf(
        CommunityModel(
            R.drawable.user,
            "CnGal玩家交流群",
            "本群无资源请支持正版，调戏看板娘要注意节度哦",
            "https://jq.qq.com/?_wv=1027&k=mG6qNvyg"
        ),
        CommunityModel(
            R.drawable.kanban_question,
            "CnGal编辑者交流与Bug反馈群",
            "群主很懒,什么都没有留下",
            "https://jq.qq.com/?_wv=1027&k=JzuI1IkF"
        ), CommunityModel(
            R.drawable.logo,
            "QQ频道",
            "CnGal资料站官方QQ频道",
            "https://qun.qq.com/qqweb/qunpro/share?_wv=3&_wwv=128&inviteCode=onAQQ&from=246610&biz=ka"
        )
    )
