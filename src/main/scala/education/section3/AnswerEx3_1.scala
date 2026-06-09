package education.section3

class Skill(
  val skillName: String,//技名
  val pronunciation: String//よみがな
  val type: String//種別
  val power: Int//威力
)

　class Pokemon(
  　val pokemonName: String,//ポケモン名
  　val pronunciation: Strig,//よみがな
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
      Trainer("サトシ", Pokemon["ピカチュウ", "リザードン", "カビゴン"]),
      Trainer("カスミ", Pokemon["ゼニガメ", "フシギバナ"]),
      Trainer("タケシ", Pokemon["ゼニガメ", "イワーク", "イシツブテ"])
    )

    val pokemon: Seq[Pokemon] = seq(
      Pokemon("ピカチュウ", "ぴかちゅう", 35, Skill["電光石火", "自己再生"])
      Pokemon("リザードン", "りざーどん", 78, Skill["火炎放射", "破壊光線"])
      Pokemon("カビゴン", "かびごん", 160, Skill["地震", "回復"])
      Pokemon("ゼニガメ", "ぜにがめ", 44, Skill["水鉄砲", "甲羅休め"])
      Pokemon("フシギバナ", "ふしぎばな", 80, Skill["草結び", "光合成"])
      Pokemon("イワーク", "いわーく", 55, Skill["岩石封じ", "地割れ"])
      Pokemon("イシツブテ", "いしつぶて", 40, Skill["落石", "治療"])








  def showAllSkills(trainers: Seq[Trainer]): Unit =
    =






