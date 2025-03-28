tax-year
====
[Download](https://open.artefacts.tax.service.gov.uk/maven2/uk/gov/hmrc/tax-year_2.13/)

This is a UK tax year utility. Therefore, it uses the `Europe/London` timezone.

Change History
====
v6.0 - 28 Mar 25
- update library dependencies

v5.0 - 19 Jun 24
- add Scala 3.0 support, noting Scala 2.13 is still supported
- update library dependencies

v4.0 - 05 Dec 23
- update Scala 2.13 version
- update library dependencies
- add 'Change History' section to README

v3.3.0 - 01 Aug 23
- add README instructions

v3.2.0 - 06 May 22
- update Scala version

v2.0.0 - 07 Apr 22
- drop redundant scalatestplus-play dependency

```scala
import uk.gov.hmrc.time.TaxYear
```

### Features
* getting the tax year for a given date
```scala
TaxYear.taxYearFor(LocalDate.of(2024, 7, 21)) // 2024 to 2025
```
* getting the current tax year
```scala
TaxYear.current // 2024 to 2025
```
* determining the extent of a tax year
```scala
TaxYear.current.starts   // 2024-04-06
TaxYear.current.finishes // 2025-04-05
```
* navigating tax years
```scala
TaxYear.current.previous // 2023 to 2024
TaxYear.current          // 2024 to 2025
TaxYear.current.next     // 2025 to 2026
```
* go back or forward by given tax years
```scala
TaxYear.current.back(2)     // 2022 to 2023
TaxYear.current.forwards(2) // 2026 to 2027
```
* determine if given date is within the tax year
```scala
TaxYear.current.contains(LocalDate.of(2024, 4, 5)) // false
TaxYear.current.contains(LocalDate.of(2024, 4, 6)) // true
```

### Adding tax-year as a dependency

```scala
"uk.gov.hmrc" %% "tax-year" % "[INSERT_VERSION]"
```

## License ##
 
This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html"). 

