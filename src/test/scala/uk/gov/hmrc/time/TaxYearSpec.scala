/*
 * Copyright 2016 HM Revenue & Customs
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

import org.joda.time.{ DateTimeZone, DateTime, LocalDate }
import org.scalatest.{WordSpecLike, Matchers}

class TaxYearSpec extends WordSpecLike with Matchers {

  class setUp extends CurrentTaxYear {
    
    var now = () => new DateTime(2013, 9, 24, 11, 39, 55, 222, DateTimeZone.forID("Europe/London"))
    
    def givenIts(when: DateTime): Unit = now = () => when
  }

  val _2011_12 = TaxYear(2011)
  val _2012_13 = TaxYear(2012)
  val _2013_14 = TaxYear(2013)
  val _2014_15 = TaxYear(2014)


  "Requesting the tax year for a date" should {

    "Return 2012 when the date is 2013/4/5" in new setUp {
      taxYearFor(new LocalDate(2013, 4, 5)) shouldBe _2012_13
    }

    "Return 2013 when the date is 2013/4/6" in new setUp {
      taxYearFor(new LocalDate(2013, 4, 6)) shouldBe _2013_14
    }

    "Return 2013 when the date is 2013/9/24" in new setUp {
      taxYearFor(new LocalDate(2013, 9, 24)) shouldBe _2013_14
    }

    "Return 2013 when the date is 2014/2/1" in new setUp {
      taxYearFor(new LocalDate(2014, 2, 1)) shouldBe _2013_14
    }

    "Return 2013 when the date is 2014/4/5" in new setUp {
      taxYearFor(new LocalDate(2014, 4, 5)) shouldBe _2013_14
    }

    "Return 2014 when the date is 2014/4/6" in new setUp {
      taxYearFor(new LocalDate(2014, 4, 6)) shouldBe _2014_15
    }
  }

  "Requesting the current" should {

    "Return 2012 when the current UK time is 23:59:59.999 on 2013/4/5" in new setUp {
      givenIts(new DateTime(2013, 4, 5, 23, 59, 59, 999, DateTimeZone.forID("Europe/London")))
      current shouldBe _2012_13
    }

    "Return 2013 when the current UK time is 00:00:00.000 on 2013/4/6" in new setUp {
      givenIts(new DateTime(2013, 4, 6, 0, 0, 0, 0, DateTimeZone.forID("Europe/London")))
      current shouldBe _2013_14
    }

    "Return 2013 when the current UK time is 11:06:23.323 on 2013/9/24" in new setUp {
      givenIts(new DateTime(2013, 9, 24, 11, 6, 23, 323, DateTimeZone.forID("Europe/London")))
      current shouldBe _2013_14
    }

    "Return 2013 when the current UK time is 11:06:23.323 on 2014/2/1" in new setUp {
      givenIts(new DateTime(2014, 2, 1, 11, 6, 23, 323, DateTimeZone.forID("Europe/London")))
      current shouldBe _2013_14
    }

    "Return 2013 when the current UK time is 23:59:59.999 on 2014/4/5" in new setUp {
      givenIts(new DateTime(2014, 4, 5, 23, 59, 59, 999, DateTimeZone.forID("Europe/London")))
      current shouldBe _2013_14
    }

    "Return 2014 when the current UK time is 00:00:00.000 on 2014/4/6" in new setUp {
      givenIts(new DateTime(2014, 4, 6, 0, 0, 0, 0, DateTimeZone.forID("Europe/London")))
      current shouldBe _2014_15
    }
  }


  "the tax year 2012-13" should {
    "start on 2012/4/6" in {
      _2012_13.starts shouldBe new LocalDate(2012, 4, 6)
    }
    "start at exactly 00:00:00 on 2012/4/6" in {
      _2012_13.startInstant shouldBe new DateTime(2012, 4, 6, 0, 0, 0, 0, DateTimeZone.forID("Europe/London"))
    }
    "finish on 2013/4" in {
      _2012_13.finishes shouldBe new LocalDate(2013, 4, 5)
    }
    "end at exactly at 00:00:00 on 2013/4/6" in {
      _2012_13.finishInstant shouldBe new DateTime(2013, 4, 6, 0, 0, 0, 0, DateTimeZone.forID("Europe/London"))
    }
    "span 00:00:00 on 2012/4/6 until 00:00:00 on 2013/4/6" in {
      _2012_13.interval.getStart shouldBe new DateTime(2012, 4, 6, 0, 0, 0, 0, DateTimeZone.forID("Europe/London"))
      _2012_13.interval.getEnd shouldBe new DateTime(2013, 4, 6, 0, 0, 0, 0, DateTimeZone.forID("Europe/London"))
    }
    "not contain 5th April 2012 as that's in the last tax year" in {
      _2012_13.contains(new LocalDate(2012, 4, 5)) shouldBe false
    }

    "contain 6th April 2012" in {
      _2012_13.contains(new LocalDate(2012, 4, 6)) shouldBe true
    }
    
    "contain 31st Dec 2012" in {
      _2012_13.contains(new LocalDate(2012, 12, 31)) shouldBe true
    }

    "contain 1st January 2013" in {
      _2012_13.contains(new LocalDate(2013, 1, 1)) shouldBe true
    }

    "contain 5th April 2013" in {
      _2012_13.contains(new LocalDate(2013, 4, 5)) shouldBe true
    }

    "not contain 6th April 2013 as that's in the NEXT tax year" in {
      _2012_13.contains(new LocalDate(2013, 4, 6)) shouldBe false
    }

    "not contain 1st Jan 2013 as that's in the NEXT tax year" in {
      _2012_13.contains(new LocalDate(2014, 1, 1)) shouldBe false
    }

    "be preceded by 2011-12" in {
      _2012_13.previous shouldBe _2011_12
    }

    "be followed by 2013-14" in {
      _2012_13.next shouldBe _2013_14
    }
  }

}
