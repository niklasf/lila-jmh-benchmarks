package org.openjdk.jmh.samples

import org.openjdk.jmh.annotations._

import java.util.concurrent.TimeUnit

@State(Scope.Thread)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
class HtmlEscape {

    def escape(s: String): String = {
      val sb = new StringBuilder
      var i = 0
      while (i < s.length) {
        sb.append {
          s.charAt(i) match {
            case '<' => "&lt;"
            case '>' => "&gt;"
            case '&' => "&amp;"
            case '"' => "&quot;"
            case '\'' => "&#39;"
            case c => c
          }
        }
        i += 1
      }
      sb.toString
    }

  val shortAscii   = "Outside the board"
  val shortUnicode = "Ожидание соперниа"

  val longAscii   = "Free online Chess server. Play Chess now in a clean interface. No registration, no ads, no plugin required. Play Chess with the computer, friends or random opponents."
  val longUnicode = "Бесплатные онлайн-шахматы. Играйте в шахматы прямо сейчас в простом интерфейсе без рекламы. Не требует регистрации и скачивания программы. Играйте в шахматы с компьи."

  @Benchmark
  def testShortAscii = escape(shortAscii)

  @Benchmark
  def testShortUnicode = escape(shortUnicode)

  @Benchmark
  def testLongAscii = escape(longAscii)

  @Benchmark
  def testLongUnicode = escape(longUnicode)
}
