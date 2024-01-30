### Del 1: Teori

Les [wikisiden om innkapsling](https://www.ntnu.no/wiki/display/tdt4100/Innkapsling) og svar på følgende:

-   Hva er en synlighetsmodifikator?
-   Hva er forskjellen på private og public og når brukes de?

Teori-oppgavene besvares i en tekstfil eller på papir, og gjennomgås med studass ved godkjenning.

---

Sannsynlighetsmodifikator er en indikator som viser hva slags access modifier en property/method har.  
I wiki-siden viser de "public" med `+` eller grønt, og "private" med `-` eller rødt.

Du har fire access modifiers i Java: `public`, `protected`, `default`, og `private`.

-   `public` gjør at propertien/methoden kan brukes utenfor egen package
-   `protected` gjør at propertien/methoden kan brukes utenfor egen package bare i subclasses
-   `default` gjør at propertien/methoden kan bare brukes i egen package
-   `private` gjør at propertien/methoden kan bare brukes i egen class
