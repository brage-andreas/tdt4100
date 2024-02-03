### Del 1

> Målet for oppgaven er å finne en feil i funksjonen `part1()` ved hjelp av debuggeren i VSCode. Kjør hovedprogrammet i debug-modus, og bruk dette til å finne hva kapasiteten og nåværende volum av kaffe er like før programmet utløser et unntak.
>
> Finn også ut hvilken metode i **CoffeeCup** som utløser unntaket.

Svar:

-   `capacity` er `57.0`
-   `currentVolume` er `5.0`
-   Metoden `CoffeeCup#drinkCoffee` utløser unntaket

### Del 2

> Du fant feilen i oppgave 1, bra! Kommentér ut kallet til `part1()` i hovedprogrammet, så slipper vi å ha mer med det å gjøre.
>
> Du skal nå finne ut hvordan nåverende volum av kaffe endrer seg i `part2()`, før programmet utløser et unntak. Lag en liste over hvilke verdier nivået har. Hvilken metode i **CoffeeCup** utløser et unntak denne gangen? Hvilken type unntak blir utløst?

Svar:

-   `currentVolume`:

    1.  `0.0`
    2.  `20.5`
    3.  `14.5`
    4.  `38.5`
    5.  `36.5`
    6.  `6.5`
    7.  Exception når programmet prøver å endre til `46.5`

-   Metoden `CoffeeCup#fillCoffee` utløser unntaket
-   `IllegalArgumentException` er typen unntak som blir utløst
