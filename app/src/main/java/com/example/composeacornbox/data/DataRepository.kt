package com.example.composeacornbox.data

import androidx.compose.ui.graphics.Color
import javax.inject.Inject

/**
 * @Author: LuoJia
 * @Date:
 * @Description: 首页数据仓库
 */

class DataRepository @Inject constructor() {

    fun getAccountInfo(): List<Account> {
        val list = ArrayList<Account>()
        list.apply {
            add(
                Account(
                    avatarUrl = "https://s1.ax1x.com/2022/03/25/qt89GF.png",
                    acnCoinNum = 6747105.1,
                    rmbNum = 1747.0
                )
            )
        }
        return list
    }

    fun getDAppsInfo(): List<DApp> {
        val list = ArrayList<DApp>()
        list.apply {
            add(DApp(imgUrl = "https://s1.ax1x.com/2022/03/25/qYLwGR.png", name = "WorkBox"))
            add(DApp(imgUrl = "https://oimrs6ayd.qnssl.com/dappicon.png", name = "存币生息"))
            add(DApp(imgUrl = "https://s1.ax1x.com/2022/03/25/qYLHeS.png", name = "tata"))
            add(DApp(imgUrl = "https://upload-images.jianshu.io/upload_images/760670-46d44568c7625580.png", name = "Shining Run"))
            add(DApp(imgUrl = "https://s1.ax1x.com/2022/03/25/qYLXJs.png", name = "Flappy Bird"))
        }
        return list
    }

    fun getRecommendInfo(): List<Recommend> {
        val list = ArrayList<Recommend>()
        list.apply {
            add(
                Recommend(
                    name = "WorkBox",
                    cover = "https://s1.ax1x.com/2022/03/25/qYL95d.png",
                    title = "做workbox任务，赢5000 ACN奖励",
                    bgColor = Color(0xfff57901)
                )
            )
            add(
                Recommend(
                    name = "tata",
                    cover = "https://s1.ax1x.com/2022/03/25/qYLJqU.png",
                    title = "分享即有回报",
                    bgColor = Color(0xff1965ff)
                )
            )
        }

        return list
    }

    fun getUserCenterInfo(): List<UserCenter> {
        val list = ArrayList<UserCenter>()
        list.apply {
            add(UserCenter(name = "任务中心", state = "认证后解锁", group = 0))
            add(UserCenter(name = "账单", state = "", group = 0))
            add(UserCenter(name = "身份认证", state = "去认证", group = 0))
            add(UserCenter(name = "绑定管理", state = "", group = 0))
            add(UserCenter(name = "备份账户", state = "", group = 1))
            add(UserCenter(name = "设置", state = "", group = 2))
            add(UserCenter(name = "关于", state = "", group = 2))
            add(UserCenter(name = "ACN社群", state = "", group = 2))
            add(UserCenter(name = "Logout", state = "", group = 2))
        }
        return list
    }

    fun getWorkBoxInfo(): List<WorkBox> {
        val list = ArrayList<WorkBox>()
        list.apply {
            add(
                WorkBox(
                    title = "邀请好友",
                    content = "邀请用户，平台额外送收益！",
                    icon = "https://oimrs6ayd.qnssl.com/wb/homepage/invite_friend.png",
                    group = 0
                ),
            )

            for (i in 0..7) {
                add(
                    WorkBox(
                        title = "polygon甄别任务测试324",
                        content = "0.5~2ACN/任务",
                        icon = "https://view.alive-story.com/task-source-file-dev/icon/task_group_icon_1593490183.png",
                        group = 1
                    ),
                )
            }

            add(
                WorkBox(
                    title = "workbox排名竞赛来袭",
                    content = "做Workbox任务，赢5000 ACN奖励",
                    icon = "",
                    bgUrl = "https://bytebridge.s3.ap-northeast-1.amazonaws.com/files/event_banner.png",
                    group = 2
                ),
            )

            add(
                WorkBox(
                    title = "补贴奖励",
                    content = "再完成3个任务即可获得50ACN补贴",
                    icon = "https://view.alive-story.com/task-source-file/icon/subsidy_icon_1600423228.png",
                    bgUrl = "https://view.alive-story.com/task-source-file/icon/subsidy_background_1600411217.png",
                    group = 3
                ),
            )

        }

        return list
    }

}