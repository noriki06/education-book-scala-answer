package education.section3

class Skill(
  val skillName: String,//技名
  val pronunciation: String,//よみがな
  val type: String,//種別
  val power: Int//威力
)

class Pokemon(
  val pokemonName: String,//ポケモン名
  val pronunciation: String,//よみがな
  val maxHP: Int,//最大 HP
  val currentHP : Int,//現在 HP
  val rememberedTechniques: String//覚えている技
)

class Trainer(
  val trainerName: String,//トレーナー名
  val holdsPokemon: String//手持ちポケモン
)

object AnswerEx3_1:

  def main(args: Array[String]): Unit =
    val trainers: Seq[Trainer] = Seq(
      Trainer("サトシ", List(Pokemon["ピカチュウ", "リザードン", "カビゴン"])),
      Trainer("カスミ", List(Pokemon["ゼニガメ", "フシギバナ"])),
      Trainer("タケシ", List(Pokemon["ゼニガメ", "イワーク", "イシツブテ"]))
    )

    val pokemon: Seq[Pokemon] = Seq(
      Pokemon("リザードン", "りざーどん", 78, 78, List(Skill["火炎放射", "破壊光線"])),
      Pokemon("カビゴン", "かびごん", 160, 160, List(Skill["地震", "回復"])),
      Pokemon("ゼニガメ", "ぜにがめ", 44, 44, List(Skill["水鉄砲", "甲羅休め"])),
      Pokemon("フシギバナ", "ふしぎばな", 80, 80, List(Skill["草結び", "光合成"])),
      Pokemon("イワーク", "いわーく", 55, 55, List(Skill["岩石封じ", "地割れ"])),
      Pokemon("イシツブテ", "いしつぶて", 40, 40, List(Skill["落石", "治療"]))
    )

    val skill: Seq[Skill] = Seq(
      Skill("電光石火", "でんこうせっか", "攻撃", 40),
      Skill("自己再生", "じこさいせい", "回復", 50),
      Skill("火炎放射", "かえんほうしゃ", "回復", 40),
      Skill("破壊光線", "はかいこうせん", "攻撃", 150),
      Skill("地震", "じしん", "攻撃", 100),
      Skill("回復", "かいふく", "回復", 60),
      Skill("水鉄砲", "みずでっぽう", "攻撃", 45),
      Skill("甲羅休め", "こうらやすめ", "攻撃", 40),
      Skill("草結び", "くさむすび", "攻撃",65),
      Skill("光合成", "こうごうせい", "回復", 70),
      Skill("岩石封じ", "がんせきふうじ	", "攻撃", 60),
      Skill("地割れ", "じわれ", "攻撃", 120),
      Skill("落石", "らくせき", "攻撃", 40),
      Skill("治療", "ちりょう", "回復", 30)
    )

    println(showAllSkills(trainers))

  def showAllSkills(trainers: Seq[Trainer]): Unit =
    trainers.map(p => p.holdsPokemon).map(s => s.skillName).distinct






