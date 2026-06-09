package education.section3

class Skill(
  val skillName: String,//技名
  val pronunciation: String,//よみがな
  val kind: String,//種別
  val power: Int//威力
)

class Pokemon(
  val pokemonName: String,//ポケモン名
  val pronunciation: String,//よみがな
  val maxHP: Int,//最大 HP
  val currentHP : Int,//現在 HP
  val rememberedTechniques: Seq[Skill]//覚えている技
)

class Trainer(
  val trainerName: String,//トレーナー名
  val holdsPokemon: Seq[Pokemon]//手持ちポケモン
)

object AnswerEx3_1:

  def main(args: Array[String]): Unit =
    val trainers: Seq[Trainer] = Seq(
      Trainer("サトシ",Seq(
          Pokemon("ピカチュウ", "ぴかちゅう", 35, 35, Seq(
            Skill("電光石火", "でんこうせっか", "攻撃",40),
            Skill("自己再生", "じこさいせい", "回復", 50)
          ) ),
          Pokemon("リザードン", "りざーどん", 78, 78, Seq(
              Skill("火炎放射", "かえんほうしゃ", "攻撃",90),
              Skill("破壊光線", "はかいこうせん", "攻撃", 150)
          ) ),
          Pokemon("カビゴン", "かびごん", 160, 160,Seq(
              Skill("地震", "じしん", "攻撃",100),
              Skill("回復", "かいふく", "回復", 60)
      ) ) ) ),
      Trainer("カスミ", Seq(
          Pokemon("ゼニガメ", "ぜにがめ", 44, 44, Seq(
              Skill("水鉄砲", "でんこうせっか", "攻撃",40),
              Skill("甲羅休め", "じこさいせい", "回復", 50)
          ) ),
          Pokemon("フシギバナ", "りざーふしぎばな", 80, 80, Seq(
              Skill("草結び", "くさむすび", "攻撃",65),
              Skill("光合成", "こうごうせい", "回復", 70)
      ) ) ) ),
      Trainer("タケシ", Seq(
          Pokemon("ゼニガメ", "ぜにがめ", 44, 44, Seq(
              Skill("水鉄砲", "みずでっぽう	", "攻撃",45),
              Skill("甲羅休め", "こうらやすめ", "回復", 40)
          ) ),
          Pokemon("イワーク", "いわーく", 55, 55, Seq(
              Skill("岩石封じ", "がんせきふうじ	", "攻撃",60),
              Skill("地割れ", "じわれ", "攻撃", 120)
          ) ),
          Pokemon("イシツブテ", "いしつぶて", 40, 40, Seq(
              Skill("落石", "らくせき", "攻撃",50),
              Skill("治療", "ちりょう", "回復", 30)
    ) ) ) ) )
    println(showAllSkills(trainers))

  def showAllSkills(trainers: Seq[Trainer]): Unit =
    trainers.map(t => t.holdsPokemon).map(p => p.rememberedTechniques)
