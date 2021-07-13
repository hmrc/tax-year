/*
 * Copyright 2021 HM Revenue & Customs
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

import java.time.{ ZoneId, ZonedDateTime, LocalDate }
import org.scalatest.{WordSpecLike, Matchers}

class TaxYearSpec extends WordSpecLike with Matchers {

  class setUp extends CurrentTaxYear {
    
    var now: () => LocalDate = () => LocalDate.of(2013, 9, 24)

    def givenIts(when: ZonedDateTime): Unit = now = () => when.toLocalDate
  }

  private val _2011_12 = TaxYear(2011)
  private val _2012_13 = TaxYear(2012)
  private val _2013_14 = TaxYear(2013)
  private val _2014_15 = TaxYear(2014)
  private val _2015_16 = TaxYear(2015)
  private val _2016_17 = TaxYear(2016)
  private val _2017_18 = TaxYear(2017)
  private val _2018_19 = TaxYear(2018)
  private val _2019_20 = TaxYear(2019)

  private val ukTimeZone = ZoneId.of("Europe/London")

  "Requesting the tax year for a date" should {

    "Return 2012 when the date is 2013/4/5" in new setUp {
      taxYearFor(LocalDate.of(2013, 4, 5)) shouldBe _2012_13
    }

    "Return 2013 when the date is 2013/4/6" in new setUp {
      taxYearFor(LocalDate.of(2013, 4, 6)) shouldBe _2013_14
    }

    "Return 2013 when the date is 2013/9/24" in new setUp {
      taxYearFor(LocalDate.of(2013, 9, 24)) shouldBe _2013_14
    }

    "Return 2013 when the date is 2014/2/1" in new setUp {
      taxYearFor(LocalDate.of(2014, 2, 1)) shouldBe _2013_14
    }

    "Return 2013 when the date is 2014/4/5" in new setUp {
      taxYearFor(LocalDate.of(2014, 4, 5)) shouldBe _2013_14
    }

    "Return 2014 when the date is 2014/4/6" in new setUp {
      taxYearFor(LocalDate.of(2014, 4, 6)) shouldBe _2014_15
    }

    "Return 2014 when the date is 2015/4/5" in new setUp {
      taxYearFor(LocalDate.of(2015, 4, 5)) shouldBe _2014_15
    }

    "Return 2015 when the date is 2015/4/6" in new setUp {
      taxYearFor(LocalDate.of(2015, 4, 6)) shouldBe _2015_16
    }

    "Return 2015 when the date is 2016/4/5" in new setUp {
      taxYearFor(LocalDate.of(2016, 4, 5)) shouldBe _2015_16
    }

    "Return 2016 when the date is 2016/4/6" in new setUp {
      taxYearFor(LocalDate.of(2016, 4, 6)) shouldBe _2016_17
    }

    "Return 2016 when the date is 2017/4/5" in new setUp {
      taxYearFor(LocalDate.of(2017, 4, 5)) shouldBe _2016_17
    }

    "Return 2017 when the date is 2017/4/6" in new setUp {
      taxYearFor(LocalDate.of(2017, 4, 6)) shouldBe _2017_18
    }

    "Return 2017 when the date is 2018/4/5" in new setUp {
      taxYearFor(LocalDate.of(2018, 4, 5)) shouldBe _2017_18
    }

    "Return 2018 when the date is 2018/4/6" in new setUp {
      taxYearFor(LocalDate.of(2018, 4, 6)) shouldBe _2018_19
    }

    "Return 2018 when the date is 2019/4/5" in new setUp {
      taxYearFor(LocalDate.of(2019, 4, 5)) shouldBe _2018_19
    }

    "Return 2019 when the date is 2019/4/6" in new setUp {
      taxYearFor(LocalDate.of(2019, 4, 6)) shouldBe _2019_20
    }

    "Return 2019 when the date is 2020/4/5" in new setUp {
      taxYearFor(LocalDate.of(2020, 4, 5)) shouldBe _2019_20
    }

  }

  "Requesting the current" should {

    "Return 2012 when the current UK time is 23:59:59.999 on 2013/4/5" in new setUp {
      givenIts(ZonedDateTime.of(2013, 4, 5, 23, 59, 59, 999, ukTimeZone))
      current shouldBe _2012_13
      current.currentYear shouldBe current.startYear
      current.currentYear shouldBe 2012
    }

    "Return 2013 when the current UK time is 00:00:00.000 on 2013/4/6" in new setUp {
      givenIts(ZonedDateTime.of(2013, 4, 6, 0, 0, 0, 0, ukTimeZone))
      current shouldBe _2013_14
    }

    "Return 2013 when the current UK time is 11:06:23.323 on 2013/9/24" in new setUp {
      givenIts(ZonedDateTime.of(2013, 9, 24, 11, 6, 23, 323, ukTimeZone))
      current shouldBe _2013_14
    }

    "Return 2013 when the current UK time is 11:06:23.323 on 2014/2/1" in new setUp {
      givenIts(ZonedDateTime.of(2014, 2, 1, 11, 6, 23, 323, ukTimeZone))
      current shouldBe _2013_14
    }

    "Return 2013 when the current UK time is 23:59:59.999 on 2014/4/5" in new setUp {
      givenIts(ZonedDateTime.of(2014, 4, 5, 23, 59, 59, 999, ukTimeZone))
      current shouldBe _2013_14
    }

    "Return 2014 when the current UK time is 00:00:00.000 on 2014/4/6" in new setUp {
      givenIts(ZonedDateTime.of(2014, 4, 6, 0, 0, 0, 0, ukTimeZone))
      current shouldBe _2014_15
    }
  }


  "the tax year 2012-13" should {
    "start on 2012/4/6" in {
      _2012_13.starts shouldBe LocalDate.of(2012, 4, 6)
    }
    "start at exactly 00:00:00 on 2012/4/6" in {
      _2012_13.startInstant shouldBe ZonedDateTime.of(2012, 4, 6, 0, 0, 0, 0, ukTimeZone)
    }
    "finish on 2013/4" in {
      _2012_13.finishes shouldBe LocalDate.of(2013, 4, 5)
    }
    "end at exactly at 00:00:00 on 2013/4/6" in {
      _2012_13.finishInstant shouldBe ZonedDateTime.of(2013, 4, 6, 0, 0, 0, 0, ukTimeZone)
    }
    "span 00:00:00 on 2012/4/6 until 00:00:00 on 2013/4/6" in {
      _2012_13.interval.getStart shouldBe ZonedDateTime.of(2012, 4, 6, 0, 0, 0, 0, ukTimeZone).toInstant
      _2012_13.interval.getEnd shouldBe ZonedDateTime.of(2013, 4, 6, 0, 0, 0, 0, ukTimeZone).toInstant
    }
    "not contain 5th April 2012 as that's in the last tax year" in {
      _2012_13.contains(LocalDate.of(2012, 4, 5)) shouldBe false
    }

    "contain 6th April 2012" in {
      _2012_13.contains(LocalDate.of(2012, 4, 6)) shouldBe true
    }
    
    "contain 31st Dec 2012" in {
      _2012_13.contains(LocalDate.of(2012, 12, 31)) shouldBe true
    }

    "contain 1st January 2013" in {
      _2012_13.contains(LocalDate.of(2013, 1, 1)) shouldBe true
    }

    "contain 5th April 2013" in {
      _2012_13.contains(LocalDate.of(2013, 4, 5)) shouldBe true
    }

    "not contain 6th April 2013 as that's in the NEXT tax year" in {
      _2012_13.contains(LocalDate.of(2013, 4, 6)) shouldBe false
    }

    "not contain 1st Jan 2013 as that's in the NEXT tax year" in {
      _2012_13.contains(LocalDate.of(2014, 1, 1)) shouldBe false
    }

    "be preceded by 2011-12" in {
      _2012_13.previous shouldBe _2011_12
    }

    "be followed by 2013-14" in {
      _2012_13.next shouldBe _2013_14
    }
  }

}
