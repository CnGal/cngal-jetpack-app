package com.cngal.app.model.entry

enum class PositionGeneralType
{
    Other,
    Video,
    Design,
    Music,
    ComposingWords,
    Singing,
    Script,
    CV,
    Show,
    FineArts,
    Program,
    Operate,
    Issue,
    Make,
    PV,
    LaterStage,
    MainUrge,
    Plan,
    ComposeMusic,
    ProductionGroup,
    Publisher,
    SpecialThanks
}

fun String.getGeneralType(): PositionGeneralType
{
    try
    {
        return PositionGeneralType.valueOf(this)
    } catch (e: IllegalArgumentException)
    {
        //查找其他符合的文本
        if (this.contains("原画") || this.contains("画师"))
        {
            return PositionGeneralType.FineArts
        }
        else if (this.contains("设计"))
        {
            return PositionGeneralType.FineArts
        }
        else if ((this.contains("配音") || this.contains("声优") || this.uppercase()
                .contains("CV") || this.uppercase()
                .contains("CAST")) && !this.contains("导演") && !this.contains("监督") && !this.contains(
                "制作"
            ) && !this.contains("后期") && !this.contains("处理") && !this.contains("后制")
        )
        {
            return PositionGeneralType.CV
        }
        else if (this.contains("感谢") || this.uppercase().contains("鸣谢") || this.uppercase()
                .contains("致谢")
        )
        {
            return PositionGeneralType.SpecialThanks
        }

        return PositionGeneralType.Other
    }


}