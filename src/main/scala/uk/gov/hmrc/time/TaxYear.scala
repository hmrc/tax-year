/*
 * Copyright 2020 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.time

import java.time.{LocalDate, ZonedDateTime}

import org.threeten.extra.Interval

import scala.collection.immutable.Range.Inclusive

case class TaxYear(startYear: Int) {

  lazy val finishYear: Int = startYear + 1
  def starts: LocalDate = TaxYear.firstDayOfTaxYear(startYear)
  def startInstant: ZonedDateTime = starts.atStartOfDay(TaxYear.ukTime)
  lazy val finishes: LocalDate = LocalDate.of(finishYear, 4, 5)
  def finishInstant: ZonedDateTime = next.startInstant

  def back(years: Int): TaxYear = TaxYear(startYear - years)
  lazy val previous: TaxYear = back(1)

  lazy val currentYear : Int = startYear

  def forwards(years: Int): TaxYear = TaxYear(startYear + years)
  lazy val next: TaxYear = forwards(1)

  def contains(date: LocalDate): Boolean = !(date.isBefore(starts) || date.isAfter(finishes))

  def yearRange: Inclusive = startYear to finishYear
  def interval: Interval = Interval.of(startInstant.toInstant, finishInstant.toInstant)

  override def toString = s"$startYear to $finishYear"
}

object TaxYear extends CurrentTaxYear with (Int => TaxYear) {
  override def now: () => LocalDate = () => LocalDate.now(ukTime)
}
