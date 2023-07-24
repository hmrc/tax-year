tax-year
====
[Download](https://open.artefacts.tax.service.gov.uk/maven2/uk/gov/hmrc/tax-year_2.13/)

This is a UK tax year utility. Therefore, it uses the `Europe/London` timezone.

```scala
import uk.gov.hmrc.time.TaxYear
```

### Features
* getting the tax year for a given date
```scala
TaxYear.taxYearFor(LocalDate.of(2023, 7, 21)) // 2023 to 2024
```
* getting the current tax year
```scala
TaxYear.current // 2023 to 2024
```
* determining the extent of a tax year
```scala
TaxYear.current.starts   // 2023-04-06
TaxYear.current.finishes // 2024-04-05
```
* navigating tax years
```scala
TaxYear.current.previous // 2022 to 2023
TaxYear.current          // 2023 to 2024
TaxYear.current.next     // 2024 to 2025
```
* go back or forward by given tax years
```scala
TaxYear.current.back(2)     // 2021 to 2022
TaxYear.current.forwards(2) // 2025 to 2026
```
* determine if given date is within the tax year
```scala
TaxYear.current.contains(LocalDate.of(2023, 4, 5)) // false
TaxYear.current.contains(LocalDate.of(2023, 4, 6)) // true
```
* support scala 2.13 with version 2 and above
* support for scala 2.11 dropped from version 3

### Adding tax-year as a dependency

```scala
"uk.gov.hmrc" %% "tax-year" % "[INSERT_VERSION]"
```

## License ##
 
This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html"). 

