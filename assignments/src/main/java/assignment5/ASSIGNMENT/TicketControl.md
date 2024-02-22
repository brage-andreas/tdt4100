# Interface - TicketControl-oppgave

Denne oppgaven handler om å lage en representasjon av billetter. Du skal lage et grensesnitt for billetter, og to klasser som implementer dette; en som representerer en enkeltbillett og en som representerer en periodebillett.

## Del 1 - Ticket-grensesnitt

Lag et grensesnitt kalt `Ticket`, med følgende metoder:

* `boolean scan()` - Denne metoden kalles hver gang billetten scannes. Dersom billetten er gyldig, skal metoden returnere `true`, ellers `false`. Legg merke til at du **ikke** skal implementere metoden her, du skal bare definere grensesnittet.

Tanken her er at vi kan senere lage en kontrollørklasse som kan bruke `scan()`-metoden for å sjekke om en billett er gyldig.

## Del 2 - SingleTicket-klasse

Lag en klasse kalt `SingleTicket` som implementerer `Ticket`-grensesnittet. `SingleTicket` skal implementere `scan()`-metoden slik at den kun returnerer `true` den første gangen metoden blir kalt.

**Hint:** Du kan bruke en boolean-variabel for å holde styr på om billetten er gyldig eller ikke.

## Del 3 - PeriodTicket-klasse

Lag en klasse kalt `PeriodTicket` som implementerer `Ticket`-grensesnittet. `PeriodTicket` skal implementere `scan()`-metoden slik at den returnerer `true` dersom det nåværende tidspunktet er mellom to gitte tidspunkter. `PeriodTicket` skal ha en konstruktør som tar inn to `LocalDateTime`-objekter som representerer start- og sluttidspunktet for gyldigheten til billetten. Legg til validering der det gir mening.