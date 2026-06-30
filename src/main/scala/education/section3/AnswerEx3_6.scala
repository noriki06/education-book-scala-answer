package education.section3

object AnswerEx3_6:
  val MATH_RANDOM = new scala.util.Random(256)


  // ポケモンの技のケースクラス
  case class PokemonSkill(
    val name: String,//技名
    val pronunciation: String,//よみがな
    val kind: String,//種別
    val power: Int//威力
  )
  // ポケモンのケースクラス
  case class Pokemon(
    val name: String,//ポケモン名
    val pronunciation: String,//よみがな
    val hpMax: Int,//最大 HP
    val hp: Int,//現在 HP
    val skill: Seq[PokemonSkill]//覚えている技
  )

  // トレーナーのケースクラス
  case class Trainer(
    val name: String,//トレーナー名
    val holdsPokemon: Seq[Pokemon]//手持ちポケモン
  )

  val trainers: Seq[Trainer] = Seq( // トレーナーデータ
    Trainer("サトシ",Seq(
      Pokemon("ピカチュウ", "ぴかちゅう", 35, 35, Seq(
        PokemonSkill("電光石火", "でんこうせっか", "攻撃",40),
        PokemonSkill("自己再生", "じこさいせい", "回復", 50)
      ) ),
      Pokemon("リザードン", "りざーどん", 78, 78, Seq(
          PokemonSkill("火炎放射", "かえんほうしゃ", "攻撃",90),
          PokemonSkill("破壊光線", "はかいこうせん", "攻撃", 150)
      ) ),
      Pokemon("カビゴン", "かびごん", 160, 160,Seq(
          PokemonSkill("地震", "じしん", "攻撃",100),
          PokemonSkill("回復", "かいふく", "回復", 60)
    ) ) ) ),
    Trainer("カスミ", Seq(
      Pokemon("ゼニガメ", "ぜにがめ", 44, 44, Seq(
          PokemonSkill("水鉄砲", "みずでっぽう", "攻撃",45),
          PokemonSkill("甲羅休め", "こうらやすめ", "回復", 40)
      ) ),
      Pokemon("フシギバナ", "ふしぎばな", 80, 80, Seq(
          PokemonSkill("草結び", "くさむすび", "攻撃",65),
          PokemonSkill("光合成", "こうごうせい", "回復", 70)
    ) ) ) ),
    Trainer("タケシ", Seq(
      Pokemon("ゼニガメ", "ぜにがめ", 44, 44, Seq(
          PokemonSkill("水鉄砲", "みずでっぽう", "攻撃",45),
          PokemonSkill("甲羅休め", "こうらやすめ", "回復", 40)
      ) ),
      Pokemon("イワーク", "いわーく", 55, 55, Seq(
        PokemonSkill("岩石封じ", "がんせきふうじ", "攻撃",60),
        PokemonSkill("地割れ", "じわれ", "攻撃", 120)
      ) ),
      Pokemon("イシツブテ", "いしつぶて", 40, 40, Seq(
        PokemonSkill("落石", "らくせき", "攻撃",50),
        PokemonSkill("治療", "ちりょう", "回復", 30)
  ) ) ) ) )

  val satoshi = trainers(0).holdsPokemon
  val kasumi = trainers(1).holdsPokemon
  val takeshi = trainers(2).holdsPokemon


  def main(args: Array[String]): Unit =
    println(battle(satoshi, kasumi, true, 1))

  /*
   *「攻撃側が 1 ターン行動した結果の (新しい攻撃側チーム, 新しい防御側チーム)」を返します。
   * 攻撃なら防御側が、回復なら攻撃側が更新されます。
   */
  def takeTurn(attacker: Seq[Pokemon], defender: Seq[Pokemon]): (Seq[Pokemon], Seq[Pokemon]) =
    val attackerPokemon = // 攻撃するポケモンの選定（生きてる中の、先頭）
      attacker
        .filter(pokemon => pokemon.hp > 0)
        .head

    def randomAliveIndex(team: Seq[Pokemon]): Int =
      val index = MATH_RANDOM.nextInt(team.size)

      if team(index).hp > 0 then
        index
      else
        randomAliveIndex(team)

    val randomRecoverIndex =
      randomAliveIndex(attacker)

    val recoverePokemon =
      attacker(randomRecoverIndex)

    val randomDefenderIndex =
      randomAliveIndex(defender)

    val defenderPokemon =
      defender(randomDefenderIndex)

    val randomSkillIndex = // ランダムなインデックスを取得（攻撃側の技選定用）
      MATH_RANDOM
        .nextInt(attackerPokemon.skill.size)

    val attackSkill = // ランダムなインデックスで技を決定
      attackerPokemon
        .skill(randomSkillIndex)

    val newDefenderHp = // 攻撃された防御側のポケモンのhpを変更（０が最小値）
      scala
        .math
        .max(0, defenderPokemon.hp - attackSkill.power)

    val updatedDefenderPokemon = // 攻撃されたhpで新しくポケモンインスタンスの生成
      defenderPokemon
        .copy(hp = newDefenderHp)

    val newAttackerHp = // 回復された攻撃側のポケモンのhpを変更（maxHpが最大値）
      scala
        .math
        .min(recoverePokemon.hpMax, recoverePokemon.hp + attackSkill.power)

    val updatedAttackerPokemon = // 回復されたhpで新しくポケモンインスタンスの生成
      attackerPokemon
        .copy(hp = newAttackerHp)

    val updatedDefenderTeam = // ポケモンseqを更新、攻撃されたポケモンの要素をアップデート
      defender
        .updated(randomDefenderIndex, updatedDefenderPokemon)

    val updatedAttackerTeam = // ポケモンseqを更新、回復されたポケモンの要素をアップデート
      attacker
        .updated(randomRecoverIndex, updatedAttackerPokemon)

    attackSkill.kind match // 技の種類で表示を場合分け
      case "攻撃" => (attacker, updatedDefenderTeam)
      // 攻撃時（攻撃したポケモンseq, 攻撃されたポケモン）
      case "回復" => (updatedAttackerTeam, defender) //

  /*
   * 決着: どちらかのチームの 全ポケモンの体力が 0（ひんし） になったら、もう一方の勝ち。
   * 引き分け: 決着がつかないまま 20 ターン を超えたら引き分け（回復ばかりで終わらない場合の保険）。
   */
  def battle(a: Seq[Pokemon], b: Seq[Pokemon], aTurn: Boolean, turn: Int): String =
    if      a.forall(a => a.hp <= 0) then s"決着: トレーナーBの勝ち！" // b の勝ち
    else if b.forall(b => b.hp <= 0) then s"決着: トレーナーAの勝ち！" // a の勝ち
    else if turn > 20 then "引き分け"                         // 決着がつかないまま 20 ターン を超えたら引き分け
    else
      val (na, nb) = if aTurn then takeTurn(a, b) else takeTurn(b, a).swap
      battle(na, nb, !aTurn, turn + 1)
